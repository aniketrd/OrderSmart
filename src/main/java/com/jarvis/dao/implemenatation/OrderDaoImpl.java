package com.jarvis.dao.implemenatation;

import com.jarvis.dao.BaseDao;
import com.jarvis.dao.OrderDao;
import com.jarvis.data.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.*;
import java.util.List;

import static com.jarvis.etc.Constants.*;

/**
 * Created by Aniket on 04-03-2017.
 */
public class OrderDaoImpl extends BaseDao implements OrderDao {

    private static final Logger logger = LoggerFactory.getLogger(OrderDaoImpl.class);

    @Override
    public Integer createOrder(Order orderDetails) {
        String sql = "INSERT INTO `smartorder`.`ordertable`\n" +
                "(OrderType,GuestCount,CustomerId,TableId,RestaurantId,CreatedTime,CreatedBy)" +
                " VALUES(?,?,?,?,?,?,?)" ;
        java.util.Date date = new java.util.Date();
        logger.debug("Creating a new order : "+orderDetails);
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
        logger.debug("Order created successfully (Order Id)"+keyHolder.getKey());
        return (int) keyHolder.getKey().longValue();

    }

    @Override
    public Integer updateOrder(Order orderDetails) {
        Integer orderId =orderDetails.getOrderId();
        if(orderId==null || orderId <= 0)
            return -1;

        StringBuilder builder = new StringBuilder();
        logger.debug("Update Order Details : " + orderDetails);
        List<Object> params = getUpdateQueryAndParams(orderDetails,ORDER_TABLE_NAME,ORDER_ID,builder);
        String sql =builder.toString();
        return executeUpdateStatement(jdbcTemplateObject,sql,params);

    }
}
