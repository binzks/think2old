package org.think2.jdbc.type;

import org.think2.jdbc.Think2JdbcException;
import org.think2.jdbc.filter.LogicExpression;

/**
 * Created by zhoubin on 15/11/30.
 * 数据库查询条件类型
 */
public enum LogicType {

    And("and"), Or("or");

    private String name;

    LogicType(String name) {
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
    public static LogicType valueOfString(String name) {
        if (name.equalsIgnoreCase("and")) {
            return LogicType.And;
        } else if (name.equalsIgnoreCase("or")) {
            return LogicType.Or;
        } else {
            throw new Think2JdbcException("不支持的数据库条件类型[" + name + "]");
        }
    }

}
