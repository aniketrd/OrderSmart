package com.jarvis.controller;

import com.jarvis.data.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Aniket on 24-02-2017.
 */

@RestController
public class UserDetailsController {

    @RequestMapping("/")
    public User getUserDetails()
    {

        return new User("Aniket");
    }
}
