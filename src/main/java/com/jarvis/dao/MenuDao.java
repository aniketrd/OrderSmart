package com.jarvis.dao;

import com.jarvis.data.MenuItem;

import java.util.List;

/**
 * Created by Aniket on 01-03-2017.
 */
public interface MenuDao {

    /**
     * Get all the menu Items for a restaurant
     * @param restaurantId
     * @return List of menu Items
     */
    public List<MenuItem> getMenuList(Integer restaurantId);

    /**
     * Add a menu Item for a restaurant
     * @param menuItem
     * @return Menu Item ID
     */
    public int addMenuItem(MenuItem menuItem);

    /**
     * Delete a menu Item for a restaurant
     * @param itemId
     * @return No of entry deleted / affected
     */
    public int deleteItem(Integer itemId);

    /**
     * Update a menu Item for a restaurant
     * @param menuItem
     * @return
     */
    public int updateMenuItem(MenuItem menuItem);
}
