package com.jarvis.dao;

import com.jarvis.data.Order;

/**
 * Created by Aniket on 04-03-2017.
 */
public interface OrderDao {

    /**
     * Create a new Order
     * @param orderDetails
     * @return orderId
     */
    public int createOrder(Order orderDetails);

}
