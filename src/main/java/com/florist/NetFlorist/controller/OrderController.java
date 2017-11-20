/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.florist.NetFlorist.controller;

import com.florist.NetFlorist.model.Orderinformation;
import com.florist.NetFlorist.services.OrderService;
import java.io.Serializable;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author User
 */
@Controller
public class OrderController implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Autowired
    private OrderService orderService;
    
     //=========================Save an Order==========================
    @RequestMapping(value = "/saveOrder", method = RequestMethod.POST)
    @ResponseBody
    public Orderinformation saveOrder(@RequestBody Orderinformation order)
    {
       Orderinformation storeOrder = new Orderinformation();
        
        try{
            storeOrder = orderService.saveOrder(order);
            if(storeOrder != null)
           {
               System.out.println("Save Order");
           }else{
               System.out.println("Save Not Order");
           }
        }catch(Exception ex){
            System.out.println("Error Message:" + ex.getMessage());
        }
       
        return storeOrder;
    }
    
     //===============Remove Order ==========================
    @RequestMapping(value = "/viewOrder", method = RequestMethod.GET)
    @ResponseBody
    public Object getAllOrders()
    {
        return orderService.getOrderdetails();
    }
    
    //=================Edit Order Status based on a orderno==========================
    @RequestMapping(value = "/editOrderStatus/{id}/{status}" , method = RequestMethod.PUT)
    @ResponseBody
    public int updateOrderStatus(@PathVariable int id, @PathVariable String status )
    {
          
        int updated = 0;
        
        try{
             updated = orderService.updateOrderStatus(id, status);
             if(updated != 0)
            {
                System.out.println("Status has been Updated");
            }else{
                System.out.println("Status not Updated");
            }
        }catch(Exception ex){
            System.out.println("Error Message:" + ex.getMessage());
        }
        
        return updated;
    }
    
    //=====================Remove Order Status==========================
    @RequestMapping(value = "/removewOrderStatus/{id}" , method = RequestMethod.DELETE)
    @ResponseBody
    public int removeOrderStatu(@PathVariable int id )
    {
        int delete = 0;
        try{
            delete = orderService.removeOrder(id);

           if(delete != 0)
           {
               System.out.println("Order has been deleted");
           }else{
               System.out.println("Order not deleted");
           }
        }catch(Exception ex)
        {
            System.out.println("Error: " + ex.getMessage());
        }
        return delete;
    }
    
    //======================View Order Status based on a orderNo==========================
    @RequestMapping(value = "/viewbyOrderNo/{orderno}", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<Orderinformation> viewByOrderNo(@PathVariable int orderno)
    {
        return orderService.findByOrderNo(orderno);
    }
    
}