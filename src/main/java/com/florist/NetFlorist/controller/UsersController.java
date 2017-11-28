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
  
    //========================Find User Based on ID======================
    @RequestMapping(value = "/findUserByUserId/{userId}" , method = RequestMethod.GET)
    @ResponseBody
    public Users findUsersByUserId(@PathVariable int userId)
    {
        Users user = usersService.findUserByUserId(userId);
        if(user == null)
        {
            throw new DataNotFoundException("User do not Exists...");
        }
        return user;
    }
    
    //========================Register  User======================
   @RequestMapping(method = RequestMethod.POST, value="/register")
   @ResponseBody
    public Users registerUsers(@RequestBody Users users) throws Exception 
    {
        Users  user = usersService.saveUser(users);
        if(user != null)
        {
            return user;
        }else{
            throw new DataNotFoundException("User Not Registered...Email already in-User!!!");
        }     
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
              throw new DataNotFoundException("User Password Incorrect...Try Again!!!");
           }
        }else{
             throw new DataNotFoundException("User Email Don't exist...Try Again!!!");
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
            throw new DataNotFoundException("User email Don't exist...Try Again");
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
            throw new DataNotFoundException("User Password Not Updated...");
        }
    }
}
