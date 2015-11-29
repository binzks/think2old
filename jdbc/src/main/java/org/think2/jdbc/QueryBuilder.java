package org.think2.jdbc;

import org.think2.jdbc.bean.Order;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by zhoubin on 15/11/27.
 * 查询生成器，主要用于生成查询过滤、排序、分页、分组
 */
public class QueryBuilder {

    private int begin;
    private int size;
    private Set<String> groups;
    private List<Order> orders;
    private List<Filter> Filters;

    public QueryBuilder() {
        groups = new HashSet<>();
        orders = new ArrayList<>();
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
        for (String key : keys) {
            groups.add(key);
        }
    }

    /**
     * 添加倒叙排序，按照添加先后顺序排序，多个desc字段可以重复
     *
     * @param keys order by字段名称，为model定义的字段名称，根据model定义获取字段的实际所属join、expression等
     */
    public void desc(String... keys) {
        addOrder(FilterType.OrderDesc, keys);
    }

    /**
     * 添加正序排序，按照添加先后顺序排序，多个asc字段可以重复
     *
     * @param keys order by字段名称，为model定义的字段名称，根据model定义获取字段的实际所属join、expression等
     */
    public void asc(String... keys) {
        addOrder(FilterType.OrderAsc, keys);
    }

    /**
     * 添加排序，按照添加先后顺序排序，多个asc字段可以重复
     *
     * @param type 排序方式desc或者asc
     * @param keys 排序字段，可以多个
     */
    private void addOrder(FilterType type, String... keys) {
        List<String> list = new ArrayList<>();
        for (String key : keys) {
            list.add(key);
        }
        if (list.size() > 0) {
            Order order = new Order();
            order.setType(type);
            order.setKeys(list);
            orders.add(order);
        }
    }

   public void and(Filter... filters){

   }
}
