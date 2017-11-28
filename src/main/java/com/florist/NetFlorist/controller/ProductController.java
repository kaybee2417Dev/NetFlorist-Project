/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.florist.NetFlorist.controller;

import com.florist.NetFlorist.exceptions.DataNotFoundException;
import com.florist.NetFlorist.model.Product;
import com.florist.NetFlorist.services.ProductService;
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
@RequestMapping(value = "/product")
public class ProductController{
  
    @Autowired
    private ProductService productService;
    
     //=============================Find All products==========================
    @RequestMapping(value = "/findAllProducts" , method = RequestMethod.GET)
    @ResponseBody
    public Object findAllProduct()
    {
        Object products = productService.findAllProduct();
        if(products == null)
        {
            throw new DataNotFoundException("Products Not Found...");
        }
        return products;
    }
    
    //====================Find product based on product Id==========================
    @RequestMapping(value = "/findProductById/{productId}" , method = RequestMethod.GET)
    @ResponseBody
    public Product findProductByProductID(@PathVariable int productId)
    {
        Product product = productService.findProductByProductId(productId);
        if(product == null)
        {
            throw new DataNotFoundException("Product Do Not exists...");
        }
        return product;
    }
    
    
    //============================Save Products==========================
    @RequestMapping(value = "/saveProduct" , method = RequestMethod.POST)
    @ResponseBody
    public Product saveProduct(@RequestBody Product product) {
        
       Product products = productService.saveProduct(product);
       if(products == null)
       {
           throw new DataNotFoundException("Product Not Added...");
       }
       return products;
    }
    
    //============Remove Product based on product ID==========================
    @RequestMapping(value = "/deleteProduct/{productId}" , method = RequestMethod.DELETE)
    @ResponseBody
    public int deleteProduct(@PathVariable int productId)
    {
        int deleted = productService.deleteProduct(productId);
        
        if(deleted == 0)
        {
            throw new DataNotFoundException("Product Not deleted...");
        }
       return deleted;
    }
     
    //===================Update product==========================
    @RequestMapping(value = "/updateProduct/{productId}/{name}/{category}/{price}" , method = RequestMethod.PUT)
    @ResponseBody
    public int updateProduct(@PathVariable int productId, @PathVariable String name, @PathVariable String category, @PathVariable double price )
    {
        
        int updated = productService.updateProduct(productId, name, category, price);
        if(updated != 1)
        {
           throw new DataNotFoundException("Product Not Updated...");
        }
        
        return updated;
    }
    
    //=========================Get Product based on Category name==========================
    @RequestMapping(value = "/findProductByCategory/{category}" , method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<Product> retrieveProductsByCategory(@PathVariable String category)
    {
        ArrayList<Product> listPro = productService.findProductByCategory(category);
    
        if(listPro == null)
        {
            throw new DataNotFoundException("Category name do not exist....");
        }
        return listPro;
    }
       
}
