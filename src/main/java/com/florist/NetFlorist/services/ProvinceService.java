/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.florist.NetFlorist.services;

import com.florist.NetFlorist.repositories.ProvinceRepositiory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service
public class ProvinceService{
    @Autowired
    private ProvinceRepositiory provinceRepository;
    public Object findAllProvinces()
    {
        return provinceRepository.findAll();
    }
    
}
