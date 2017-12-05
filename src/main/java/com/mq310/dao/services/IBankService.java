/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mq310.dao.services;

import com.mq310.ent.org.Bank;
import java.util.SortedSet;

/**
 *
 * @author Moises
 */
public interface IBankService {
    
    public abstract SortedSet<Bank> getBankList();
    
    public abstract Long getCountsOfBanks();
    
    public abstract void saveBank(Bank bank);
    
}
