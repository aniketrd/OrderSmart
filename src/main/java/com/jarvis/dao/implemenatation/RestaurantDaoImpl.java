package com.jarvis.dao.implemenatation;

import com.jarvis.dao.BaseDao;
import com.jarvis.dao.RestaurantDao;
import com.jarvis.data.RestaurantDetails;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Aniket on 26-02-2017.
 */
public class RestaurantDaoImpl extends BaseDao implements RestaurantDao {

    @Override
    public RestaurantDetails getRestaurantDetails(String userName, String password) {
        String sql = "select * from Restaurant where RootUserName = '"+userName+"'";
        List<RestaurantDetails> restaurantDetails= jdbcTemplateObject.query(sql,new RestaurantMapper());
        return restaurantDetails.get(0);
    }

    public class RestaurantMapper implements RowMapper<RestaurantDetails> {

        @Override
        public RestaurantDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
            RestaurantDetails restaurantDetails = new RestaurantDetails();
            restaurantDetails.setRestaurantId(rs.getInt("RestaurantId"));
            restaurantDetails.setRestaurantName(rs.getString("RestaurantName"));
            restaurantDetails.setAddress(rs.getString("Address"));
            restaurantDetails.setMobileNo(rs.getString("MobileNo"));
            restaurantDetails.setOwerName(rs.getString("OwnerName"));
            restaurantDetails.setRootUserName(rs.getString("RootUserName"));
            restaurantDetails.setCreatedTime(rs.getTimestamp("CreatedTime"));
            restaurantDetails.setUpdatedTime(rs.getTimestamp("UpdatedTime"));
            restaurantDetails.setSubscribitonEnd(rs.getTimestamp("SubscribitonEnd"));
            return restaurantDetails;
        }
    }
}
