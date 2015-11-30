package org.think2.jdbc;

import org.think2.jdbc.bean.Column;

import java.util.Map;

/**
 * Created by zhoubin on 15/11/28.
 */
public interface SqlExpression {

    /**
     * 将表达式转换成sql语句
     *
     * @param columns 数据model对象的列集合
     * @return sql语句
     */
    String toSqlString(Map<String, Column> columns);

    /**
     * 获取sql语句所需要的过滤值
     *
     * @return 过滤值
     */
    Object[] toValues();
}
