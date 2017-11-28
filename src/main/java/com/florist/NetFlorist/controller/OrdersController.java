/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.florist.NetFlorist.controller;

import com.florist.NetFlorist.model.Orders;
import com.florist.NetFlorist.services.AddressService;
import com.florist.NetFlorist.services.OrdersService;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(value = "/orders")
public class OrdersController {
    
    @Autowired
    private OrdersService ordersServices;
    
     @Autowired
    private AddressService addressService;
    
     //=========================Save an Order==========================
    @RequestMapping(value = "/saveOrders", method = RequestMethod.POST)
    @ResponseBody
    public Orders saveOrder(@RequestBody Orders orders) throws Exception
    {
       Orders order1 = new Orders();
        
        try{
            order1 = ordersServices.saveOrders(orders);
           if(order1 != null)
           {
               System.out.println("Save Order");
               
           }else{
               System.out.println("Save Not Order");
           }
        }catch(Exception ex){
          throw new Exception(ex.getMessage());
        }
       
        return order1;
    }
    
     //===============Remove Order ==========================
    @RequestMapping(value = "/findAllOrders", method = RequestMethod.GET)
    @ResponseBody
    public Object findAllOrders()
    {
        return ordersServices.findAllOrders();
    }
    
    //=================Update Order Status based on a orderno==========================
    @RequestMapping(value = "/updateOrderStatus/{orderId}/{orderStatus}", method = RequestMethod.PUT)
    @ResponseBody
    public int updateOrderStatus(@PathVariable int orderId, @PathVariable String orderStatus )
    {
          
        int updated = 0;
        updated = ordersServices.updateOrdersStatus(orderId, orderStatus);
        return updated;
    }
    
    //=====================Remove Order Status==========================
    @RequestMapping(value = "/deleteOrders/{orderNo}" , method = RequestMethod.DELETE)
    @ResponseBody
    public int removeOrderStatu(@PathVariable int orderNo )
    {
        int delete = 0;
        
        delete = ordersServices.deleteOrders(orderNo);

        if(delete != 0)
        {
             addressService.deleteDelivary(orderNo);
        }
        return delete;
    }
    
    //======================View Order Status based on a orderNo==========================
    @RequestMapping(value = "/findByOrderNo/{orderno}", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<Orders> viewByOrderNo(@PathVariable int orderno)
    {
        return ordersServices.findOrdersByOrderNo(orderno);
    }
    
}
