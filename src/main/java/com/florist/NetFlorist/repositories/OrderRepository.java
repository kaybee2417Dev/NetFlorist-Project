/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.florist.NetFlorist.repositories;

import com.florist.NetFlorist.model.Orderinformation;
import java.util.ArrayList;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import javax.transaction.Transactional;
import org.springframework.data.rest.webmvc.RepositoryRestController;

/**
 *
 * @author User
 */
@RepositoryRestController
public interface OrderRepository extends CrudRepository<Orderinformation, Integer>{
    
    @Transactional
    @Modifying
    @Query("Update Orderinformation o SET o.orderstatus = :orderstatus WHERE o.orderID = :orderID")
    public int updateStatusName(@Param("orderID") int orderID, @Param("orderstatus") String status);

    @Transactional
    @Modifying
    @Query("Delete FROM Orderinformation o WHERE o.orderno = :orderno")
    public int removeOrder(@Param("orderno") int orderNo);
     
    @Query("SELECT o FROM Orderinformation o WHERE o.orderno = :orderno")
    public ArrayList<Orderinformation> viewByOrderNo(@Param("orderno") int orderNo);
}
