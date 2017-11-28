/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.florist.NetFlorist.controller;

import com.florist.NetFlorist.model.Address;
import com.florist.NetFlorist.services.AddressService;
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
@RequestMapping(value = "/address")
public class AddressController {
    
    @Autowired
    private AddressService addressService;
    
    //**********************Save Delivary Detatils********************************
    @RequestMapping(value = "/saveAddress", method = RequestMethod.POST)
    @ResponseBody
    public Address saveAddress(@RequestBody Address address)
    {
        Address address1 = new Address();
     
        try{
            
            address1 = addressService.saveAddress(address);
            if(address1.getAddressID() != null)
            {
                 System.out.println("Delivary saved: ");
            }else{
                 System.out.println("Delivary not saved: " );
            }
        }catch(Exception ex)
        {
            System.out.println("Delivary Error: " + ex.getMessage());
        }
        return address1;
    }
    
    //=====================Get Delivary based on order no============
    @RequestMapping(value = "/findAddressByOrderNo/{orderno}", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<Address> viewByOrderNo(@PathVariable int orderno)
    {
        return addressService.findAddressByOrderNo(orderno);
    }
}
