/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mq310.ent.org.counting;

import com.mq310.ent.BaseEntity;
import java.util.Date;
import java.util.SortedSet;
import java.util.TreeSet;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.OrderBy;

/**
 *
 * @author Administrador
 */
@Entity
public class Remittance extends BaseEntity {

    private static final long serialVersionUID = -6473613819304950862L;

    private final ObjectProperty<Date> creationDateProperty = new SimpleObjectProperty<>();
    private final ObjectProperty<Date> closingDateProperty = new SimpleObjectProperty<>();
    private final ObjectProperty<Date> deliveryDateProperty = new SimpleObjectProperty<>();
    private final ObjectProperty<SortedSet<Counting>> countingsProperty = new SimpleObjectProperty<>(new TreeSet<>());
    private final ObjectProperty<RemittanceStatus> remittanceStatusProperty = new SimpleObjectProperty<>(RemittanceStatus.OPENED);

    @Temporal(javax.persistence.TemporalType.DATE)
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

    @Temporal(TemporalType.DATE)
    public Date getClosingDate() {
        return closingDateProperty.get();
    }

    public void setClosingDate(Date closingDate) {
        this.closingDateProperty.set(closingDate);
    }

    @Transient
    public ObjectProperty<Date> getClosingDateProperty() {
        return closingDateProperty;
    }

    @Temporal(TemporalType.DATE)
    public Date getDeliveryDate() {
        return deliveryDateProperty.get();
    }

    public void setDeliveryDate(
            Date deliveryDate) {
        this.deliveryDateProperty.set(deliveryDate);
    }

    @Transient
    public ObjectProperty<Date> getDeliveryDateProperty() {
        return deliveryDateProperty;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "remittance")
    @Fetch(FetchMode.SELECT)
    @OrderBy(clause = "id")
    public SortedSet<Counting> getCountings(){
        return countingsProperty.get();
    }
    
    public void setCountings(SortedSet<Counting> list){
        this.countingsProperty.set(list);
    }
    
    public void addCounting(Counting counting){
        counting.setRemittance(this);
        this.countingsProperty.get().add(counting);
    }
    
    @Enumerated(EnumType.STRING)
    public RemittanceStatus getRemittanceStatus(){
        return remittanceStatusProperty.get();
    }
    
    public void setRemittanceStatus(RemittanceStatus remittanceStatus){
        remittanceStatusProperty.set(remittanceStatus);
    }
    
    @Transient
    public ObjectProperty<RemittanceStatus> getRemittanceStatusProperty() {
        return remittanceStatusProperty;
    }

}
