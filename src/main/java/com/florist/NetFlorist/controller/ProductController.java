/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.florist.NetFlorist.controller;

import com.florist.NetFlorist.exceptions.DataNotFoundException;
import com.florist.NetFlorist.model.Product;
import com.florist.NetFlorist.services.ProductService;
import java.io.Serializable;
import java.sql.SQLException;
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
    public Product saveProducts(@RequestBody Product product) throws SQLException
    {
        Product prod = new Product();
        try{
            prod = productService.saveProduct(product);
        }catch(Exception ex)
        {
            System.out.println("Erro Side");
            throw new SQLException(ex.getMessage());
        }
       return prod;  
    }
    
     //============Remove Product based on name==========================
    @RequestMapping(value = "/deleteproduct/{name}" , method = RequestMethod.DELETE)
    @ResponseBody
    public int removeProduct(@PathVariable String name)
    {
        int deleted = 0;
            deleted = productService.deleteProduct(name);
        return deleted;
    }
     
     //===================Update product==========================
    @RequestMapping(value = "/update/{id}/{name}/{category}/{price}" , method = RequestMethod.PUT)
    @ResponseBody
    public int removeProduct(@PathVariable int id, @PathVariable String name, @PathVariable String category, @PathVariable double price )
    {
        int updated = 0;
        
            updated = productService.updateProduct(id, name, category, price);
        
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
