/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mq310.ent.org.counting.docs;

import com.mq310.ent.org.BankAccount;
import com.mq310.ent.org.counting.Counting;
import java.util.Date;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

/**
 *
 * @author Administrador
 */
@Entity
public class IncomingBankDocument extends BankDocument {

    /**
     *
     */
    private static final long serialVersionUID = 8493912033483927366L;

    private final ObjectProperty<BankAccount> destinationBankAccountProperty = new SimpleObjectProperty<>();
    private final ObjectProperty<Counting> countingProperty = new SimpleObjectProperty<>();

    public IncomingBankDocument() {
    }

    public IncomingBankDocument(BankDocumentType type, Counting counting) {
        super();
        setCounting(counting);
        setType(type);
        setCreationDate(new Date());
    }
    
    @ManyToOne
    public BankAccount getDestinationBankAccount() {
        return destinationBankAccountProperty.get();
    }

    public void setDestinationBankAccount(BankAccount destinationBankAccount) {
        this.destinationBankAccountProperty.set(destinationBankAccount);
    }

    @Transient
    public ObjectProperty<BankAccount> getDestinationBankAccountProperty() {
        return destinationBankAccountProperty;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    public Counting getCounting() {
        return countingProperty.get();
    }

    public void setCounting(Counting counting) {
        this.countingProperty.set(counting);
    }

    @Transient
    public ObjectProperty<Counting> getCountingProperty() {
        return countingProperty;
    }

}
