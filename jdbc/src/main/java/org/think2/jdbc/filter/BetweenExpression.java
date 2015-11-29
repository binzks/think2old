package org.think2.jdbc.filter;

import org.think2.jdbc.SqlExpression;
import org.think2.jdbc.Think2JdbcException;
import org.think2.jdbc.bean.Column;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zhoubin on 15/11/29.
 * between表达式
 */
public class BetweenExpression implements SqlExpression {

    private String key;  //过滤字段名
    private Object[] values; //过滤值

    /**
     * between表达式
     *
     * @param key   过滤字段名
     * @param begin 开始值
     * @param end   结束值
     */
    public BetweenExpression(String key, Object begin, Object end) {
        this.key = key;
        this.values = new Object[2];
        this.values[0] = begin;
        this.values[1] = end;
    }

    @Override
    public String toSqlString(Map<String, Column> columns) {
        Column column = columns.get(this.key);
        if (null == column) {
            throw new Think2JdbcException("生成过滤条件失败，字段[" + this.key + "]不存在");
        }
        StringBuilder sql = new StringBuilder();
        sql.append("`").append(column.getJoinAlias()).append("`.`").append(column.getName()).append("`");
        sql.append(" BETWEEN ? AND ?");
        return sql.toString();
    }

    @Override
    public Object[] toValues() {
        return this.values;
    }
}
