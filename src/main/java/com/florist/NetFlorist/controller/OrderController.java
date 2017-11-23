/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.florist.NetFlorist.controller;

import com.florist.NetFlorist.model.Orderinformation;
import com.florist.NetFlorist.services.DelivaryService;
import com.florist.NetFlorist.services.OrderService;
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
public class OrderController{
   
    @Autowired
    private OrderService orderService;
    
    @Autowired
    private DelivaryService delivaryService;
    
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
        updated = orderService.updateOrderStatus(id, status);
        /*try{
             updated = orderService.updateOrderStatus(id, status);
             if(updated != 0)
            {
                System.out.println("Status has been Updated");
            }else{
                System.out.println("Status not Updated");
            }
        }catch(Exception ex){
            System.out.println("Error Message:" + ex.getMessage());
        }*/
        
        return updated;
    }
    
    //=====================Remove Order Status==========================
    @RequestMapping(value = "/removewOrderStatus/{orderNo}" , method = RequestMethod.DELETE)
    @ResponseBody
    public int removeOrderStatu(@PathVariable int orderNo )
    {
        int delete = 0;
        
        delete = orderService.removeOrder(orderNo);

        if(delete != 0)
        {
             delivaryService.deleteDelivary(orderNo);
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