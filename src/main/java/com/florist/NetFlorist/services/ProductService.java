/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.florist.NetFlorist.services;

import com.florist.NetFlorist.model.Product;
import com.florist.NetFlorist.repositories.ProductRepository;
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
    
    
    public Object findAllProduct()
    {
        return productRepository.findAll();
    }
    
    public Product saveProduct(Product product)
    {
        return productRepository.save(product);
    }
    
    public int deleteProduct(int productId)
    {
        return productRepository.deleteProduct(productId);
    }

    public int updateProduct(int productId, String name, String cat, double price)
    {
        
        return productRepository.updateProduct(productId, name, cat, price);
    }
    
    public ArrayList<Product> findProductByCategory(String category)
    {
        return productRepository.findProductByCategory(category);
    }
    
    public Product findProductByProductId(int productId)
    {
        return productRepository.findOne(productId);
    }
    
}
