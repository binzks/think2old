package org.think2.jdbc.expression;

import org.think2.jdbc.SqlExpression;
import org.think2.jdbc.bean.Column;
import org.think2.jdbc.bean.Model;
import org.think2.jdbc.utils.FieldUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zhoubin on 15/11/30.
 * 新增sql表达式，用于生成新增的sql语句和字段值数组
 */
public class InsertExpression implements SqlExpression {

    private Object[] values;    //新增的字段值数组
    private Object instance;    //新增对象
    private String table;       //新增表名
    private String tableAlias;  //新增表别名，用于判断列是否属于新增表

    /***
     * 初始化新增表达式
     *
     * @param table      新增表名
     * @param tableAlias 新增表别名，用于判断列是否属于新增表
     * @param instance   新增对象
     */
    public InsertExpression(String table, String tableAlias, Object instance) {
        this.table = table;
        this.tableAlias = tableAlias;
        this.instance = instance;
    }

    @Override
    public String toSqlString(Map<String, Column> columns) {
        StringBuilder colSql = new StringBuilder();
        StringBuilder valSql = new StringBuilder();
        List<Object> values = new ArrayList<>();
        for (Column column : columns.values()) {
            if (!column.getTableAlias().equals(tableAlias)) {
                continue;
            }
            String colName = column.getName();
            Object value = FieldUtils.getFieldValue(instance, colName);
            // 如果是值为null则不新增
            if (null == value) {
                continue;
            }
            if (colSql.length() > 0) {
                colSql.append(",");
                valSql.append(",");
            }
            colSql.append("`").append(colName).append("`");
            valSql.append("?");
            values.add(value);
        }
        StringBuilder sql = new StringBuilder();
        sql.append("insert into `").append(table).append("` (").append(colSql.toString());
        sql.append(") values (").append(colSql.toString()).append(")");
        this.values = values.toArray();
        return sql.toString();
    }

    @Override
    public Object[] toValues() {
        return values;
    }
}
