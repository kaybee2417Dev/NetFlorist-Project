/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.florist.NetFlorist.services;

import com.florist.NetFlorist.model.Orderinformation;
import com.florist.NetFlorist.repositories.OrderRepository;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    
    public Orderinformation saveOrder(Orderinformation order)
    {
        System.out.println(order.getDate());
         return orderRepository.save(order);
    }
    
    public Object getOrderdetails()
    {
        return orderRepository.findAll();
    }
    
    public int updateOrderStatus(int orderID, String status)
    {
        return orderRepository.updateStatusName(orderID, status);
    }
    
   public int removeOrder(int orderNo)
   {
       return orderRepository.removeOrder(orderNo);
   }
     
   public ArrayList<Orderinformation> findByOrderNo(int orderNo)
   {
       return orderRepository.viewByOrderNo(orderNo);
   }
}
