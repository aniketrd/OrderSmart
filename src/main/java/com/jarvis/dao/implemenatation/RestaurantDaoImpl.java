package com.jarvis.dao.implemenatation;

import com.jarvis.dao.BaseDao;
import com.jarvis.dao.RestaurantDao;
import com.jarvis.data.RestaurantDetails;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static com.jarvis.etc.Constants.*;

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
            restaurantDetails.setRestaurantId(rs.getInt(RST_ID));
            restaurantDetails.setRestaurantName(rs.getString(RST_NAME));
            restaurantDetails.setAddress(rs.getString(RST_ADDRESS));
            restaurantDetails.setMobileNo(rs.getString(RST_MOBILE_NO));
            restaurantDetails.setOwerName(rs.getString(RST_OWNER));
            restaurantDetails.setRootUserName(rs.getString(RST_USER_NAME));
            restaurantDetails.setCreatedTime(rs.getTimestamp(RST_CREATED_TIME));
            restaurantDetails.setUpdatedTime(rs.getTimestamp(RST_UPDATED_TIME));
            restaurantDetails.setSubscribitonEnd(rs.getTimestamp(RST_SUBSC_END_TIME));
            return restaurantDetails;
        }
    }
}
