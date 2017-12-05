package com.mq310.dao.services;

import com.mq310.ent.org.accounts.Account;
import com.mq310.ent.org.accounts.AccountsGroup;
import java.util.List;
import java.util.SortedSet;
import javafx.scene.control.TreeItem;

public interface IAccountsGroupService {

        public abstract TreeItem<Object> getAccountTreeItemRoot();
    
	public abstract AccountsGroup saveAccountGroup(AccountsGroup accountsGroup);

	public abstract Account saveAccount(Account account);

	public abstract SortedSet<AccountsGroup> getAccountsGroupList();
        
        public abstract List<Account> getAccountList();
        
        public abstract Long getAccountsCount();
}