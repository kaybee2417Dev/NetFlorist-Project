/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.florist.NetFlorist.controller;

import com.florist.NetFlorist.model.Category;
import com.florist.NetFlorist.services.CategoryService;
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
public class CategoryController implements Serializable
{
     private static final long serialVersionUID = 1L;
     
    @Autowired
    private CategoryService catService;
    
    //========================Find All Categories======================
    @RequestMapping(value = "/showAllCat", method = RequestMethod.GET)
    @ResponseBody
    public Object getAllRep()
    {
        return catService.viewAllCategories();
    }
    
    
    //========================SaveCategories======================
    @RequestMapping(value = "/saveCat", method = RequestMethod.POST)
    @ResponseBody
    public Category saveCategory(@RequestBody Category category)
    {
        Category cat = new Category();
        try{
            cat = catService.saveCategory(category);
                    
            if(cat != null)
            {
                System.out.println(" Category saved");
            }else
            {
                System.out.println(" Category Not saved");
            }
        }catch(Exception ex)
        {
            System.out.println("Error Message: " + ex.getMessage());
        }
        
        return cat;         
    }
   
    //========================Delete Categories based on name======================
    @RequestMapping(value = "/deteleCat/{name}", method = RequestMethod.DELETE)
    @ResponseBody
    public int deleteCategory(@PathVariable String name)
    {
        int deleteRow = 0;
        try{
            deleteRow = catService.removeCategory(name);
                    
            if(deleteRow != 0)
            {
                System.out.println("Category Deleted");
            }else
            {
                System.out.println(" Category Not Deleted");
            }
        }catch(Exception ex)
        {
            System.out.println("Error Message: " + ex.getMessage());
        }
        
        return deleteRow;         
    }
    
}
