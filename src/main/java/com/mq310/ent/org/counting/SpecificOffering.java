/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mq310.ent.org.counting;

import com.mq310.ent.BaseEntity;
import com.mq310.ent.org.accounts.AccountsPlanItem;
import com.mq310.ent.org.accounts.movements.CountingMovement;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
public class SpecificOffering extends BaseEntity {

    /**
     *
     */
    private static final long serialVersionUID = 8436292032974232390L;

    private final ObjectProperty<AccountsPlanItem> accountsPlanItemProperty = new SimpleObjectProperty<>(new AccountsPlanItem());
    private final ObjectProperty<Packet> packetProperty = new SimpleObjectProperty<>(new Packet());
    private final DoubleProperty amountProperty = new SimpleDoubleProperty(0.0);
    private final ObjectProperty<CountingMovement> countingMovementProperty = new SimpleObjectProperty<>();
    
    public SpecificOffering() {
        super();
    }

    @ManyToOne
    public AccountsPlanItem getAccountsPlanItem() {
        return accountsPlanItemProperty.get();
    }

    public void setAccountsPlanItem(AccountsPlanItem acccountsPlanItem) {
        this.accountsPlanItemProperty.set(acccountsPlanItem);
    }

    @Transient
    public ObjectProperty<AccountsPlanItem> getAccountsPlanItemProperty() {
        return accountsPlanItemProperty;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    public Packet getPacket() {
        return packetProperty.get();
    }

    public void setPacket(Packet packet) {
        this.packetProperty.set(packet);
    }

    @Transient
    public ObjectProperty<Packet> getPacketProperty() {
        return packetProperty;
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

    @OneToOne
    public CountingMovement getCountingMovement(){
        return countingMovementProperty.get();
    }
    
    public void setCountingMovement(CountingMovement movement){
        countingMovementProperty.set(movement);
    }

    @Transient
    public ObjectProperty<CountingMovement> getCountingMovementProperty() {
        return countingMovementProperty;
    }

}
