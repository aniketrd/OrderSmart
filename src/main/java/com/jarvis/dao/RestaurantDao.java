package com.jarvis.dao;

import com.jarvis.data.RestaurantDetails;

/**
 * Created by Aniket on 26-02-2017.
*/

public interface RestaurantDao {
    /*
    ** Get restaurant details
    */
    public RestaurantDetails getRestaurantDetails(String userName, String password);
    public int signUp(RestaurantDetails details);
}
