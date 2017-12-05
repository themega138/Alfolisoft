/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mq310.ent.org.counting;

import com.mq310.ent.BaseEntity;
import com.mq310.ent.binnacle.CountingBinnacle;
import com.mq310.ent.org.accounts.movements.CountingMovement;
import com.mq310.ent.org.counting.docs.IncomingBankDocument;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
public class Counting extends BaseEntity {

    private static final long serialVersionUID = 2318754515770154858L;

    private final ObjectProperty<Date> creationDateProperty = new SimpleObjectProperty<>();
    private final ObjectProperty<CashCount> cashCountProperty = new SimpleObjectProperty<>(new CashCount());
    private final ObjectProperty<SortedSet<IncomingBankDocument>> incomingBankDocumentsProperty = new SimpleObjectProperty<>(new TreeSet<>());
    private final ObjectProperty<SortedSet<Packet>> packetsProperty = new SimpleObjectProperty<>(new TreeSet<>());
    private final ObjectProperty<Remittance> remittanceProperty = new SimpleObjectProperty<>();
    private final ObjectProperty<SortedSet<CountingMovement>> countingMovementsProperty = new SimpleObjectProperty<>(new TreeSet<>());
    private final ObjectProperty<CountingStatus> countingStatusProperty = new SimpleObjectProperty<>(CountingStatus.STANDBY);
    private final ObjectProperty<Date> countingStartTimeProperty = new SimpleObjectProperty<>();
    private final ObjectProperty<Date> countingFinishTimeProperty = new SimpleObjectProperty<>();
    private final ObjectProperty<CountingBinnacle> binnacleProperty = new SimpleObjectProperty<>();

    public Counting() {
        super();
        cashCountProperty.get().setCounting(this);
        //binnacleProperty.get().setCounting(this);
    }

    @Temporal(TemporalType.DATE)
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
    
    @Temporal(TemporalType.TIMESTAMP)
    public Date getCountingStartTime() {
        return this.countingStartTimeProperty.get();
    }

    public void setCountingStartTime(Date start) {
        this.countingStartTimeProperty.set(start);
    }

    @Transient
    public ObjectProperty<Date> getCountingStartTimeProperty() {
        return countingStartTimeProperty;
    }
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date getCountingFinishTime() {
        return this.countingFinishTimeProperty.get();
    }
    
    public void setCountingFinishTime(Date date) {
        this.countingFinishTimeProperty.set(date);
    }

    @Transient
    public ObjectProperty<Date> getCountingFinishTimeProperty() {
        return countingFinishTimeProperty;
    }
    
    @OneToOne(cascade = CascadeType.ALL)
    public CashCount getCashCount() {
        return cashCountProperty.get();
    }

    public void setCashCount(CashCount cashCount) {
        this.cashCountProperty.set(cashCount);
    }

    @Transient
    public ObjectProperty<CashCount> getCashCountProperty() {
        return cashCountProperty;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "counting")
    @Fetch(FetchMode.SELECT)
    @OrderBy(clause = "id")
    public SortedSet<IncomingBankDocument> getIncomingBankDocuments() {
        return incomingBankDocumentsProperty.get();
    }

    public void setIncomingBankDocuments(SortedSet<IncomingBankDocument> docs) {
        this.incomingBankDocumentsProperty.set(docs);
    }
    
    public void addIncomingBankDocument(IncomingBankDocument bankDocument){
        bankDocument.setCounting(this);
        this.incomingBankDocumentsProperty.get().add(bankDocument);
    }
    
    public void removeIncomingBankDocument(IncomingBankDocument bankDocument){
        this.incomingBankDocumentsProperty.get().remove(bankDocument);
    }

    @Transient
    public ObjectProperty<SortedSet<IncomingBankDocument>> getIncomingBankDocumentsProperty() {
        return incomingBankDocumentsProperty;
    }
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "counting")
    @Fetch(FetchMode.SELECT)
    @OrderBy(clause = "id")
    public SortedSet<Packet> getPackets() {
        return packetsProperty.get();
    }

    public void setPackets(SortedSet<Packet> packets) {
        this.packetsProperty.set(packets);
    }

    @Transient
    public ObjectProperty<SortedSet<Packet>> getPacketsProperty() {
        return packetsProperty;
    }
    
    @ManyToOne(fetch = FetchType.LAZY)
    public Remittance getRemittance() {
        return remittanceProperty.get();
    }

    public void setRemittance(Remittance remittance) {
        this.remittanceProperty.set(remittance);
    }

    @Transient
    public ObjectProperty<Remittance> getRemittanceProperty() {
        return remittanceProperty;
    }

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "counting")
    @Fetch(FetchMode.SELECT)
    @OrderBy(clause = "id")
    public SortedSet<CountingMovement> getCountingMovements(){
        return countingMovementsProperty.get();
    }
    
    public void setCountingMovements(SortedSet<CountingMovement> movements){
        this.countingMovementsProperty.set(movements);
    }
    
    public void addCountingMovement(CountingMovement movement){
        movement.setCounting(this);
        this.countingMovementsProperty.get().add(movement);
    }
    
    public void removeCountingMovement(CountingMovement movement){
        this.countingMovementsProperty.get().remove(movement);
    }

    @Transient
    public ObjectProperty<SortedSet<CountingMovement>> getCountingMovementsProperty() {
        return countingMovementsProperty;
    }
    
    @Enumerated(EnumType.STRING)
    public CountingStatus getCountingStatus(){
        return countingStatusProperty.get();
    }
    
    public void setCountingStatus(CountingStatus status){
        this.countingStatusProperty.set(status);
    }

    @Transient
    public ObjectProperty<CountingStatus> getCountingStatusProperty() {
        return countingStatusProperty;
    }
    
    @OneToOne(cascade = CascadeType.ALL)
    public CountingBinnacle getBinnacle(){
        return this.binnacleProperty.get();
    }
    
    public void setBinnacle(CountingBinnacle binnacle){
        this.binnacleProperty.set(binnacle);
    }
    
    @Transient
    public ObjectProperty<CountingBinnacle> getBinnacleProperty() {
        return binnacleProperty;
    }

}
