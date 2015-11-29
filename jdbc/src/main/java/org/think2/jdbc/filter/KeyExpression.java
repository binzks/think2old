package org.think2.jdbc.filter;

import org.think2.jdbc.SqlExpression;
import org.think2.jdbc.Think2JdbcException;
import org.think2.jdbc.bean.Column;

import java.util.Map;

/**
 * Created by zhoubin on 15/11/29.
 * 过滤字段表达式，生成字段1,字段2...字段n sql语句，返回过滤值null，主要用于order、group
 */
public class KeyExpression implements SqlExpression {

    private String[] keys;

    public KeyExpression(String... keys) {
        this.keys = keys;
    }

    @Override
    public String toSqlString(Map<String, Column> columns) {
        StringBuilder sql = new StringBuilder();
        for (int i = 0; i < keys.length; i++) {
            String key = keys[i];
            Column column = columns.get(key);
            if (null == column) {
                throw new Think2JdbcException("生成过滤条件失败，字段[" + key + "]不存在");
            }
            if (i > 0) {
                sql.append(",");
            }
            sql.append("`").append(column.getJoinAlias()).append("`.`").append(column.getAlias()).append("`");

        }
        return sql.toString();
    }

    @Override
    public Object[] toValues() {
        return new Object[0];
    }
}
