/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.florist.NetFlorist.controller;

import com.florist.NetFlorist.exceptions.DataNotFoundException;
import com.florist.NetFlorist.model.Bank;
import com.florist.NetFlorist.services.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author User
 */
@Controller
@RequestMapping(value = "/bank")
public class BankController{
    
    @Autowired
    private BankService bankService;
    
  //######################## find bank account details  #####################################
    @RequestMapping(value = "/findBankAccount/{cardNo}/{cardHolder}/{bankName}", method = RequestMethod.GET)
    @ResponseBody
    public Bank findBankAccount(@PathVariable int cardNo, @PathVariable String cardHolder, @PathVariable String bankName)
    {
        Bank bank = bankService.findBankAccount(cardNo, cardHolder, bankName);
        
        if(bank == null)
        {
            throw new DataNotFoundException("Invalid banking details");
        }
        return bank;
    }
    
    
     
   //######################## Update Bank balance after deduction#####################################
    @RequestMapping(value = "/updateBankBalance/{cardNo}/{balance}", method = RequestMethod.PUT)
    @ResponseBody
    public int updateBankBalance(@PathVariable int cardNo, @PathVariable double balance)
    {
        int update = 0;
        
        try{
            update = bankService.updateBankBalance(cardNo, balance);
            
            if(update != 0)
            {
                System.out.println("Account Balance Updated");
            }else{
                 System.out.println("Account Balance Not Updated");
            }
        }catch(Exception ex)
        {
            System.out.println("Error Message: " + ex.getMessage());
        }
        return update;
    }
    
}
