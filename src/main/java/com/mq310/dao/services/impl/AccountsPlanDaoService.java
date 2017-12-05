package com.mq310.dao.services.impl;

import com.mq310.dao.services.IAccountsPlanService;
import java.util.List;

import com.mq310.ent.org.accounts.AccountsPlan;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class AccountsPlanDaoService extends GeneralService implements IAccountsPlanService {

    @Override
    @Transactional
    public AccountsPlan getActiveAccountsPlan() {
        return dao.get(AccountsPlan.class, "from AccountsPlan as ap "
                + "left join fetch ap.accountsPlanItems as api "
                + "left join fetch api.movements "
                + "inner join fetch api.account as ac "
                + "inner join fetch ac.accountsGroup "
                + "where ap.enable = "+true);
    }

    @Override
    @Transactional
    public List<AccountsPlan> getAccountsPlanList() {
        return dao.getAll(AccountsPlan.class);
    }

    @Override
    @Transactional
    public Integer createNewAccountsPlan(AccountsPlan plan) {
        AccountsPlan accountsPlan = plan;
        Integer id = dao.save(accountsPlan);
        accountsPlan.setId(id);
        dao.merge(accountsPlan);
        return id;
    }

    @Override
    @Transactional
    public Boolean disableAllAccountsPlan() {
        AccountsPlan plan = getActiveAccountsPlan();
        plan.setEnable(Boolean.FALSE);
        dao.merge(plan);
        return true;
    }

    @Override
    @Transactional
    public AccountsPlan getAccountsPlanById(Integer id) {
        return dao.get(AccountsPlan.class, "from AccountsPlan as ap "
                + "left join fetch ap.accountsPlanItems as api "
                + "left join fetch api.movements "
                + "inner join fetch api.account as ac "
                + "inner join fetch ac.accountsGroup "
                + "where ap.id = "+id);
    }

    @Override
    public Long getAccountsPlanCount() {
        return dao.get(Long.class, "select count(ap.id) from AccountsPlan as ap");
    }

}
