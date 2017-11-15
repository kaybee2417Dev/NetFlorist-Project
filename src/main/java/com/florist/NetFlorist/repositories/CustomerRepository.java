/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.florist.NetFlorist.repositories;

import com.florist.NetFlorist.model.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.webmvc.RepositoryRestController;
/**
 *
 * @author User
 */
@RepositoryRestController
public interface CustomerRepository extends CrudRepository<Customer, Integer>{
    
    //public Customer signin(String email);
    //@Query("SELECT c FROM Person c WHERE LOWER(p.lastName) = LOWER(:lastName)")
   
    //public List<Person> find(@Param("lastName") String lastName);
     @Query("SELECT c FROM Customer c WHERE c.email = :email AND c.password = :password")
     public Customer login(@Param("email") String email, @Param("password") String password);
}
