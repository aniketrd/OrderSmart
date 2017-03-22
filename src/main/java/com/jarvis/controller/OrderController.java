package com.jarvis.controller;

import com.jarvis.dao.OrderDao;
import com.jarvis.data.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Aniket on 04-03-2017.
 */
@RestController
@RequestMapping(value = "/order")
public class OrderController {
    @Autowired
    private OrderDao orderDao;

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public Integer createOrder(@RequestBody Order orderDetails)
    {
        return orderDao.createOrder(orderDetails);
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Integer updateOrder(@RequestBody Order orderDetails)
    {
        return orderDao.updateOrder(orderDetails);
    }

    @RequestMapping(value = "/getDetails",method = RequestMethod.GET)
    public Order getOrderDetails(@RequestParam(name = "orderId") Integer orderId)
    {
        return orderDao.getOrderDetails(orderId);
    }

    @RequestMapping(value = "/getLiveOrders",method = RequestMethod.GET)
    public List<Order> getAllLiveOrders(@RequestParam(name = "restaurantId") Integer restaurantId)
    {
        return orderDao.getAllLiveOrders(restaurantId);
    }

}
