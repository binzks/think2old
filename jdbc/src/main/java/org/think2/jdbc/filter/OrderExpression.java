package org.think2.jdbc.filter;

import org.think2.jdbc.SqlExpression;
import org.think2.jdbc.bean.Column;
import org.think2.jdbc.bean.Order;

import java.util.List;
import java.util.Map;

/**
 * Created by zhoubin on 15/11/29.
 */
public class OrderExpression implements SqlExpression {

    public enum OrderType {
        Desc, Asc
    }

    private OrderType orderType; //排序类型
    private String keys;  //排序字段名集合

    /***
     * 排序表达式
     *
     * @param orderType 排序类型desc、asc
     * @param keys      排序字段集合
     */
    public OrderExpression(OrderType orderType, String... keys) {
        this.orderType = orderType;
        StringBuilder sql = new StringBuilder();
        for (int i = 0; i < keys.length; i++) {
            if (i > 1) {
                sql.append(",");
            }
            sql.append(keys[i]);
        }
        this.keys = sql.toString();
    }

    @Override
    public String toSqlString(Map<String, Column> columns) {
        if (OrderType.Asc == orderType) {
            return keys + " ASC";
        } else {
            return keys + " DESC";
        }
    }

    @Override
    public Object[] toValues() {
        return null;
    }
}
