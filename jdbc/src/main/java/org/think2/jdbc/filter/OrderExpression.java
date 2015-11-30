package org.think2.jdbc.filter;

import org.think2.jdbc.SqlExpression;
import org.think2.jdbc.bean.Column;
import org.think2.jdbc.type.OrderType;

import java.util.Map;

/**
 * Created by zhoubin on 15/11/29.
 */
public class OrderExpression implements SqlExpression {

    private OrderType orderType; //排序类型
    private KeyExpression keyExpression; //过滤字段表达式

    /***
     * 排序表达式
     *
     * @param orderType 排序类型desc、asc
     * @param keys      排序字段集合
     */
    public OrderExpression(OrderType orderType, String... keys) {
        this.orderType = orderType;
        this.keyExpression = new KeyExpression(keys);
    }

    @Override
    public String toSqlString(Map<String, Column> columns) {
        if (OrderType.Asc == orderType) {
            return this.keyExpression.toSqlString(columns) + " ASC";
        } else {
            return this.keyExpression.toSqlString(columns) + " DESC";
        }
    }

    @Override
    public Object[] toValues() {
        return null;
    }
}
