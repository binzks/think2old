package org.think2.jdbc.bean;


import org.think2.jdbc.FilterType;

import java.util.List;

/**
 * Created by zhoubin on 15/11/28.
 */
public class Order {

    private List<String> keys; //排序字段名称列表

    private FilterType type; //排序方式，desc或者asc

    public List<String> getKeys() {
        return keys;
    }

    public void setKeys(List<String> keys) {
        this.keys = keys;
    }

    public FilterType getType() {
        return type;
    }

    public void setType(FilterType type) {
        this.type = type;
    }
}
