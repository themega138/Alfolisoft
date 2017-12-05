/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mq310.ent.org;

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
public class Organization extends OrganizationBase {

    /**
     *
     */
    private static final long serialVersionUID = -5309283631354331510L;

    private final StringProperty phoneProperty = new SimpleStringProperty();
    private final ObjectProperty<SortedSet<BankAccount>> bankAccountsProperty = new SimpleObjectProperty<>(new TreeSet<>());

    public String getPhone() {
        return phoneProperty.get();
    }

    public void setPhone(String phone) {
        phoneProperty.set(phone);
    }

    @Transient
    public StringProperty getPhoneProperty() {
        return phoneProperty;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "organization")
    @Fetch(FetchMode.SELECT)
    @OrderBy(clause = "id")
    public SortedSet<BankAccount> getBankAccounts() {
        return bankAccountsProperty.get();
    }

    public void setBankAccounts(SortedSet<BankAccount> bankAccounts) {
        this.bankAccountsProperty.set(bankAccounts);
    }

    @Transient
    public ObjectProperty<SortedSet<BankAccount>> getBankAccountsProperty() {
        return bankAccountsProperty;
    }
    
}
