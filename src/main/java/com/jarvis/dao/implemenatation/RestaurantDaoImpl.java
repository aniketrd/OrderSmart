package com.jarvis.dao.implemenatation;

import com.jarvis.dao.BaseDao;
import com.jarvis.dao.RestaurantDao;
import com.jarvis.data.RestaurantDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.*;
import java.util.List;

import static com.jarvis.etc.Constants.*;

/**
 * Created by Aniket on 26-02-2017.
 */
public class RestaurantDaoImpl extends BaseDao implements RestaurantDao{

    private static final Logger logger = LoggerFactory.getLogger(RestaurantDaoImpl.class);

    @Override
    public RestaurantDetails getRestaurantDetails(String userName, String password) {
        logger.debug("Get restaurant details userName : " + userName);
        String sql = "select * from Restaurant where RootUserName = ?";
        List<RestaurantDetails> restaurantDetails= jdbcTemplateObject.query(sql,new Object[]{userName},new RestaurantMapper());
        logger.debug("SQL Query : "+sql);
        logger.debug("Restaurant details : " + restaurantDetails.toString());
        return restaurantDetails.get(0);
    }

    @Override
    public int signUp(RestaurantDetails details) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        logger.debug("Sign up #Restaurant details : " + details);
        String sql = "INSERT INTO smartorder.restaurant(RestaurantName,OwnerName,Address,MobileNo,CreatedTime,UpdatedTime,SubscribitonEnd,RootUserName,RootPassword)"+
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        java.util.Date data = new java.util.Date();
        jdbcTemplateObject.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {

                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1,details.getRestaurantName());
                ps.setString(2,details.getOwerName());
                ps.setString(3,details.getAddress());
                ps.setString(4,details.getMobileNo());
                ps.setTimestamp(5,new Timestamp(data.getTime()));
                ps.setTimestamp(6,new Timestamp(data.getTime()));
                ps.setTimestamp(7,Timestamp.valueOf(details.getSubscribitonEnd()));
                ps.setString(8,details.getRootUserName());
                ps.setString(9,details.getRootPassword());
                return ps;
            }
        },keyHolder);
        logger.debug("Sql query : " + sql);
        logger.debug("Restaurant Id generate : " + keyHolder.getKey().intValue());
        return keyHolder.getKey().intValue();
    }

    @Override
    public int updateDetails(RestaurantDetails details) {
  /*      if(!validateUpdateFields())
            return -1;*/
        logger.debug("Update Restaurant details : " + details);
        StringBuilder sqlBuilder = new StringBuilder();
        java.util.Date date= new java.util.Date();
        details.setUpdatedTime(new Timestamp(date.getTime()));
        List<Object> params = getUpdateQueryAndParams(details,RST_TABLE_NAME,RST_ID,sqlBuilder);
        return executeUpdateStatement(jdbcTemplateObject,sqlBuilder.toString(),params);
    }

    public class RestaurantMapper implements RowMapper<RestaurantDetails> {

        @Override
        public RestaurantDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
            RestaurantDetails restaurantDetails = new RestaurantDetails();
            restaurantDetails.setRestaurantId(rs.getInt(RST_ID));
            restaurantDetails.setRestaurantName(rs.getString(RST_NAME));
            restaurantDetails.setAddress(rs.getString(RST_ADDRESS));
            restaurantDetails.setMobileNo(rs.getString(RST_MOBILE_NO));
            restaurantDetails.setOwerName(rs.getString(RST_OWNER));
            restaurantDetails.setRootUserName(rs.getString(RST_USER_NAME));
            restaurantDetails.setCreatedTime(rs.getTimestamp(RST_CREATED_TIME));
            restaurantDetails.setUpdatedTime(rs.getTimestamp(RST_UPDATED_TIME));
            restaurantDetails.setSubscribitonEnd(rs.getTimestamp(RST_SUBSC_END_TIME).toString());
            return restaurantDetails;
        }
    }
}
