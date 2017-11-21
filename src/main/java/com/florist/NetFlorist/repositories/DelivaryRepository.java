/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.florist.NetFlorist.repositories;

import com.florist.NetFlorist.model.Delivary;
import java.util.ArrayList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.webmvc.RepositoryRestController;

/**
 *
 * @author User
 */
@RepositoryRestController
public interface DelivaryRepository extends CrudRepository<Delivary, Integer>{
   
    @Query("SELECT d FROM Delivary d WHERE d.orderno = :orderno")
    public ArrayList<Delivary> viewByOrderNo(@Param("orderno") int orderNo);
}
