package com.jarvis.controller;

import com.jarvis.dao.UserDao;
import com.jarvis.data.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Aniket on 24-02-2017.
 */

@RestController
public class UserDetailsController {

    @Autowired
    private UserDao userDao;

    @RequestMapping("/getUsers")
    public List<User> getUserDetails()
    {
        List<User> users= userDao.getAllUsers();
        return users;
    }
}
