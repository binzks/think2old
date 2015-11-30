package org.think2.jdbc.type;

import org.think2.jdbc.Think2JdbcException;

/**
 * Created by zhoubin on 15/9/6.
 * 数据库类型定义
 */
public enum DataBaseType {
    Mysql("mysql"), Sqlite("sqlite"), SqlServer("sqlServer"), Oracle("oracle");

    private String name;

    DataBaseType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    /***
     * 根据字符串获取枚举值
     *
     * @param name 枚举值字符串
     * @return 数据库类型
     */
    public static DataBaseType valueOfString(String name) {
        if (name.equalsIgnoreCase("mysql")) {
            return DataBaseType.Mysql;
        } else if (name.equalsIgnoreCase("sqlite")) {
            return DataBaseType.Sqlite;
        } else if (name.equalsIgnoreCase("sqlServer")) {
            return DataBaseType.SqlServer;
        } else if (name.equalsIgnoreCase("oracle")) {
            return DataBaseType.Oracle;
        } else {
            throw new Think2JdbcException("不支持的数据库类型[" + name + "]");
        }
    }
}