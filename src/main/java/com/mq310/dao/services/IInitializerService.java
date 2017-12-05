/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mq310.dao.services;

import com.mq310.ent.org.Bank;
import com.mq310.ent.org.accounts.AccountsGroup;

/**
 *
 * @author Moises
 */
public interface IInitializerService {
    
    public abstract void saveAccountsGroup(AccountsGroup accountsGroup);
    
    public abstract void saveBank(Bank bank);
    
    public abstract Long getCountOfBanks();
    
    public abstract Long getCountOfAccountsGroup();
    
}
