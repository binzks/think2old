package org.think2.jdbc;

import org.think2.jdbc.bean.Column;
import org.think2.jdbc.core.MysqlSqlPrepare;
import org.think2.jdbc.filter.SimpleExpression;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by zhoubin on 15/11/27.
 */
public class MainTest {

    public static void main(String[] args) {
//        SqlPrepare sqlPrepare = new MysqlSqlPrepare();
//        Student student = new Student();
//
//        Map<String, Object> map = new LinkedHashMap<>();
//        map.put("id", 1);
//        map.put("name", "2");
//        map.put("code", "3");
//        sqlPrepare.toInsert(student);

        Map<String, Column> map = new HashMap<>();
        for (int i = 0; i < 3; i++) {
            Column column = new Column();
            column.setName("column" + i);
            column.setAlias("alias" + i);
            column.setJoinAlias("t" + i);
        }


    }

    public static void test(String... args) {
        String[] as = new String[2];
        // as = args;
        as[0] = "1";
        as[1] = "a";
        for (String a : as) {
            System.out.println(a);
        }
    }
}
