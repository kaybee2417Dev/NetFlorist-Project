/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.florist.NetFlorist.controller;

import com.florist.NetFlorist.model.Category;
import com.florist.NetFlorist.services.CategoryService;
import java.sql.SQLException;
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
@RequestMapping(value = "/category")
public class CategoryController{
     
    @Autowired
    private CategoryService categoryService;
    
    //========================Find All Categories======================
    @RequestMapping(value = "/findAllCategories", method = RequestMethod.GET)
    @ResponseBody
    public Object getAllRep()
    {
        return categoryService.findAllCategories();
    }
  
    //========================Save Categories======================
    @RequestMapping(value = "/saveCategory", method = RequestMethod.POST)
    @ResponseBody
    public Category saveCategory(@RequestBody Category category) throws SQLException
    {
        Category category1 = new Category();
        try{
            category1 = categoryService.saveCategory(category);
        }catch(Exception ex)
        {
            throw new SQLException(ex.getMessage());
        }
        
        return category1;         
    }
   
    //========================Delete Categories based on category Id======================
    @RequestMapping(value = "/deleteCategory/{name}", method = RequestMethod.DELETE)
    @ResponseBody
    public int deleteCategory(@PathVariable String name)
    {
        int deleteRow = 0;
        deleteRow = categoryService.deleteCategory(name);
        return deleteRow;         
    }
    
}
