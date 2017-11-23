/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.florist.NetFlorist.services;

import com.florist.NetFlorist.model.Product;
import com.florist.NetFlorist.repositories.ProductRepository;
import java.io.Serializable;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service
public class ProductService{
    @Autowired
    private ProductRepository productRepository;
    
    
    public Object viewAllProduct()
    {
        return productRepository.findAll();
    }
    
    public Product saveProduct(Product product)
    {
        Product pro = new Product();
        pro.setPID(product.getPID());
        pro.setName(product.getName());
        pro.setPrice(product.getPrice());
        pro.setCategory(product.getCategory());
       pro.setImage(product.getImage());
        return productRepository.save(product);
    }
    
    public int deleteProduct(String name)
    {
        return productRepository.deleteProduct(name);
    }

    public int updateProduct(int pid, String name, String cat, double price)
    {
        
        return productRepository.upddateProduct(pid, name, cat, price);
    }
    
    public ArrayList<Product> viewCategory(String category)
    {
        return productRepository.viewByCategory(category);
    }
    
    public Product getProductsById(int pid)
    {
        return productRepository.findOne(pid);
    }
    
}
