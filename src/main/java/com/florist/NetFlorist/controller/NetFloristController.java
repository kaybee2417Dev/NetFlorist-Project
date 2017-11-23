package com.florist.NetFlorist.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 *
 * @author User
 */
@Controller
public class NetFloristController {
    
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String homePage()
    {
        return "home";
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage()
    {
        return "login";
    }
    
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerPage()
    {
        return "register";
    }
    
    @RequestMapping(value = "/adminHomePage", method = RequestMethod.GET)
    public String adminPage()
    {
        return "adminHomePage";
    }
    
    @RequestMapping(value = "/addProduct", method = RequestMethod.GET)
    public String addProduct()
    {
        return "addProduct";
    }
    
    @RequestMapping(value = "/updateProduct", method = RequestMethod.GET)
    public String updateProduct()
    {
        return "updateProduct";
    }
    
    @RequestMapping(value = "/addCategory", method = RequestMethod.GET)
    public String addCategory()
    {
        return "addCategory";
    }
    
    @RequestMapping(value = "/registerAdmin", method = RequestMethod.GET)
    public String registerAdmin()
    {
        return "registerAdmin";
    }
    
    @RequestMapping(value = "/viewOrders", method = RequestMethod.GET)
    public String viewOrders()
    {
        return "viewOrders";
    }
    
    //##############################Customer Site#########################
    @RequestMapping(value = "/customerHomePage", method = RequestMethod.GET)
    public String customerHomePage()
    {
        return "customerHomePage";
    }
    
    @RequestMapping(value = "/customerOrders", method = RequestMethod.GET)
    public String customerOrders()
    {
        return "customerOrders";
    }
}
