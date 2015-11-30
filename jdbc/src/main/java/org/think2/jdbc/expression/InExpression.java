package org.think2.jdbc.expression;

import org.think2.jdbc.SqlExpression;
import org.think2.jdbc.Think2JdbcException;
import org.think2.jdbc.bean.Column;

import java.util.Map;

/**
 * Created by zhoubin on 15/11/28.
 * in、not in表达式
 */
public class InExpression implements SqlExpression {

    private String key; //过滤字段名称
    private StringBuilder valueSql; //in过滤值?字符串
    private Object[] values; //过滤值
    private boolean not; //true为not in false为in

    /**
     * 生成in表达式
     *
     * @param key    过滤字段名称
     * @param not    true为not in false为in
     * @param values 过滤值
     */
    public InExpression(String key, boolean not, Object... values) {
        this.valueSql = new StringBuilder();
        this.values = values;
        for (int i = 0; i < values.length; i++) {
            if (i > 0) {
                this.valueSql.append(",");
            }
            this.valueSql.append("?");
        }
        this.key = key;
        this.not = not;
    }

    @Override
    public String toSqlString(Map<String, Column> columns) {
        Column column = columns.get(this.key);
        if (null == column) {
            throw new Think2JdbcException("生成过滤条件失败，字段[" + this.key + "]不存在");
        }
        StringBuilder sql = new StringBuilder();
        sql.append("`").append(column.getTableAlias()).append("`.`").append(column.getAlias()).append("`");
        if (not) {
            sql.append(" NOT");
        }
        sql.append(" IN(").append(this.valueSql.toString()).append(")");
        return sql.toString();
    }

    @Override
    public Object[] toValues() {
        return this.values;
    }
}
