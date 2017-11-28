/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.florist.NetFlorist.services;

import com.florist.NetFlorist.model.Bank;
import com.florist.NetFlorist.repositories.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service
public class BankService{
    
    @Autowired
    private BankRepository bankRepository;
    
    public Bank findBankAccount(int cardNo, String cardHolder, String bankName)
    {
        return bankRepository.findBankAccount(cardNo, cardHolder, bankName);
    }
    
    public int updateBankBalance(int cardNo, double balance)
    {
        return bankRepository.updateBankBalance(cardNo, balance);
    }
}
