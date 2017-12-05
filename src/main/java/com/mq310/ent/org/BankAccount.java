/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mq310.ent.org;

import com.mq310.ent.BaseEntity;
import com.mq310.ent.org.counting.docs.IncomingBankDocument;
import java.util.ArrayList;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.OrderBy;

@Entity
public class BankAccount extends BaseEntity {

    /**
     *
     */
    private static final long serialVersionUID = -8037664747470734232L;

    private final StringProperty referenceProperty = new SimpleStringProperty("");
    private ObjectProperty<Bank> bankProperty = new SimpleObjectProperty<>(new Bank());
    private final ObjectProperty<SortedSet<IncomingBankDocument>> incomingBankDocumentsProperty = new SimpleObjectProperty<>(new TreeSet<>());
    private final ObjectProperty<Organization> organizationProperty = new SimpleObjectProperty<>();
    private StringProperty descriptionProperty = new SimpleStringProperty("");

    public BankAccount() {
        super();
    }

    public String getReference() {
        return referenceProperty.get();
    }

    public void setReference(String reference) {
        this.referenceProperty.set(reference);
    }

    @Transient
    public StringProperty getReferenceProperty() {
        return referenceProperty;
    }
    
    @ManyToOne
    public Bank getBank() {
        return bankProperty.get();
    }

    public void setBank(Bank bank) {
        bankProperty.set(bank);
    }

    @Transient
    public ObjectProperty<Bank> getBankProperty() {
        return bankProperty;
    }

    public void setBankProperty(ObjectProperty<Bank> bankProperty) {
        this.bankProperty = bankProperty;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "destinationBankAccount")
    @Fetch(FetchMode.SELECT)
    @OrderBy(clause = "id")
    public SortedSet<IncomingBankDocument> getIncomingBankDocuments() {
        return incomingBankDocumentsProperty.get();
    }

    public void setIncomingBankDocuments(SortedSet<IncomingBankDocument> incomingBankDocuments) {
        this.incomingBankDocumentsProperty.set(incomingBankDocuments);
    }
    
    @Transient
    public ObjectProperty<SortedSet<IncomingBankDocument>> getIncomingBankDocumentsProperty() {
        return incomingBankDocumentsProperty;
    }
    
    @ManyToOne(fetch = FetchType.LAZY)
    public Organization getOrganization() {
        return organizationProperty.get();
    }

    public void setOrganization(Organization organization) {
        organizationProperty.set(organization);
    }

    @Transient
    public ObjectProperty<Organization> getOrganizationProperty() {
        return organizationProperty;
    }
    
    public String getDescription(){
        return descriptionProperty.get();
    }
    
    public void setDescription(String description){
        descriptionProperty.set(description);
    }

    @Transient
    public StringProperty getDescriptionProperty() {
        return descriptionProperty;
    }

    public void setDescriptionProperty(StringProperty descriptionProperty) {
        this.descriptionProperty = descriptionProperty;
    }
    
}
