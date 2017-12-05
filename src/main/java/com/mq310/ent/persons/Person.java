/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mq310.ent.persons;

import com.mq310.ent.BaseEntity;
import com.mq310.ent.org.counting.docs.BankDocument;
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
public class Person extends BaseEntity {

    private static final long serialVersionUID = 1879114998532142367L;

    private final StringProperty firstnameProperty = new SimpleStringProperty("");
    private final StringProperty lastnameProperty = new SimpleStringProperty("");
    private final ObjectProperty<SortedSet<BankDocument>> bankDocumentsProperty = new SimpleObjectProperty<>(new TreeSet<>());
    
    public Person() {
        super();
    }
    
    public String getFirstname() {
        return firstnameProperty.get();
    }

    public void setFirstname(String firstname) {
        this.firstnameProperty.set(firstname);
    }

    @Transient
    public StringProperty getFirstnameProperty() {
        return firstnameProperty;
    }

    public String getLastname() {
        return lastnameProperty.get();
    }

    public void setLastname(String lastname) {
        this.lastnameProperty.set(lastname);
    }

    @Transient
    public StringProperty getLastnameProperty() {
        return lastnameProperty;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "person")
    @Fetch(FetchMode.SELECT)
    @OrderBy(clause = "id")
    public SortedSet<BankDocument> getBankDocuments() {
        return bankDocumentsProperty.get();
    }

    public void setBankDocuments(SortedSet<BankDocument> bankDocuments) {
        this.bankDocumentsProperty.set(bankDocuments);
    }

}
