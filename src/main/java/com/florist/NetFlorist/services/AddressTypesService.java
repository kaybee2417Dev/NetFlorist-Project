/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.florist.NetFlorist.services;

import com.florist.NetFlorist.repositories.AddressTypesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service
public class AddressTypesService {
    @Autowired
    private AddressTypesRepository addressTypeRepepository;
    
    public Object findAllAddressTypes()
    {
        return addressTypeRepepository.findAll();
    }
}
