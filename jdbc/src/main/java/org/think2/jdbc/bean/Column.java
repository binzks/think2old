package org.think2.jdbc.bean;

/**
 * Created by zhoubin on 15/9/6.
 * model列的定义，对应数据库的字段
 */
public class Column {

    private String name; //列的名称，对应数据库表的字段名称

    private String joinName; //关联名称，如果列是关联表的字段，则填写关联的名称获取关联表别名

    private String alias; //列的别名，用于数据库查询的时候使用别名

    private String joinAlias; //列所对应的真正数据库表在关联中的别名

    private String expression; //表达式，表达式例子count(?)，生成字段的时候将字段替换?

    private String comment; //字段注释

    private String type;  //字段类型 varchar int等数据库类型

    private int size;  //字段长度

    private Boolean nullAble; //字段是否可空，true可以，false不可空

    private String defaultValue;  //字段的默认值

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJoinName() {
        return joinName;
    }

    public void setJoinName(String joinName) {
        this.joinName = joinName;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getJoinAlias() {
        return joinAlias;
    }

    public void setJoinAlias(String joinAlias) {
        this.joinAlias = joinAlias;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Boolean getNullAble() {
        return nullAble;
    }

    public void setNullAble(Boolean nullAble) {
        this.nullAble = nullAble;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }
}
