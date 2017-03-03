package com.jarvis.controller;

import com.jarvis.dao.RestaurantDao;
import com.jarvis.data.RestaurantDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Aniket on 26-02-2017.
 */
@RestController
@RequestMapping(value = "/restaurant")
public class RestaurantDetailsController {
    @Autowired
    private RestaurantDao restaurantDao;

    /**
     * Get details of a restaurnt using authentication
     * @param userName
     * @param password
     * @return
     */
    @RequestMapping(value = "/getDetails",method = RequestMethod.GET)
    public RestaurantDetails getRestaurantDetails(@RequestParam(name = "userName") String userName , @RequestParam(name = "password") String password )
    {
        RestaurantDetails details= restaurantDao.getRestaurantDetails(userName,password);
        return details;
    }

    /**
     * Sign Up Service
     * @param details
     * @return
     */
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public int signUp(@RequestBody RestaurantDetails details)
    {
        int restaurantId = restaurantDao.signUp(details);
        return restaurantId;
    }

    /**
     * Update Restaurant details service
     * @param details
     * @return
     */
    @RequestMapping(value = "/updateDetails", method = RequestMethod.POST)
    public int updateDetails(@RequestBody RestaurantDetails details)
    {
        return restaurantDao.updateDetails(details);
    }
}
