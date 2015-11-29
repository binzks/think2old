package org.think2.jdbc.filter;

import org.think2.jdbc.SqlExpression;
import org.think2.jdbc.Think2JdbcException;
import org.think2.jdbc.bean.Column;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zhoubin on 15/11/28.
 * like、not like表达式
 */
public class LikeExpression implements SqlExpression {

    public enum LikeType {
        Left, Right, All
    }

    private String key; //过滤字段名称
    private Object[] values; //过滤值
    private boolean not; //true为not like false为like
    private LikeType likeType; //like类型

    /**
     * like表达式
     *
     * @param key      过滤字段名称
     * @param not      true为not like false为like
     * @param value    过滤值
     * @param likeType like类型
     */
    public LikeExpression(String key, boolean not, Object value, LikeType likeType) {
        this.key = key;
        this.not = not;
        this.likeType = likeType;
        this.values = new Object[1];
        if (LikeType.Left == likeType) {
            this.values[0] = "%" + value;
        } else if (LikeType.Right == likeType) {
            this.values[0] = value + "%";
        } else {
            this.values[0] = "%" + value + "%";
        }
    }

    @Override
    public String toSqlString(Map<String, Column> columns) {
        Column column = columns.get(this.key);
        if (null == column) {
            throw new Think2JdbcException("生成过滤条件失败，字段[" + this.key + "]不存在");
        }
        StringBuilder sql = new StringBuilder();
        sql.append("`").append(column.getJoinAlias()).append("`.`").append(column.getAlias()).append("`");
        if (not) {
            sql.append(" NOT");
        }
        sql.append(" LIKE ?");
        return sql.toString();
    }

    @Override
    public Object[] toValues() {
        return this.values;
    }
}
