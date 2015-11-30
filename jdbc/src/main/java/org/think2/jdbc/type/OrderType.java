package org.think2.jdbc.type;

import org.think2.jdbc.Think2JdbcException;

/**
 * Created by zhoubin on 15/11/30.
 * 数据库排序类型定义
 */
public enum OrderType {

    Desc("desc"), Asc("asc");

    private String name;

    OrderType(String name) {
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
    public static OrderType valueOfString(String name) {
        if (name.equalsIgnoreCase("desc")) {
            return OrderType.Desc;
        } else if (name.equalsIgnoreCase("asc")) {
            return OrderType.Asc;
        } else {
            throw new Think2JdbcException("不支持的数据库排序类型[" + name + "]");
        }
    }

}
