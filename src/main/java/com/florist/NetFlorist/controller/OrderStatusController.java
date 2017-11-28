/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.florist.NetFlorist.controller;

import com.florist.NetFlorist.exceptions.DataNotFoundException;
import com.florist.NetFlorist.services.OrderStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author User
 */
@Controller
@RequestMapping(value = "/orderStatus")
public class OrderStatusController {
    
    @Autowired
    private OrderStatusService orderStatusService;
    
    
    @RequestMapping(method = RequestMethod.GET, value = "/findAllOrderStatus")
    @ResponseBody
    public Object viewOrderStatus()
    {
        Object object =  orderStatusService.findAllOrderStatus();
        if(object == null)
        {
            throw new DataNotFoundException("Status Names Not Found...");
        }
        return object;
    }
    
    
}
