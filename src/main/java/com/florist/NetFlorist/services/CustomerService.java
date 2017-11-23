/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.florist.NetFlorist.services;

import com.florist.NetFlorist.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.florist.NetFlorist.repositories.CustomerRepository;
import java.util.List;

/**
 *
 * @author User
 */
@Service
public class CustomerService{
    
    @Autowired
    private CustomerRepository customerRepository;

    public Customer saveCustomer(Customer customer)
    {
      return customerRepository.save(customer);
    }
    
    public Object findAllCustomer()
    {
        return customerRepository.findAll();
    }
    
    public Customer findCustomerbyID(int id)
    {
        return customerRepository.findOne(id);
    }
    
    public void deleteCustomer(int id)
    {
        customerRepository.delete(id);
    }
    
    public Customer loginDetails(String email, String password)
    {
        return customerRepository.login(email, password);
    }
    
    public List<Customer> findListCustomer()
    {
        return (List<Customer>) customerRepository.findAll();
    }
}
 