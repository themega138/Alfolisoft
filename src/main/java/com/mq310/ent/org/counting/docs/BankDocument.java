/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mq310.ent.org.counting.docs;

import com.mq310.ent.persons.Member;
import com.mq310.ent.persons.Person;
import java.util.Date;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author Administrador
 */
@Entity
public class BankDocument extends Document {

    /**
     *
     */
    private static final long serialVersionUID = -270572171574194548L;

    private final DoubleProperty amountProperty = new SimpleDoubleProperty(0.0);
    private final ObjectProperty<Date> creationDateProperty = new SimpleObjectProperty<>(new Date());
    private final ObjectProperty<DocumentStatus> documentStatusProperty = new SimpleObjectProperty<>();
    private final ObjectProperty<Person> personProperty = new SimpleObjectProperty<>(new Member());
    private final ObjectProperty<BankDocumentType> typeProperty = new SimpleObjectProperty<>();

    public BankDocument() {
        super();
    }

    public Double getAmount() {
        return amountProperty.get();
    }

    public void setAmount(Double amount) {
        this.amountProperty.set(amount);
    }

    @Transient
    public DoubleProperty getAmountProperty() {
        return amountProperty;
    }

    @Temporal(value = TemporalType.DATE)
    public Date getCreationDate() {
        return creationDateProperty.get();
    }

    public void setCreationDate(Date creationDate) {
        this.creationDateProperty.set(creationDate);
    }

    @Transient
    public ObjectProperty<Date> getCreationDateProperty() {
        return creationDateProperty;
    }

    @Enumerated(value = EnumType.STRING)
    public DocumentStatus getDocumentStatus() {
        return documentStatusProperty.get();
    }

    public void setDocumentStatus(DocumentStatus status) {
        this.documentStatusProperty.set(status);
    }

    @Transient
    public ObjectProperty<DocumentStatus> getStatusProperty() {
        return documentStatusProperty;
    }

    @ManyToOne
    public Person getPerson() {
        return personProperty.get();
    }

    public void setPerson(Person person) {
        this.personProperty.set(person);
    }

    @Transient
    public ObjectProperty<Person> getPersonProperty() {
        return personProperty;
    }

    @Enumerated(EnumType.STRING)
    public BankDocumentType getType() {
        return typeProperty.get();
    }

    public void setType(BankDocumentType type) {
        this.typeProperty.set(type);
    }

    @Transient
    public ObjectProperty<BankDocumentType> getTypeProperty() {
        return typeProperty;
    }
    
    

}
