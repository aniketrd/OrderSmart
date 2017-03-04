package com.jarvis.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;

import javax.sql.DataSource;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aniket on 26-02-2017.
 */
public abstract class BaseDao {

    private static final Logger logger = LoggerFactory.getLogger(BaseDao.class);
    @Autowired
    private DataSource dataSource;
    protected JdbcTemplate jdbcTemplateObject;


    @Autowired
    public void setDataSource(DataSource dataSource){
        jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    public List<Object> getUpdateQueryAndParams(Object updateObject, String tableName,String primaryKey , StringBuilder stringBuilder){

        Boolean firstEntry=true;
        Object primaryKeyObj=null;
        List<Object> params =new ArrayList<>();

        logger.debug("Create UPDATE SQL : Table Name :" +tableName + "\n Primary Key : " + primaryKey +"\n Update Object : "+updateObject);
        stringBuilder.append("UPDATE ");
        stringBuilder.append(tableName);
        try {

            for (Field field : updateObject.getClass().getDeclaredFields()) {
                if (field.getName().toLowerCase().equals(primaryKey.toLowerCase())) {
                    PropertyDescriptor pd = new PropertyDescriptor(field.getName(), updateObject.getClass());
                    primaryKeyObj = pd.getReadMethod().invoke(updateObject);
                    continue;
                }

                PropertyDescriptor pd = new PropertyDescriptor(field.getName(), updateObject.getClass());
                Object valueObj = pd.getReadMethod().invoke(updateObject);
                if (valueObj != null) {
                    params.add(valueObj);
                    if (!firstEntry) {
                        stringBuilder.append(" , " + field.getName() + " = ? ");
                    } else {
                        firstEntry = false;
                        stringBuilder.append(" SET " + field.getName() + " = ? ");
                    }
                }


            }
        }catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString());
        }
        stringBuilder.append(" WHERE "+ primaryKey +" = ?");
        params.add(primaryKeyObj);
        logger.debug("Update SQL generated : "+ stringBuilder.toString());
        logger.debug("Update Params : "+params);
        return params;
    }


    public Boolean validateUpdateFields(){
        return true;
    }

    protected int executeUpdateStatement(JdbcTemplate jdbcTemplateObject, String sql, List<Object> params) {
        return jdbcTemplateObject.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(sql);
                for (int i=0; i<params.size();i++)
                {
                    ps.setObject(i+1,params.get(i));
                }
                return ps;
            }
        });
    }
}


