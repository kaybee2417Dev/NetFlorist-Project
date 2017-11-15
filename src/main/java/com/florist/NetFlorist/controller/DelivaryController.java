/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.florist.NetFlorist.controller;

import com.florist.NetFlorist.model.Delivary;
import com.florist.NetFlorist.services.DelivaryService;
import java.io.Serializable;
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
public class DelivaryController implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Autowired
    private DelivaryService delivaryService;
    
    //********************************Save Delivary Detatils@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    @RequestMapping(value = "/saveDelivary", method = RequestMethod.POST)
    @ResponseBody
    public Delivary saveDelivary(@RequestBody Delivary delivary)
    {
        Delivary deliver = new Delivary();
     
        try{
            
            deliver = delivaryService.saveDelivary(delivary);
            if(deliver.getDelivaryID() != null)
            {
                 System.out.println("Delivary saved: ");
            }else{
                 System.out.println("Delivary not saved: " );
            }
        }catch(Exception ex)
        {
            System.out.println("Delivary Error: " + ex.getMessage());
        }
        return deliver;
    }
    
    //=====================Get Delivary based on order no============
    @RequestMapping(value = "/viewdelivary/{orderno}", method = RequestMethod.GET)
    @ResponseBody
    public Object viewByOrderNo(@PathVariable int orderno)
    {
        return delivaryService.viewByOrderNo(orderno);
    }
    
}
