package org.think2.jdbc.filter;

import org.think2.jdbc.SqlExpression;
import org.think2.jdbc.Think2JdbcException;
import org.think2.jdbc.bean.Column;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zhoubin on 15/11/28.
 */
public class SimpleExpression implements SqlExpression {

    private String key;  //过滤字段名
    private String op;   //过滤操作= > 等
    private Object[] values; //过滤值

    public SimpleExpression(String key, String op, Object value) {
        this.key = key;
        this.op = op;
        this.values = new Object[1];
        this.values[0] = value;
    }

    @Override
    public String toSqlString(Map<String, Column> columns) {
        Column column = columns.get(this.key);
        if (null == column) {
            throw new Think2JdbcException("生成过滤条件失败，字段[" + this.key + "]不存在");
        }
        return String.format("`%s`.`%s` %s ?", column.getJoinAlias(), column.getName(), this.op);
    }

    @Override
    public Object[] toValues() {
        return this.values;
    }
}
