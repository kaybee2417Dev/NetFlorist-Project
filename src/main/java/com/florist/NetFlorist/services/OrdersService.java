/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.florist.NetFlorist.services;

import com.florist.NetFlorist.model.Orders;
import com.florist.NetFlorist.repositories.OrdersRepository;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service
public class OrdersService {
    
    @Autowired
    private OrdersRepository orderRepository;
    
    public Orders saveOrders(Orders orders)
    {
   
        return orderRepository.save(orders);
    }
    
    public Object findAllOrders()
    {
        return orderRepository.findAll();
    }
    
    public int updateOrdersStatus(int orderID, String statusStatus)
    {
        return orderRepository.updateOrderStatus(orderID, statusStatus);
    }
    
   public int deleteOrders(int orderNo)
   {
       return orderRepository.deleteOrder(orderNo);
   }
     
   public ArrayList<Orders> findOrdersByOrderNo(int orderNo)
   {
       return orderRepository.findOrderByOrderNo(orderNo);
   }
}
