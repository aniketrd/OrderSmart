package com.jarvis.dao;

import com.jarvis.data.MenuItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

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

    public List<Object> getUpdateQueryAndParams(Object updateObject, String tableName,String primaryKey , StringBuilder stringBuilder){

        Boolean firstEntry=true;
        Object primaryKeyObj=null;
        List<Object> params =new ArrayList<>();

        stringBuilder.append("UPDATE ");
        stringBuilder.append(tableName);
        try {

            for (Field field : updateObject.getClass().getDeclaredFields()) {
                if (field.getName().toLowerCase().equals(primaryKey.toLowerCase())) {
                    PropertyDescriptor pd = new PropertyDescriptor(field.getName(), updateObject.getClass());
                    primaryKeyObj = pd.getReadMethod().invoke(updateObject);
                    continue;
                }

                PropertyDescriptor pd = new PropertyDescriptor(field.getName(), MenuItem.class);
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
        }
        stringBuilder.append(" WHERE "+ primaryKey +" = ?");
        params.add(primaryKeyObj);
        return params;
    }
}
