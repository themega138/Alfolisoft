/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mq310.ent.org;

import com.mq310.ent.EntityCreator;
import com.mq310.ent.org.Bank;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

/**
 *
 * @author Moises
 */
@Entity
public class BankPhone extends Phone{
    
    private ObjectProperty<Bank> bankProperty = new SimpleObjectProperty<>(new Bank());

    public BankPhone() {
    }

    public BankPhone(String phone, EntityCreator creator) {
        super(phone);
        setCreator(creator);
    }
    
    @ManyToOne(fetch = FetchType.LAZY)
    public Bank getBank(){
        return bankProperty.get();
    }
    
    public void setBank(Bank bank){
        bankProperty.set(bank);
    }

    @Transient
    public ObjectProperty<Bank> getBankProperty() {
        return bankProperty;
    }

}
