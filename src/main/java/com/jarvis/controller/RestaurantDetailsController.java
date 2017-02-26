package com.jarvis.controller;

import com.jarvis.dao.RestaurantDao;
import com.jarvis.data.RestaurantDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Aniket on 26-02-2017.
 */
@RestController
public class RestaurantDetailsController {
    @Autowired
    private RestaurantDao restaurantDao;

    @RequestMapping(value = "/getRestaurantDetails")
    public RestaurantDetails getRestaurantDetails(@RequestParam(name = "userName") String userName , @RequestParam(name = "password") String password )
    {
        RestaurantDetails details= restaurantDao.getRestaurantDetails(userName,password);
        return details;
    }
}
