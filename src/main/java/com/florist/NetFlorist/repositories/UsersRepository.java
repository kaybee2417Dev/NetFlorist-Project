/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.florist.NetFlorist.repositories;

import com.florist.NetFlorist.model.Users;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.webmvc.RepositoryRestController;

/**
 *
 * @author User
 */
@RepositoryRestController
public interface UsersRepository extends CrudRepository<Users, Integer> {
    
    //==============================Login Based on email And password=============================================
    @Query("SELECT u FROM Users u WHERE u.email = :email")
    public Users login(@Param("email") String email);
     
    //==============================Login Based on email And password=============================================
    @Query("SELECT u FROM Users u WHERE u.email = :email")
    public Users forgotPassword(@Param("email") String email);
    
    //==============================Update Password/Forgot Password=============================================
    @Transactional
    @Modifying
    @Query("Update Users u SET u.password = :password WHERE u.email = :email")
    public int updatePassword(@Param("password") String password, @Param("email") String email);

}
