package org.think2.jdbc.bean;

import org.think2.jdbc.type.JoinType;

/**
 * Created by zhoubin on 15/11/30.
 * 数据model关联定义，生成关联条件 table alias alias.key type joinAlias.joinKey
 */
public class Join {

    private String table;  //关联表名

    private String alias;  //关联表的别名

    private String key;    //关联表的字段名

    private JoinType type; //关联类型

    private String joinAlias;//关联主表的别名

    private String joinKey;  //关联主表字段名

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public JoinType getType() {
        return type;
    }

    public void setType(JoinType type) {
        this.type = type;
    }

    public String getJoinAlias() {
        return joinAlias;
    }

    public void setJoinAlias(String joinAlias) {
        this.joinAlias = joinAlias;
    }

    public String getJoinKey() {
        return joinKey;
    }

    public void setJoinKey(String joinKey) {
        this.joinKey = joinKey;
    }
}
