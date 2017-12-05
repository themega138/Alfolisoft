/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mq310.ent.org;

import com.mq310.ent.EntityCreator;
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
public class Bank extends OrganizationBase {

    /**
     *
     */
    private static final long serialVersionUID = -5834802061994932118L;

    private final ObjectProperty<SortedSet<BankAccount>> bankAccountsProperty = new SimpleObjectProperty<>(new TreeSet<>());
    private final ObjectProperty<SortedSet<BankPhone>> bankPhonesProperty = new SimpleObjectProperty<>(new TreeSet<>());
    private final StringProperty iconProperty = new SimpleStringProperty("");

    public Bank() {
        super();
    }
    
    public Bank(String name, String icon, BankPhone[] phones, EntityCreator creator) {
        super();
        setName(name);
        setIcon(icon);
        for (BankPhone bankPhone : phones) {
            addBankPhone(bankPhone);
        }
        setCreator(creator);
    }
    
    public void addBankPhone(BankPhone phone){
        phone.setBank(this);
        this.bankPhonesProperty.get().add(phone);
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "bank")
    @Fetch(FetchMode.SELECT)
    @OrderBy(clause = "id")
    public SortedSet<BankAccount> getBankAccounts() {
        return bankAccountsProperty.get();
    }

    public void setBankAccounts(SortedSet<BankAccount> bankAccounts) {
        this.bankAccountsProperty.set(bankAccounts);
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "bank")
    @Fetch(FetchMode.SELECT)
    @OrderBy(clause = "id")
    public SortedSet<BankPhone> getBankPhones() {
        return bankPhonesProperty.get();
    }

    public void setBankPhones(SortedSet<BankPhone> bankPhones) {
        this.bankPhonesProperty.set(bankPhones);
    }

    public String getIcon(){
        return iconProperty.get();
    }
    
    public void setIcon(String icon){
        iconProperty.set(icon);
    }
    
    @Transient
    public StringProperty getIconProperty() {
        return iconProperty;
    }

}
