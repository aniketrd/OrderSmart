package com.jarvis.controller;

import com.jarvis.dao.MenuDao;
import com.jarvis.data.MenuItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Aniket on 01-03-2017.
 */
@RestController
@RequestMapping(value = "/menu")
public class MenuController {

    @Autowired
    private MenuDao menuDao;

    @RequestMapping(value = "/getMenuList")
    public List<MenuItem> getMenuList(@RequestParam(name = "restaurantId") Integer restaurantId){
        return  menuDao.getMenuList(restaurantId);

    }

    @RequestMapping(value = "/addMenuItem", method = RequestMethod.POST,consumes = "application/json")
    @ResponseBody
    public int createMenuItem(@RequestBody MenuItem menuItem){
        return  menuDao.addMenuItem(menuItem);

    }
}
