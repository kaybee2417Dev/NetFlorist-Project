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
public class ProductController implements Serializable  {
    private static final long serialVersionUID = 1L;
    
    @Autowired
    private ProductService productService;
    
     //=============================Find All products==========================
    @RequestMapping(value = "/findAllProducts" , method = RequestMethod.GET)
    @ResponseBody
    public Object findAllProduct()
    {
        return productService.findAllProduct();
    }
    
    //====================find product based on product Id==========================
    @RequestMapping(value = "/findProductById/{productId}" , method = RequestMethod.GET)
    @ResponseBody
    public Product findProductByProductID(@PathVariable int productId)
    {
        return productService.findProductByProductId(productId);
    }
    
    
    //============================Save Products==========================
    @RequestMapping(value = "/saveProduct" , method = RequestMethod.POST)
    @ResponseBody
    public Product saveProduct(@RequestBody Product product) throws Exception
    {
        Product product1 = new Product();
        try{
            product1 = productService.saveProduct(product);
        }catch(Exception ex)
        {
           throw new Exception(ex.getMessage());
        }
       return product1;  
    }
    
     //============Remove Product based on product ID==========================
    @RequestMapping(value = "/deleteProduct/{productId}" , method = RequestMethod.DELETE)
    @ResponseBody
    public int deleteProduct(@PathVariable int productId)
    {
        int deleted = 0;
        deleted = productService.deleteProduct(productId);
        return deleted;
    }
     
    //===================Update product==========================
    @RequestMapping(value = "/updateProduct/{productId}/{name}/{category}/{price}" , method = RequestMethod.PUT)
    @ResponseBody
    public int updateProduct(@PathVariable int productId, @PathVariable String name, @PathVariable String category, @PathVariable double price )
    {
        int updated = 0;
        
        updated = productService.updateProduct(productId, name, category, price);
        
        return updated;
    }
    
    //=========================Get Product based on Category name==========================
    @RequestMapping(value = "/findProductByCategory/{category}" , method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<Product> retrieveProductsByCategory(@PathVariable String category)
    {
        ArrayList<Product> listPro = productService.findProductByCategory(category);
    
        return listPro;
    }
       
}
