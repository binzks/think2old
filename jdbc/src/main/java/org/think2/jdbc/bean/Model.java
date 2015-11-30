package org.think2.jdbc.bean;

import org.think2.jdbc.QueryBuilder;
import org.think2.jdbc.type.DataBaseType;

import java.util.List;
import java.util.Map;


/**
 * Created by zhoubin on 15/11/28.
 * 数据model定义
 */
public class Model {

    private DataBaseType dataBaseType;

    private String name;  //model名称

    private String table; //model对应主表表名

    private String tableAlias; //model对应表的别名

    private String pk; //主表主键名称

    private String ds; //model对应的spring数据源bean id

    private Map<String, Column> columns; //model的列定义

    private List<Join> joins;  //model的关联表信息

    private QueryBuilder queryBuilder; //model默认的查询条件

    public DataBaseType getDataBaseType() {
        return dataBaseType;
    }

    public void setDataBaseType(DataBaseType dataBaseType) {
        this.dataBaseType = dataBaseType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getTableAlias() {
        return tableAlias;
    }

    public void setTableAlias(String tableAlias) {
        this.tableAlias = tableAlias;
    }

    public String getPk() {
        return pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }

    public String getDs() {
        return ds;
    }

    public void setDs(String ds) {
        this.ds = ds;
    }

    public Map<String, Column> getColumns() {
        return columns;
    }

    public void setColumns(Map<String, Column> columns) {
        this.columns = columns;
    }

    public List<Join> getJoins() {
        return joins;
    }

    public void setJoins(List<Join> joins) {
        this.joins = joins;
    }

    public QueryBuilder getQueryBuilder() {
        return queryBuilder;
    }

    public void setQueryBuilder(QueryBuilder queryBuilder) {
        this.queryBuilder = queryBuilder;
    }
}
