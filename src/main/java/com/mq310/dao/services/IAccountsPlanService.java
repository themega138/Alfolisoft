package com.mq310.dao.services;

import java.util.List;

import com.mq310.ent.org.accounts.AccountsPlan;

public interface IAccountsPlanService {
	
	public abstract AccountsPlan getActiveAccountsPlan();
	
        public abstract Integer createNewAccountsPlan(AccountsPlan plan);
	
	public abstract List<AccountsPlan> getAccountsPlanList();
        
        public abstract Boolean disableAllAccountsPlan();
        
        public abstract AccountsPlan getAccountsPlanById(Integer id);
        
        public abstract Long getAccountsPlanCount();

}
