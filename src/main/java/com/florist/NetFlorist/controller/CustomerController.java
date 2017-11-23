/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.florist.NetFlorist.controller;



import com.florist.NetFlorist.exceptions.DataNotFoundException;
import com.florist.NetFlorist.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.florist.NetFlorist.services.CustomerService;
import java.io.Console;
import java.sql.SQLException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author User
 */
@RestController 
public class CustomerController  {
  
    @Autowired
    private CustomerService customerService;
 
    //========================Find All Customer/Admin======================
    @RequestMapping(method = RequestMethod.GET, value="/view")
    @ResponseBody
    public Object viewAllCustomers()
    {
       
        return customerService.findAllCustomer();
    }
    
    //========================Find Customer/Admin Based on ID======================
    @RequestMapping(value = "/findUser/{userid}" , method = RequestMethod.GET)
    @ResponseBody
    public Customer retrieveById(@PathVariable int userid)
    {
        return customerService.findCustomerbyID(userid);
    }
    
    //========================Register Customer/Admin======================
   @RequestMapping(method = RequestMethod.POST, value="/register")
   @ResponseBody
    public Customer registerCustomer(@RequestBody Customer customer) throws SQLException 
    {
        Customer cust = new Customer();
        
        try{
            cust = customerService.saveCustomer(customer);
        }catch(Exception ex)
        {
            System.out.println("Erro Side");
            throw new SQLException(ex.getMessage());
        }
       return cust;
    }
    
    //========================FLogin Using username and password======================
    @RequestMapping(value="/login/{username}/{password}", method = RequestMethod.GET)
    @ResponseBody
    public Customer Login(@PathVariable String username, @PathVariable String password)
    { 
        Customer cust = customerService.loginDetails(username, password);
       
            if(cust == null)
            {
              throw  new DataNotFoundException("User Not Found");
            }
        return cust;
    }
 
   

}
