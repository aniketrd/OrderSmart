package com.jarvis.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * Created by Aniket on 26-02-2017.
 */
public abstract class BaseDao {

    @Autowired
    private DataSource dataSource;
    protected JdbcTemplate jdbcTemplateObject;


    @Autowired
    public void setDataSource(DataSource dataSource){
        jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

}
