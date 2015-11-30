package org.think2.jdbc.expression;

import org.think2.jdbc.SqlExpression;
import org.think2.jdbc.bean.Column;
import org.think2.jdbc.type.LogicType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zhoubin on 15/11/29.
 */
public class LogicExpression implements SqlExpression {

    private LogicType logicType; //逻辑类型and或者or
    private Object[] values; //过滤值
    private SqlExpression[] sqlExpressions; //过滤条件集合

    /**
     * 逻辑表达式，and或者or，将各个过滤条件拼接成and或者or条件
     *
     * @param logicType      表达式类型and或者or
     * @param sqlExpressions 条件表达式
     */
    public LogicExpression(LogicType logicType, SqlExpression... sqlExpressions) {
        this.logicType = logicType;
        this.sqlExpressions = sqlExpressions;
        List<Object> list = new ArrayList<>();
        for (SqlExpression sqlExpression : sqlExpressions) {
            Object[] values = sqlExpression.toValues();
            for (Object value : values) {
                list.add(value);
            }
        }
        this.values = list.toArray();
    }

    @Override
    public String toSqlString(Map<String, Column> columns) {
        StringBuilder sql = new StringBuilder();
        if (LogicType.Or == logicType) {
            sql.append(" OR ");
        } else {
            sql.append(" AND ");
        }
        if (sqlExpressions.length > 1) {
            sql.append("(");
        }
        for (SqlExpression sqlExpression : sqlExpressions) {
            sql.append(sqlExpression.toSqlString(columns));
        }
        if (sqlExpressions.length > 1) {
            sql.append(")");
        }
        return sql.toString();
    }

    @Override
    public Object[] toValues() {
        return this.values;
    }
}
