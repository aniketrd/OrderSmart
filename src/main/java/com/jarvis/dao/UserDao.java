package com.jarvis.dao;

import com.jarvis.data.User;
import java.util.List;

/**
 * Created by Aniket on 26-02-2017.
 */

public interface UserDao {

    /*
    * * Retrive all the users
    */
    public List<User> getAllUsers();

}
