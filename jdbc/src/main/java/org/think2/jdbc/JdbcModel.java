package org.think2.jdbc;

import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.think2.jdbc.bean.SqlValues;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

/**
 * Created by zhoubin on 15/11/27.
 * 数据模型处理类，根据定义的model进行数据库操作，增、删、改、查
 */
public class JdbcModel {

    private static Logger logger = Logger.getLogger(JdbcModel.class);  //log4j日志对象

    private SqlPrepare sqlPrepare;
    private JdbcTemplate jdbcTemplate;

    private int insert(Object instance) {
        SqlValues sqlValues = this.sqlPrepare.toInsert(instance, null);
        String sql = sqlValues.getSql();
        List<Object> values = sqlValues.getValues();
        logger.debug("sql: " + sql + " values: " + new Gson().toJson(values));
        KeyHolder keyHolder = new GeneratedKeyHolder();
        this.jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            for (int i = 0; i < values.size(); i++) {
                ps.setObject(i + 1, values.get(i));
            }
            return ps;
        }, keyHolder);
        return keyHolder.getKey().intValue();
    }
}
