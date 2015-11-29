package org.think2.jdbc.filter;

import org.think2.jdbc.SqlExpression;
import org.think2.jdbc.Think2JdbcException;
import org.think2.jdbc.bean.Column;

import java.util.List;
import java.util.Map;

/**
 * Created by zhoubin on 15/11/29.
 */
public class NullExpression implements SqlExpression {

    private String key;  //过滤字段名称
    private boolean not; //true为not null false为null

    /**
     * null过滤表达式
     *
     * @param key 过滤字段名称
     * @param not true为not null false为null
     */
    public NullExpression(String key, boolean not) {
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
        sql.append("`").append(column.getJoinAlias()).append("`.`").append(column.getName()).append("`");
        sql.append(" IS");
        if (not) {
            sql.append(" NOT");
        }
        sql.append(" NULL");
        return sql.toString();
    }

    @Override
    public Object[] toValues() {
        return null;
    }
}
