package com.mq310.dao.services.impl;

import com.mq310.dao.services.IAccountsGroupService;
import com.mq310.ent.EntityCreator;
import com.mq310.ent.EntityStatus;
import com.mq310.ent.EntityVisibility;
import com.mq310.ent.org.accounts.Account;
import com.mq310.ent.org.accounts.AccountsGroup;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import javafx.scene.control.TreeItem;
import org.springframework.transaction.annotation.Transactional;

public class AccountsGroupDaoService extends GeneralService implements IAccountsGroupService {

    @Override
    @Transactional
    public AccountsGroup saveAccountGroup(AccountsGroup accountsGroup) {
        AccountsGroup group = accountsGroup;
        group.setId(dao.save(group));
        return dao.merge(group);
    }

    @Override
    @Transactional
    public Account saveAccount(Account account) {
        Integer id = dao.save(account);
        Account ac = account;
        ac.setId(id);
        return dao.merge(ac);
    }

    @Override
    @Transactional
    public SortedSet<AccountsGroup> getAccountsGroupList() {
        return new TreeSet<>(dao.execute(AccountsGroup.class, "from AccountsGroup as ag "
                + "left join fetch ag.accounts as a "
                + "where a.status = '"+EntityStatus.ENABLE+"' "
                + "and a.visibility = '"+EntityVisibility.SHOW+"' "
                + "and a.creator = '"+EntityCreator.USER+"'"));
    }

    @Override
    @Transactional
    public TreeItem<Object> getAccountTreeItemRoot() {
        TreeItem<Object> rootItem = new TreeItem<>("Cuentas Activas", loadIcon("/images/accountsPlan_16x16.png"));
        rootItem.setExpanded(true);
        TreeItem<Object> item;
        for (AccountsGroup accountsGroup : dao.getAll(AccountsGroup.class)) {
            item = new TreeItem<>(accountsGroup, loadIcon("/Images/accounts_16x16.png"));
            TreeItem<Object> itemAccount;
            for (Account account : ((AccountsGroup) item.getValue()).getAccounts()) {
                itemAccount = new TreeItem<>(account);
                item.getChildren().add(itemAccount);
            }
            rootItem.getChildren().add(item);
        }
        rootItem.getChildren().stream().forEach((treeItem) -> {
            treeItem.setExpanded(true);
        });
        return rootItem;
    }

    @Override
    @Transactional
    public List<Account> getAccountList() {
        return dao.execute(Account.class, "from Account as a "
                + "inner join fetch a.accountsGroup "
                + "where a.status = '"+EntityStatus.ENABLE+"' "
                + "and a.visibility = '"+EntityVisibility.SHOW+"' "
                + "and a.creator = '"+EntityCreator.USER+"'");
    }

    @Override
    @Transactional
    public Long getAccountsCount() {
        return dao.get(Long.class, "select count(a.id) from Account as a");
    }

}
