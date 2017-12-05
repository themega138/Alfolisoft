/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mq310.dao.services.impl;

import com.mq310.dao.services.IBankService;
import com.mq310.ent.org.Bank;
import java.util.SortedSet;
import java.util.TreeSet;
import org.springframework.transaction.annotation.Transactional;


public class BankDaoService extends GeneralService implements IBankService {

    @Override
    @Transactional
    public SortedSet<Bank> getBankList() {
        return new TreeSet<>(dao.getAll(Bank.class));
    }

    @Override
    @Transactional
    public Long getCountsOfBanks() {
        return dao.get(Long.class, "select count(b.id) from Bank as b");
    }

    @Override
    @Transactional
    public void saveBank(Bank bank) {
        Bank b = bank;
        b.setId(dao.save(bank));
        dao.merge(b);
    }
    
}
