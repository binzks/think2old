package org.think2.jdbc;

import org.think2.jdbc.bean.Column;
import org.think2.jdbc.filter.KeyExpression;
import org.think2.jdbc.filter.OrderExpression;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zhoubin on 15/11/27.
 * 查询生成器，主要用于生成查询过滤、排序、分页、分组
 */
public class QueryBuilder {

    private int begin;
    private int size;
    private List<SqlExpression> groups;
    private List<SqlExpression> orders;
    private List<SqlExpression> filters;

    public QueryBuilder() {
        groups = new ArrayList<>();
        orders = new ArrayList<>();
        filters = new ArrayList<>();
    }

    /**
     * 添加分页查询条件
     *
     * @param begin 开始行数
     * @param size  获取数据数量
     */
    public void limit(int begin, int size) {
        this.begin = begin;
        this.size = size;
    }

    /**
     * 添加group by条件，没有顺序，并且字段名不能重复
     *
     * @param keys group by字段名称，为model定义的字段名称，根据model定义获取字段的实际所属join、expression等
     */
    public void group(String... keys) {
        groups.add(new KeyExpression(keys));
    }

    /**
     * 添加倒叙排序，按照添加先后顺序排序，多个desc字段可以重复
     *
     * @param keys order by字段名称，为model定义的字段名称，根据model定义获取字段的实际所属join、expression等
     */
    public void desc(String... keys) {
        this.orders.add(new OrderExpression(OrderExpression.OrderType.Desc, keys));
    }

    /**
     * 添加正序排序，按照添加先后顺序排序，多个asc字段可以重复
     *
     * @param keys order by字段名称，为model定义的字段名称，根据model定义获取字段的实际所属join、expression等
     */
    public void asc(String... keys) {
        this.orders.add(new OrderExpression(OrderExpression.OrderType.Asc, keys));
    }

    /**
     * 添加过滤
     *
     * @param sqlExpression 过滤表达式
     */
    public void filter(SqlExpression sqlExpression) {
        this.filters.add(sqlExpression);
    }

    public String toSqlString(Map<String, Column> columns) {
        StringBuilder sql = new StringBuilder();
        for (SqlExpression sqlExpression : filters) {
            sql.append(sqlExpression.toSqlString(columns));
        }
        return sql.toString();
    }

    public Object[] toValues() {
        List<Object> list = new ArrayList<>();
        list.addAll(getValues(filters));
        list.addAll(getValues(orders));
        list.addAll(getValues(groups));
        return list.toArray();
    }

    private List<Object> getValues(List<SqlExpression> list) {
        List<Object> result = new ArrayList<>();
        for (SqlExpression sqlExpression : list) {
            Object[] values = sqlExpression.toValues();
            if (null != values) {
                for (Object value : values) {
                    result.add(value);
                }
            }
        }
        return result;
    }
}
