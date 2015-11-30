package org.think2.jdbc.expression;

import org.think2.jdbc.SqlExpression;
import org.think2.jdbc.bean.Column;
import org.think2.jdbc.utils.FieldUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zhoubin on 15/11/30.
 * 修改sql表达式，用于生成修改的sql语句和字段值数组
 */
public class UpdateExpression implements SqlExpression {

    private Object[] values;    //修改的字段值数组
    private Object instance;    //修改对象
    private String table;       //修改表名
    private String tableAlias;  //修改表别名，用于判断列是否属于新增表
    private String key;  //修改关键字段名称，根据关键字的值修改记录，一般为主键
    private boolean isHandleNull; //是否将值为null的数据修改到数据库，true修改，false不修改

    /**
     * 初始化修改表达式
     *
     * @param table        修改表名
     * @param tableAlias   修改表别名，用于判断列是否属于新增表
     * @param key          修改关键字段名称，根据关键字的值修改记录，一般为主键
     * @param instance     修改对象
     * @param isHandleNull 是否将值为null的数据修改到数据库，true修改，false不修改
     */
    public UpdateExpression(String table, String tableAlias, String key, Object instance, boolean isHandleNull) {
        this.table = table;
        this.tableAlias = tableAlias;
        this.key = key;
        this.instance = instance;
        this.isHandleNull = isHandleNull;
    }

    @Override
    public String toSqlString(Map<String, Column> columns) {
        StringBuilder colSql = new StringBuilder();
        List<Object> values = new ArrayList<>();
        for (Column column : columns.values()) {
            if (!column.getTableAlias().equals(tableAlias)) {
                continue;
            }
            String colName = column.getName();
            // 关键字段不处理
            if (colName.equals(key)) {
                continue;
            }
            Object value = FieldUtils.getFieldValue(instance, colName);
            if (null != value || this.isHandleNull) {
                if (colSql.length() > 0) {
                    colSql.append(",");
                }
                colSql.append("`").append(colName).append("`=?");
                values.add(value);
            }
        }
        Object keyValue = FieldUtils.getFieldValue(instance, key);
        values.add(keyValue);
        StringBuilder sql = new StringBuilder();
        sql.append("update `").append(table).append("` set ").append(colSql.toString());
        sql.append(" where ").append(key).append(" = ?");
        this.values = values.toArray();
        return sql.toString();
    }

    @Override
    public Object[] toValues() {
        return values;
    }

}
