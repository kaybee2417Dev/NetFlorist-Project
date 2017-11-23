/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.florist.NetFlorist.services;

import com.florist.NetFlorist.model.Category;
import com.florist.NetFlorist.repositories.CatagoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service
public class CategoryService{
    @Autowired
    private CatagoryRepository catRep;
    
    public Object viewAllCategories()
    {
        return catRep.findAll();
    }
    
    public Category saveCategory(Category category)
    {
        return catRep.save(category);
    }
    
    public int removeCategory(String name)
    {
        return catRep.deleteCategory(name);
    }
    
}
