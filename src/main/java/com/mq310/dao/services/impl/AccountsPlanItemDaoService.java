/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mq310.dao.services.impl;

import com.mq310.dao.services.IAccountsPlanItemService;
import com.mq310.ent.org.accounts.AccountsPlanItem;
import java.util.SortedSet;
import java.util.TreeSet;
import javax.transaction.Transactional;

/**
 *
 * @author Moises
 */
public class AccountsPlanItemDaoService extends GeneralService implements IAccountsPlanItemService{

    @Override
    @Transactional
    public AccountsPlanItem getAccountsPlanItemById(Integer id) {
        return dao.get(AccountsPlanItem.class, id);
    }

    @Override
    @Transactional
    public SortedSet<AccountsPlanItem> getAccountsPlanItemList() {
        return new TreeSet<>(dao.getAll(AccountsPlanItem.class));
    }
    
}
