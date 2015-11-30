package org.think2.jdbc.bean;

/**
 * Created by zhoubin on 15/9/6.
 * model列的定义，对应数据库的字段
 */
public class Column {

    private String name; //列的名称，对应数据库表的字段名称

    private String alias; //列的别名，用于关联查询重复的时候定义别名，如果没有定义，则别名与name一致

    private String tableAlias; //列所属的表的别名

    private String expression; //表达式，表达式例子count(?)，生成字段的时候将字段替换?

    private String comment; //字段注释

    private String type;  //字段数据库类型

    private String size;  //字段长度

    private Boolean nullAble; //字段是否可空，true可以，false不可空

    private String defaultValue;  //字段的默认值

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getTableAlias() {
        return tableAlias;
    }

    public void setTableAlias(String tableAlias) {
        this.tableAlias = tableAlias;
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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
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
