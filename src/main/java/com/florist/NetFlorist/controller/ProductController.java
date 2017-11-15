/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.florist.NetFlorist.controller;

import com.florist.NetFlorist.model.Product;
import com.florist.NetFlorist.services.ProductService;
import java.io.Serializable;
import java.util.ArrayList;
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
public class ProductController implements Serializable  {
    private static final long serialVersionUID = 1L;
    
    @Autowired
    private ProductService productService;
    
     //=============================Finf All prodicts==========================
    @RequestMapping(value = "/viewproduct" , method = RequestMethod.GET)
    @ResponseBody
    public Object retrieveAllProduct()
    {
        return productService.viewAllProduct();
    }
    
    //====================find product based on id==========================
    @RequestMapping(value = "/findProduct/{pid}" , method = RequestMethod.GET)
    @ResponseBody
    public Product retrieveById(@PathVariable int pid)
    {
        return productService.getProductsById(pid);
    }
    
    
    //============================Save Products==========================
    @RequestMapping(value = "/saveproduct" , method = RequestMethod.POST)
    @ResponseBody
    public Product saveProducts(@RequestBody Product product)
    {
        Product prod = new Product();
        System.out.println("name:  " + product.getName());
        System.out.println("image:  " + product.getImage());
        try{
            
            prod = productService.saveProduct(product);
            if(prod != null)
            {
                System.out.println(" product saved");
            }else{
                  System.out.println(" product Not saved");
            }
        }catch(Exception ex)
        {
            System.out.println("Error Message: " + ex.getMessage());
        }
    
      return prod;  
    }
    
     //============Remove Product based on name==========================
    @RequestMapping(value = "/deleteproduct/{name}" , method = RequestMethod.DELETE)
    @ResponseBody
    public int removeProduct(@PathVariable String name)
    {
        int deleted = 0;
        
        try{
            deleted = productService.deleteProduct(name);
            if(deleted != 0)
            {
                System.out.println("Product has been Deleted");
            }else{
                System.out.println("Product not Deleted");
            }
        }catch(Exception ex){
             System.out.println("Error Message: " + ex.getMessage());
        }
       
        return deleted;
    }
     
     //===================Update product==========================
    @RequestMapping(value = "/update/{id}/{name}/{category}/{price}" , method = RequestMethod.PUT)
    @ResponseBody
    public int removeProduct(@PathVariable int id, @PathVariable String name, @PathVariable String category, @PathVariable double price )
    {
        int updated = 0;
        
        try{
            updated = productService.updateProduct(id, name, category, price);
            if(updated != 0)
            {
                System.out.println("Product has been Updated");
            }else{
                System.out.println("Product not Updated");
            }
        }catch(Exception ex){
            System.out.println("Error Message: " + ex.getMessage());
        }
        
        return updated;
    }
    
    //=========================Get Product based on Category name==========================
    @RequestMapping(value = "/viewByCategory/{category}" , method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<Product> retrieveAllProduct(@PathVariable String category)
    {
        ArrayList<Product> listPro = productService.viewCategory(category);
    
        return listPro;
    }
       
}
