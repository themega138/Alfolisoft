/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mq310.ent.org.counting.docs;

import com.mq310.ent.BaseEntity;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.persistence.Entity;
import javax.persistence.Transient;

/**
 *
 * @author Administrador
 */
@Entity
public class Document extends BaseEntity {

    /**
     *
     */
    private static final long serialVersionUID = -4200252778932269816L;

    private StringProperty referenceProperty = new SimpleStringProperty("");

    public Document() {
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

}
