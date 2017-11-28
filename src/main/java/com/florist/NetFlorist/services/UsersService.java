/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.florist.NetFlorist.services;

import com.florist.NetFlorist.model.Users;
import com.florist.NetFlorist.repositories.UsersRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service
public class UsersService {
    
    @Autowired
    private UsersRepository usersRepository;
    
    public Users saveUser(Users users)
    {
      PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); 
      String hashedPassword = passwordEncoder.encode(users.getPassword());
      users.setPassword(hashedPassword);
      return usersRepository.save(users);
    }
    
//    public Object findAllUsers()
//    {
//        return usersRepository.findAll();
//    }
    
    public Users findUserByUserId(int userId)
    {
        return usersRepository.findOne(userId);
    }
    
    public void deleteUser(int userId)
    {
        usersRepository.delete(userId);
    }
    
    public Users userLogin(String email)
    {
      /*  PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); 
        String hashedPassword = passwordEncoder.encode(password);
        System.out.println("encoded: " + hashedPassword);*/
        return usersRepository.login(email);
    }
    
    public Users findUserByEmail(String email)
    {
        return usersRepository.forgotPassword(email);
    }
    
    
//    public List<Users> findListOfUses()
//    {
//        return (List<Users>) usersRepository.findAll();
//    }
   
    public int updatePassword(String password, String email)
    {
        
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); 
        String hashedPassword = passwordEncoder.encode(password);
        System.out.println("password: " + hashedPassword);
        return usersRepository.updatePassword(hashedPassword, email);
    }
    
}
