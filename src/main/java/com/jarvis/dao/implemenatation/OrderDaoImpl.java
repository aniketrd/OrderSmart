package com.jarvis.dao.implemenatation;

import com.jarvis.dao.BaseDao;
import com.jarvis.dao.OrderDao;
import com.jarvis.data.Order;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.*;

/**
 * Created by Aniket on 04-03-2017.
 */
public class OrderDaoImpl extends BaseDao implements OrderDao {
    @Override
    public int createOrder(Order orderDetails) {
        String sql = "INSERT INTO `smartorder`.`ordertable`\n" +
                "(OrderType,GuestCount,CustomerId,TableId,RestaurantId,CreatedTime,CreatedBy)" +
                " VALUES(?,?,?,?,?,?,?)" ;
        java.util.Date date = new java.util.Date();
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplateObject.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, orderDetails.getOrderType());
                ps.setInt(2, orderDetails.getGuestCount());
                ps.setInt(3, orderDetails.getCustomerId());
                ps.setInt(4, orderDetails.getTableId());
                ps.setInt(5, orderDetails.getRestaurantId());
                ps.setTimestamp(6, new Timestamp(date.getTime()));
                ps.setString(7, orderDetails.getCreatedBy());
                return ps;
            }
        },keyHolder);
        return (int) keyHolder.getKey().longValue();

    }
}
