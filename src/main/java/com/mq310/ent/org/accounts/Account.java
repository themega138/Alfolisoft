/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mq310.ent.org.accounts;

import com.mq310.ent.EntityCreator;
import com.mq310.ent.BaseEntity;
import com.mq310.ent.EntityVisibility;
import java.util.SortedSet;
import java.util.TreeSet;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
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
public class Account extends BaseEntity {

    private static final long serialVersionUID = 3553395398690616535L;

    private final StringProperty nameProperty = new SimpleStringProperty("");
    private final StringProperty descriptionProperty = new SimpleStringProperty("");
    private final ObjectProperty<AccountsGroup> accountsGroupProperty = new SimpleObjectProperty<>(new AccountsGroup());
    private final ObjectProperty<SortedSet<AccountsPlanItem>> accountsPlanItemsProperty = new SimpleObjectProperty<>(new TreeSet<>());

    public Account() {
        super(0);
    }
    
    public Account(String name, String description, EntityCreator creator){
        super(0);
        this.nameProperty.set(name);
        this.descriptionProperty.set(description);
        setCreator(creator);
    }
    
    public Account(String name, String description, EntityCreator creator, EntityVisibility visibility){
        this(name,description,creator);
        setVisibility(visibility);
    }

    public String getName() {
        return nameProperty.get();
    }

    public void setName(String name) {
        this.nameProperty.set(name);
    }

    @Transient
    public StringProperty getNameProperty() {
        return nameProperty;
    }

    public String getDescription() {
        return descriptionProperty.get();
    }

    public void setDescription(String description) {
        this.descriptionProperty.set(description);
    }

    @Transient
    public StringProperty getDescriptionProperty() {
        return descriptionProperty;
    }

    @ManyToOne
    public AccountsGroup getAccountsGroup() {
        return accountsGroupProperty.get();
    }

    public void setAccountsGroup(AccountsGroup accountsGroup) {
        this.accountsGroupProperty.set(accountsGroup);
    }

    @Transient
    public ObjectProperty<AccountsGroup> getAccountsGroupProperty() {
        return accountsGroupProperty;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "account")
    @Fetch(FetchMode.SELECT)
    @OrderBy(clause = "id")
    public SortedSet<AccountsPlanItem> getAccountsPlanItems(){
        return accountsPlanItemsProperty.get();
    }
    
    public void setAccountsPlanItems(SortedSet<AccountsPlanItem> accountsPlanItems){
        this.accountsPlanItemsProperty.set(accountsPlanItems);
    }

    @Transient
    public ObjectProperty<SortedSet<AccountsPlanItem>> getAccountsPlanItemsProperty() {
        return accountsPlanItemsProperty;
    }
    
    @Override
    public String toString() {
        return getName(); 
    }
    
}
