package com.jarvis.dao;

import com.jarvis.data.RestaurantDetails;

/**
 * Created by Aniket on 26-02-2017.
*/

public interface RestaurantDao {

    /**
     *  Get restaurant details for a restaurant
     * @param userName
     * @param password
     * @return
     */
    public RestaurantDetails getRestaurantDetails(String userName, String password);

    /**
     * Sign Up for a new restaurant
     * @param details
     * @return
     */
    public int signUp(RestaurantDetails details);

    /**
     *  Update Restaurant Details
     * @param details
     * @return
     */
    public int updateDetails(RestaurantDetails details);
}
