/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.florist.NetFlorist.controller;

import com.florist.NetFlorist.exceptions.DataNotFoundException;
import com.florist.NetFlorist.services.AddressTypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author User
 */
@RestController
@RequestMapping(value = "/addressTypes")
public class AddressTypesController {
     @Autowired
    private AddressTypesService addressTypeService;
    
    //=======================Get All Address Types ==========================
    @RequestMapping(value="/findAllAddressTypes", method = RequestMethod.GET)
    @ResponseBody
    public Object findAllDeliveryTypes()
    {
        Object object = addressTypeService.findAllAddressTypes();
        if(object == null)
        {
            throw new DataNotFoundException("Address Types Not Found...");
        }
        return object;
    }
}
