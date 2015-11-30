package org.think2.jdbc.type;

import org.think2.jdbc.Think2JdbcException;
import org.think2.jdbc.filter.LikeExpression;

/**
 * Created by zhoubin on 15/11/30.
 * 数据库like条件类型定义
 */
public enum LikeType {

    Left("left like"), Right("right like"), Full("full like");

    private String name;

    LikeType(String name) {
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
    public static LikeType valueOfString(String name) {
        if (name.equalsIgnoreCase("left like")) {
            return LikeType.Left;
        } else if (name.equalsIgnoreCase("right like")) {
            return LikeType.Right;
        } else if (name.equalsIgnoreCase("full like")) {
            return LikeType.Full;
        } else {
            throw new Think2JdbcException("不支持的数据库like类型[" + name + "]");
        }
    }

}
