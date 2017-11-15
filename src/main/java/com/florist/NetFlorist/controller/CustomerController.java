/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.florist.NetFlorist.controller;


import com.florist.NetFlorist.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.florist.NetFlorist.services.CustomerService;
import java.io.Serializable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author User
 */
@Controller 
public class CustomerController implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Autowired
    private CustomerService customerService;
 
    @RequestMapping(method = RequestMethod.GET, value="/view")
    @ResponseBody
    public Object viewAllCustomers()
    {
       
        return customerService.findAllCustomer();
    }
    
    //========================Register Customer/Admin======================
   @RequestMapping(method = RequestMethod.POST, value="/customer/register")
   @ResponseBody
    public String registerCustomer(@RequestBody Customer customer)
    {
    
       String message = " ";
   
       Customer cust = new Customer();
       try{
           System.out.println("Show Role" + customer.getRole());
            cust = customerService.saveCustomer(customer);
            if(cust.getCID()!= 0)
            {
                System.out.println("Customer Registered!!!");
                message = "Customer Registered!!!";
                
            }else{

                System.out.println("Customer Not Registered!!!");
                message = "Customer Not Registered!!!" + cust.getCID();
             
            }

       }
       catch(Exception ex)
       {
           System.out.println("exception catching!!!" + ex.getMessage());
          
       }
       return message;
    }
    
    //========================FLogin Using username and password======================
    @RequestMapping(value="/login/{username}/{password}", method = RequestMethod.GET)
    @ResponseBody
    public Customer Login(@PathVariable String username, @PathVariable String password)
    {
        Customer cust = new Customer();
        try{
           
            cust = customerService.loginDetaisl(username, password);
            if(cust != null)
            {
                if(cust.getRole().equalsIgnoreCase("customer"))
                {
                    System.out.println("Customer");
                 }else{
                    System.out.println("Admin");
                }

            }else{
                System.out.println("Not Registered");
 
            }

        }catch(Exception ex)
        {
            System.out.println("Error: " + ex.getMessage());
        }
        return cust;
    }
 
    //========================Redirect to home page======================
    @RequestMapping(value = "/")
    public String homePage()
    {
        return"home";
    }
    

}
