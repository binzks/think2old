package org.think2.jdbc;

import org.think2.jdbc.bean.Column;
import org.think2.jdbc.filter.KeyExpression;
import org.think2.jdbc.filter.OrderExpression;
import org.think2.jdbc.type.OrderType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zhoubin on 15/11/27.
 * 查询生成器，主要用于生成查询过滤、排序、分页、分组
 */
public class QueryBuilder {

    private int begin;  //分页开始
    private int size;   //获取数据数量
    private List<SqlExpression> groups;  //分组
    private List<SqlExpression> orders;  //排序
    private List<SqlExpression> filters; //过滤

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
        this.groups.add(new KeyExpression(keys));
    }

    /**
     * 添加倒叙排序，按照添加先后顺序排序，多个desc字段可以重复
     *
     * @param keys order by字段名称，为model定义的字段名称，根据model定义获取字段的实际所属join、expression等
     */
    public void desc(String... keys) {
        this.orders.add(new OrderExpression(OrderType.Desc, keys));
    }

    /**
     * 添加正序排序，按照添加先后顺序排序，多个asc字段可以重复
     *
     * @param keys order by字段名称，为model定义的字段名称，根据model定义获取字段的实际所属join、expression等
     */
    public void asc(String... keys) {
        this.orders.add(new OrderExpression(OrderType.Asc, keys));
    }

    /**
     * and等于过滤条件
     *
     * @param key   过滤字段名
     * @param value 过滤值
     * @return 过滤表达式
     */
    public void eq(String key, Object value) {
        this.filters.add(Filter.and(Filter.eq(key, value)));
    }

    /**
     * and不等于过滤条件
     *
     * @param key   过滤字段名
     * @param value 过滤值
     * @return 过滤表达式
     */
    public void ne(String key, Object value) {
        this.filters.add(Filter.and(Filter.ne(key, value)));
    }

    /**
     * and大于过滤条件
     *
     * @param key   过滤字段名
     * @param value 过滤值
     * @return 过滤表达式
     */
    public void gt(String key, Object value) {
        this.filters.add(Filter.and(Filter.gt(key, value)));
    }

    /**
     * and大于等于过滤条件
     *
     * @param key   过滤字段名
     * @param value 过滤值
     * @return 过滤表达式
     */
    public void ge(String key, Object value) {
        this.filters.add(Filter.and(Filter.ge(key, value)));
    }

    /**
     * and小于过滤条件
     *
     * @param key   过滤字段名
     * @param value 过滤值
     * @return 过滤表达式
     */
    public void lt(String key, Object value) {
        this.filters.add(Filter.and(Filter.lt(key, value)));
    }

    /**
     * and小于等于过滤条件
     *
     * @param key   过滤字段名
     * @param value 过滤值
     * @return 过滤表达式
     */
    public void le(String key, Object value) {
        this.filters.add(Filter.and(Filter.le(key, value)));
    }

    /**
     * and null过滤条件
     *
     * @param key 过滤字段名
     * @return 过滤表达式
     */
    public void isNull(String key) {
        this.filters.add(Filter.and(Filter.isNull(key)));
    }

    /**
     * and not null过滤条件
     *
     * @param key 过滤字段名
     * @return 过滤表达式
     */
    public void notNull(String key) {
        this.filters.add(Filter.and(Filter.notNull(key)));
    }

    /**
     * and in过滤条件
     *
     * @param key    过滤字段名
     * @param values 过滤值
     * @return 过滤表达式
     */
    public void in(String key, Object... values) {
        this.filters.add(Filter.and(Filter.in(key, values)));
    }

    /**
     * and not in过滤条件
     *
     * @param key    过滤字段名
     * @param values 过滤值
     * @return 过滤表达式
     */
    public void notIn(String key, Object... values) {
        this.filters.add(Filter.and(Filter.notIn(key, values)));
    }

    /**
     * and between过滤条件
     *
     * @param key   过滤字段名
     * @param begin 开始值
     * @param end   结束值
     * @return 过滤表达式
     */
    public void between(String key, Object begin, Object end) {
        this.filters.add(Filter.and(Filter.between(key, begin, end)));
    }

    /**
     * and like过滤条件，过滤值两边都加上%过滤
     *
     * @param key   过滤字段名
     * @param value 过滤值
     * @return 过滤表达式
     */
    public void like(String key, Object value) {
        this.filters.add(Filter.and(Filter.like(key, value)));
    }


    /**
     * and not like过滤条件，过滤值两边都加上%过滤
     *
     * @param key   过滤字段名
     * @param value 过滤值
     * @return 过滤表达式
     */
    public void notLike(String key, Object value) {
        this.filters.add(Filter.and(Filter.notLike(key, value)));
    }

    /**
     * 根据表达式添加过滤，可以添加or条件 如Filter.or(Filter.eq(key, value))表示or key = value
     * 可以添加复杂条件，比如Filter.and(Filter.eq("key1", "value1"), Filter.and(Filter.eq("key2", "value2")));
     * 生成条件 AND (key1 = value1 AND key2 = value2)
     *
     * @param sqlExpression 过滤表达式
     */
    public void filter(SqlExpression sqlExpression) {
        this.filters.add(sqlExpression);
    }

    /**
     * 将查询生成器转换为sql语句
     *
     * @param columns model对象列
     * @return sql语句
     */
    public String toSqlString(Map<String, Column> columns) {
        StringBuilder sql = new StringBuilder();
        for (SqlExpression sqlExpression : filters) {
            sql.append(sqlExpression.toSqlString(columns));
        }
        return sql.toString();
    }

    /**
     * 获取查询sql对应的过滤值
     *
     * @return 过滤值数组
     */
    public Object[] toValues() {
        List<Object> list = new ArrayList<>();
        for (SqlExpression sqlExpression : filters) {
            Object[] values = sqlExpression.toValues();
            if (null != values) {
                for (Object value : values) {
                    list.add(value);
                }
            }
        }
        return list.toArray();
    }

}
