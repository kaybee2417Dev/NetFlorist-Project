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
    private CatagoryRepository categoryRepository;
    
    public Object findAllCategories()
    {
        return categoryRepository.findAll();
    }
    
    public Category saveCategory(Category category)
    {
        return categoryRepository.save(category);
    }
    
    public int deleteCategory(String name)
    {
        return categoryRepository.deleteCategory(name);
    }
}
