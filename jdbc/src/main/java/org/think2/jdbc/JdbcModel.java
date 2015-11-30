package org.think2.jdbc;

import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.think2.jdbc.bean.Model;
import org.think2.jdbc.bean.SqlValues;
import org.think2.jdbc.expression.InsertExpression;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

/**
 * Created by zhoubin on 15/11/27.
 * 数据模型处理类，根据定义的model进行数据库操作，增、删、改、查
 */
public class JdbcModel {

    private static Logger logger = Logger.getLogger(JdbcModel.class);  //log4j日志对象

    private Model model;
    private SqlPrepare sqlPrepare;
    private JdbcTemplate jdbcTemplate;

    private int insert(Object instance) {
        InsertExpression insertExpression = new InsertExpression(model.getTable(), model.getTableAlias(), instance);
        String sql = insertExpression.toSqlString(model.getColumns());
        Object[] values = insertExpression.toValues();
        logger.debug("sql: " + sql + " values: " + new Gson().toJson(values));
        KeyHolder keyHolder = new GeneratedKeyHolder();
        this.jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            for (int i = 0; i < values.length; i++) {
                ps.setObject(i + 1, values[i]);
            }
            return ps;
        }, keyHolder);
        return keyHolder.getKey().intValue();
    }
}
