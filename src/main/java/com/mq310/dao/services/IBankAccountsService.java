/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mq310.dao.services;

import com.mq310.ent.org.Bank;
import com.mq310.ent.org.BankAccount;
import java.util.List;

/**
 *
 * @author Moises
 */
public interface IBankAccountsService {
    
    public abstract Integer createNewBankAccount(BankAccount bankAccount);
    
    public abstract Boolean updateBankAccount(BankAccount bankAccount);
    
    public abstract List<BankAccount> getAllBankAccounts();
    
    public abstract List<Bank> getAllBanks();
    
    public abstract Long getBankAccountsCount();
}
