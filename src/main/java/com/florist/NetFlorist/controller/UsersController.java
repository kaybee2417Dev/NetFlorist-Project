/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.florist.NetFlorist.controller;

import com.florist.NetFlorist.exceptions.DataNotFoundException;
import com.florist.NetFlorist.model.Users;
import com.florist.NetFlorist.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author User
 */
@RestController
@RequestMapping(value = "/user")
public class UsersController {
    
    @Autowired
    private UsersService usersService;
    
   /* //========================Find All Customer/Admin======================
    @RequestMapping(method = RequestMethod.GET, value="/view")
    @ResponseBody
    public Object findAllUsers()
    {
        return usersService.findAllUsers();
    }*/
    
    //========================Find User Based on ID======================
    @RequestMapping(value = "/findUserByUserId/{userId}" , method = RequestMethod.GET)
    @ResponseBody
    public Users findUsersByUserId(@PathVariable int userId)
    {
        return usersService.findUserByUserId(userId);
    }
    
    //========================Register  User======================
   @RequestMapping(method = RequestMethod.POST, value="/register")
   @ResponseBody
    public Users registerUsers(@RequestBody Users users) throws Exception 
    {
        Users user = new Users();
        try{
            user = usersService.saveUser(users);
        }catch(Exception ex)
        {
          throw new Exception(ex.getMessage());
        }
       return user;
    }
    
    //========================User Login Using username and password======================
    @RequestMapping(value="/login/{username}/{password}", method = RequestMethod.GET)
    @ResponseBody
    public Users userLogin(@PathVariable String username, @PathVariable String password)
    { 
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); 
        Users user = usersService.userLogin(username);
        if(user != null)
        {
           if(passwordEncoder.matches(password, user.getPassword()))
           {
              return user;
           }else
           {
               throw new DataNotFoundException("User Password Incorrect...");
           }
        }else{
            throw new DataNotFoundException("Username Don't exist...");
        }
    }
    
    //========================Find user based on email======================
    @RequestMapping(value="/forgotPassword/{username}", method = RequestMethod.GET)
    @ResponseBody
    public Users userFogotPassword(@PathVariable String username)
    { 
        Users user = usersService.findUserByEmail(username);
        if(user != null)
        {
            return user;
        }else
        {
            throw new DataNotFoundException("Username Don't exist...");
        }
    }
    
    //========================Update Password based on email======================
    @RequestMapping(value="/newPassword/{password}/{email}", method = RequestMethod.PUT)
    @ResponseBody
    public int newPassword(@PathVariable String password,@PathVariable String email)
    { 
        int update = usersService.updatePassword(password, email);
        if(update != 0)
        {
            return update;
        }else
        {
            throw new DataNotFoundException("Username Don't exist...");
        }
    }
}
