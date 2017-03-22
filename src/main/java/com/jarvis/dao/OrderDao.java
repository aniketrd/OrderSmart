package com.jarvis.dao;

import com.jarvis.data.Order;

import java.util.List;

/**
 * Created by Aniket on 04-03-2017.
 */
public interface OrderDao {

    /**
     * Create a new Order
     * @param orderDetails
     * @return orderId
     */
    public Integer createOrder(Order orderDetails);

    public Integer updateOrder(Order orderDetails);

    public Order getOrderDetails(Integer orderId);

    public List<Order> getAllLiveOrders(Integer restaurantId);
}
