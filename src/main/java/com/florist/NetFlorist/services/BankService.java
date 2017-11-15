/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.florist.NetFlorist.services;

import com.florist.NetFlorist.model.Bank;
import com.florist.NetFlorist.repositories.BankRepository;
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service
public class BankService implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Autowired
    private BankRepository bankRepository;
    
    public Bank searchAccount(int cardNo, String cardHolder, String bankName)
    {
        return bankRepository.searchAccount(cardNo, cardHolder, bankName);
    }
    
    public int updateBankBalance(int cardNo, double balance)
    {
        return bankRepository.updateAccountBalance(cardNo, balance);
    }
}
