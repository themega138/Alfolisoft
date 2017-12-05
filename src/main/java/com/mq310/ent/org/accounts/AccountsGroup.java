/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mq310.ent.org.accounts;

import com.mq310.ent.BaseEntity;
import com.mq310.ent.EntityCreator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.OrderBy;

/**
 *
 * @author Administrador
 */
@Entity
public class AccountsGroup extends BaseEntity {

    /**
     *
     */
    private static final long serialVersionUID = 4045038255407873322L;

    private final StringProperty nameProperty = new SimpleStringProperty("");
    private final StringProperty descriptionProperty = new SimpleStringProperty("");
    private final ObjectProperty<SortedSet<Account>> accountsProperty = new SimpleObjectProperty<>(new TreeSet<>());

    public AccountsGroup() {
        super(0);
    }
    
    public AccountsGroup(String name, String description, List<Account> accounts, EntityCreator creator){
        super(0);
        this.nameProperty.set(name);
        this.descriptionProperty.set(description);
        accounts.stream().map((account) -> {
            account.setAccountsGroup(this);
            return account;
        }).forEach((account) -> {
            accountsProperty.get().add(account);
        });
        setCreator(creator);
    }

    public String getName() {
        return nameProperty.get();
    }

    public void setName(String name) {
        nameProperty.set(name);
    }

    @Transient
    public StringProperty getNameProperty() {
        return nameProperty;
    }

    public String getDescription() {
        return descriptionProperty.get();
    }

    public void setDescription(String description) {
        descriptionProperty.set(description);
    }

    @Transient
    public StringProperty getDescriptionProperty() {
        return descriptionProperty;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "accountsGroup")
    @Fetch(FetchMode.SELECT)
    @OrderBy(clause = "id")
    public SortedSet<Account> getAccounts() {
        return accountsProperty.get();
    }

    public void setAccounts(SortedSet<Account> accounts) {
        this.accountsProperty.set(accounts);
    }

    @Transient
    public ObjectProperty<SortedSet<Account>> getAccountsProperty() {
        return accountsProperty;
    }
    
    @Override
    public String toString() {
        return nameProperty.get();
    }

}
