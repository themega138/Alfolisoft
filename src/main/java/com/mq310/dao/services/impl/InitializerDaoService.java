/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mq310.dao.services.impl;

import com.mq310.dao.services.IInitializerService;
import com.mq310.ent.org.Bank;
import com.mq310.ent.org.accounts.AccountsGroup;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class InitializerDaoService extends GeneralService implements IInitializerService{

    @Override
    public void saveAccountsGroup(AccountsGroup accountsGroup) {
        Integer id = dao.save(accountsGroup);
        accountsGroup.setId(id);
        dao.merge(accountsGroup);
    }

    @Override
    public void saveBank(Bank bank) {
        Integer id = dao.save(bank);
        bank.setId(id);
        dao.merge(bank);
    }

    @Override
    public Long getCountOfBanks() {
        Long count = dao.get(Long.class, "select count(b.id) from Bank as b");
        return count;
    }

    @Override
    public Long getCountOfAccountsGroup() {
        Long count = dao.get(Long.class, "select count(ag.id) from AccountsGroup as ag");
        return count;
    }
    
}
