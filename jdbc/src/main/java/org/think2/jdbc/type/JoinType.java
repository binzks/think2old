package org.think2.jdbc.type;

import org.think2.jdbc.Think2JdbcException;

/**
 * Created by zhoubin on 15/11/30.
 * 数据库关联类型定义
 */
public enum JoinType {

    Left("left join"), Inner("inner join"), Right("right join"), Full("full join");

    private String name;

    JoinType(String name) {
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
    public static JoinType valueOfString(String name) {
        if (name.equalsIgnoreCase("left join")) {
            return JoinType.Left;
        } else if (name.equalsIgnoreCase("inner join")) {
            return JoinType.Inner;
        } else if (name.equalsIgnoreCase("right join")) {
            return JoinType.Right;
        } else if (name.equalsIgnoreCase("full join")) {
            return JoinType.Full;
        } else {
            throw new Think2JdbcException("不支持的数据库关联类型[" + name + "]");
        }
    }

}
