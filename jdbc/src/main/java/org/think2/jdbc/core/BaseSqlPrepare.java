package org.think2.jdbc.core;

import org.think2.jdbc.SqlPrepare;
import org.think2.jdbc.bean.Column;
import org.think2.jdbc.bean.Model;
import org.think2.jdbc.bean.SqlValues;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zhoubin on 15/11/28.
 */
public class BaseSqlPrepare implements SqlPrepare {

    private final String TABLE_ALIAS_PREFIX = "t";  //表的别名

    @Override
    public SqlValues toInsert(Object instance, Model model) {

//        StringBuilder colSql = new StringBuilder();
//        StringBuilder valSql = new StringBuilder();
//        List<Object> values = new ArrayList<>();
//        Map<String, Column> columns = model.getColumns();
//        for (Column column : columns.values()) {
//            String tableAlias = getTableAlias(column.getJoinName());
//            if (!tableAlias.equals(TABLE_ALIAS_PREFIX)) {
//                continue;
//            }
//            String colName = column.getName();
//            Object value;
//            if (null != instance) {
//                value = getFieldValueByName(colName, instance);
//            } else {
//                value = dataMap.get(colName);
//            }
//
//            Field[] fields = instance.getClass().getDeclaredFields();
//            for (Field field : fields) {
//                if (field.getName().equals("a")) {
//                    field.setAccessible(true);
//                    try {
//                        Object v = field.get(instance);
//                    } catch (IllegalAccessException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//
//            // 如果是主键，并且值为null则不新增
//            if (colName.equals(pkName) && null == value) {
//                continue;
//            }
//            if (colSql.length() > 0) {
//                colSql.append(",");
//                valSql.append(",");
//            }
//            colSql.append("`").append(colName).append("`");
//            valSql.append("?");
//            values.add(value);
//        }
//        SqlValues sqlValues = new SqlValues();
//        sqlValues.setSql(colSql.toString());
//        sqlValues.setValSql(valSql.toString());
//        sqlValues.setValues(values);
//        return sqlValues;

        return null;
    }

    @Override
    public SqlValues toUpdate(Object instance) {
        return null;
    }

    @Override
    public SqlValues toDelete() {
        return null;
    }

    @Override
    public SqlValues toSelect() {
        return null;
    }
}
