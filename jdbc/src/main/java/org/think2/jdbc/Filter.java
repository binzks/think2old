package org.think2.jdbc;

import org.think2.jdbc.filter.*;

/**
 * Created by zhoubin on 15/11/28.
 */
public class Filter {

    /**
     * 拼接and过滤条件
     *
     * @param filters 过滤条件 eq、like等
     * @return 过滤表达式
     */
    public static SqlExpression and(SimpleExpression... filters) {
        return new LogicExpression(LogicExpression.LogicType.And, filters);
    }

    /**
     * 拼接or过滤条件
     *
     * @param filters 过滤条件 eq、like等
     * @return 过滤表达式
     */
    public static SqlExpression or(SimpleExpression... filters) {
        return new LogicExpression(LogicExpression.LogicType.Or, filters);
    }

    /**
     * 等于过滤条件
     *
     * @param key   过滤字段名
     * @param value 过滤值
     * @return 过滤表达式
     */
    public static SqlExpression eq(String key, Object value) {
        return new SimpleExpression(key, "=", value);
    }

    /**
     * 不等于过滤条件
     *
     * @param key   过滤字段名
     * @param value 过滤值
     * @return 过滤表达式
     */
    public static SqlExpression ne(String key, Object value) {
        return new SimpleExpression(key, "<>", value);
    }

    /**
     * 大于过滤条件
     *
     * @param key   过滤字段名
     * @param value 过滤值
     * @return 过滤表达式
     */
    public static SqlExpression gt(String key, Object value) {
        return new SimpleExpression(key, ">", value);
    }

    /**
     * 大于等于过滤条件
     *
     * @param key   过滤字段名
     * @param value 过滤值
     * @return 过滤表达式
     */
    public static SqlExpression ge(String key, Object value) {
        return new SimpleExpression(key, ">=", value);
    }

    /**
     * 小于过滤条件
     *
     * @param key   过滤字段名
     * @param value 过滤值
     * @return 过滤表达式
     */
    public static SqlExpression lt(String key, Object value) {
        return new SimpleExpression(key, "<", value);
    }

    /**
     * 小于等于过滤条件
     *
     * @param key   过滤字段名
     * @param value 过滤值
     * @return 过滤表达式
     */
    public static SqlExpression le(String key, Object value) {
        return new SimpleExpression(key, "<=", value);
    }

    /**
     * null过滤条件
     *
     * @param key 过滤字段名
     * @return 过滤表达式
     */
    public static SqlExpression isNull(String key) {
        return new NullExpression(key, false);
    }

    /**
     * not null过滤条件
     *
     * @param key 过滤字段名
     * @return 过滤表达式
     */
    public static SqlExpression notNull(String key) {
        return new NullExpression(key, true);
    }

    /**
     * in过滤条件
     *
     * @param key    过滤字段名
     * @param values 过滤值
     * @return 过滤表达式
     */
    public static SqlExpression in(String key, Object... values) {
        return new InExpression(key, false, values);
    }

    /**
     * not in过滤条件
     *
     * @param key    过滤字段名
     * @param values 过滤值
     * @return 过滤表达式
     */
    public static SqlExpression notIn(String key, Object... values) {
        return new InExpression(key, true, values);
    }

    /**
     * between过滤条件
     *
     * @param key   过滤字段名
     * @param begin 开始值
     * @param end   结束值
     * @return 过滤表达式
     */
    public static SqlExpression between(String key, Object begin, Object end) {
        return new BetweenExpression(key, begin, end);
    }

    /**
     * like过滤条件，过滤值两边都加上%过滤
     *
     * @param key   过滤字段名
     * @param value 过滤值
     * @return 过滤表达式
     */
    public static SqlExpression like(String key, Object value) {
        return new LikeExpression(key, false, value, LikeExpression.LikeType.All);
    }

    /**
     * 左like过滤条件，过滤值左边都加上%过滤
     *
     * @param key   过滤字段名
     * @param value 过滤值
     * @return 过滤表达式
     */
    public static SqlExpression lLike(String key, Object value) {
        return new LikeExpression(key, false, value, LikeExpression.LikeType.Left);
    }

    /**
     * 右like过滤条件，过滤值右边都加上%过滤
     *
     * @param key   过滤字段名
     * @param value 过滤值
     * @return 过滤表达式
     */
    public static SqlExpression rLike(String key, Object value) {
        return new LikeExpression(key, false, value, LikeExpression.LikeType.Right);
    }

    /**
     * not like过滤条件，过滤值两边都加上%过滤
     *
     * @param key   过滤字段名
     * @param value 过滤值
     * @return 过滤表达式
     */
    public static SqlExpression notLike(String key, Object value) {
        return new LikeExpression(key, true, value, LikeExpression.LikeType.All);
    }

    /**
     * 左not like过滤条件，过滤值左边都加上%过滤
     *
     * @param key   过滤字段名
     * @param value 过滤值
     * @return 过滤表达式
     */
    public static SqlExpression lNotLike(String key, Object value) {
        return new LikeExpression(key, true, value, LikeExpression.LikeType.Left);
    }

    /**
     * 右not like过滤条件，过滤值右边都加上%过滤
     *
     * @param key   过滤字段名
     * @param value 过滤值
     * @return 过滤表达式
     */
    public static SqlExpression rNotLike(String key, Object value) {
        return new LikeExpression(key, true, value, LikeExpression.LikeType.Right);
    }

}
