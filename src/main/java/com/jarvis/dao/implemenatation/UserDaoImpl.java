package com.jarvis.dao.implemenatation;

import com.jarvis.dao.UserDao;
import com.jarvis.data.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Aniket on 26-02-2017.
 */
public class UserDaoImpl implements UserDao {


    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;


    @Autowired
    public void setDataSource(DataSource dataSource){
        jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    @Override
    public List<User> getAllUsers() {

        String sql = "select * from user";
        List<User> users = jdbcTemplateObject.query(sql,new UserMapper());

        return users;
    }

    public class UserMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setName(rs.getString("name"));
            return user;
        }
    }

}
