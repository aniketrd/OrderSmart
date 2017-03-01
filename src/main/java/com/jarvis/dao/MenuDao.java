package com.jarvis.dao;

import com.jarvis.data.MenuItem;

import java.util.List;

/**
 * Created by Aniket on 01-03-2017.
 */
public interface MenuDao {

    public List<MenuItem> getMenuList(Integer restaurantId);
    public int addMenuItem(MenuItem menuItem);

}
