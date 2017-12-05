/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mq310.dao.services.impl;

import com.mq310.dao.services.IBankAccountsService;
import com.mq310.ent.org.Bank;
import com.mq310.ent.org.BankAccount;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Moises
 */
public class BankAccountDaoService extends GeneralService implements IBankAccountsService{

    @Override
    @Transactional
    public Integer createNewBankAccount(BankAccount bankAccount) {
        return dao.save(bankAccount);
    }

    @Override
    @Transactional
    public Boolean updateBankAccount(BankAccount bankAccount) {
        dao.merge(bankAccount);
        return true;
    }

    @Override
    @Transactional
    public List<BankAccount> getAllBankAccounts() {
        return dao.execute(BankAccount.class, "from BankAccount as ba inner join fetch ba.bank");
    }

    @Override
    @Transactional
    public List<Bank> getAllBanks() {
        return dao.getAll(Bank.class);
    }
    
}
