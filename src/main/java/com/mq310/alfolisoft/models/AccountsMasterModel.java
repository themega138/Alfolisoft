/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mq310.alfolisoft.models;

import com.mq310.ent.org.accounts.Account;
import com.mq310.ent.org.accounts.AccountsGroup;
import java.util.List;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AccountsMasterModel extends GeneralModel {

    private ObjectProperty<Account> accountProperty = new SimpleObjectProperty<Account>();
    private ObjectProperty<AccountsGroup> accountsGroupProperty = new SimpleObjectProperty<AccountsGroup>();
    private ObservableList<AccountsGroup> accountGroups = FXCollections.observableArrayList();
    private ObservableList<Account> accounts = FXCollections.observableArrayList();

    public ObjectProperty<Account> getAccountProperty() {
        return accountProperty;
    }
    
    public Account getAccount(){
        return accountProperty.get();
    }
    
    public void setAccount(Account account){
        accountProperty.set(account);
    }

    public void replaceAccountsList(List<Account> newsAccounts) {
        this.accounts.clear();
        this.accounts.addAll(newsAccounts);
    }

    public ObservableList<AccountsGroup> getAccountGroups() {
        return accountGroups;
    }

    public void addAccountsGroup(AccountsGroup accountsGroup) {
        accountGroups.add(accountsGroup);
    }

    public ObservableList<Account> getAccounts() {
        return accounts;
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public AccountsGroup getAccountsGroup() {
        return accountsGroupProperty.get();
    }

    public void setAccountsGroup(AccountsGroup accountsGroup) {
        this.accountsGroupProperty.set(accountsGroup);
    }

    public ObjectProperty<AccountsGroup> getAccountsGroupProperty() {
        return accountsGroupProperty;
    }

}
