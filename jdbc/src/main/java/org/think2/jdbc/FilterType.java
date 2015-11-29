package org.think2.jdbc;

/**
 * Created by zhoubin on 15/11/28.
 * 查询过滤类型，包括排序和查询条件类型
 */
public enum FilterType {

    OrderAsc("asc"), OrderDesc("desc"),
    Eq("="), Ne("<>"), Gt(">"), Ge(">="), Lt("<"), Le("<="),
    Null("null"), NotNull("not null"), In("in"), NotIn("not in"),
    LeftLike("left like"), RightLike("right like"), Like("like"), Between("between"),
    LeftNotLike("left not like"), RightNotLike("right not like"), NotLike("not like");

    private String name;

    FilterType(String name) {
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
     * @return
     */
    public static FilterType valueOfString(String name) {
        switch (name.toLowerCase()) {
            case "desc":
                return FilterType.OrderDesc;
            case "asc":
                return FilterType.OrderAsc;
            case "=":
                return FilterType.Eq;
            case "<>":
                return FilterType.Ne;
            case "like":
                return FilterType.Like;
            case ">":
                return FilterType.Gt;
            case ">=":
                return FilterType.Ge;
            case "<":
                return FilterType.Lt;
            case "<=":
                return FilterType.Le;
            case "null":
                return FilterType.Null;
            case "not null":
                return FilterType.NotNull;
            case "in":
                return FilterType.In;
            case "not in":
                return FilterType.NotIn;
            case "between":
                return FilterType.Between;
            case "left like":
                return FilterType.LeftLike;
            case "right like":
                return FilterType.RightLike;
            case "not like":
                return FilterType.NotLike;
            case "left not like":
                return FilterType.LeftNotLike;
            case "right not like":
                return FilterType.RightNotLike;
            default:
                throw new Think2JdbcException("不支持的过滤类型[" + name + "]");
        }
    }
}
