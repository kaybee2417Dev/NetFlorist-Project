/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.florist.NetFlorist.services;

import com.florist.NetFlorist.model.Delivary;
import com.florist.NetFlorist.repositories.DelivaryRepository;
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service
public class DelivaryService implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Autowired
    private DelivaryRepository delivaryRepository;
    
    public Delivary saveDelivary(Delivary delivary)
    {
        return delivaryRepository.save(delivary);
    }  
    
    public Object viewByOrderNo(int orderNo)
    {
        return delivaryRepository.viewByOrderNo(orderNo);
    }
}
