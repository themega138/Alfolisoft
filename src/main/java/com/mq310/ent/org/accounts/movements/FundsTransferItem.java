package com.mq310.ent.org.accounts.movements;

import com.mq310.ent.BaseEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.OrderBy;

@Entity
public class FundsTransferItem extends BaseEntity {
    
    private final ObjectProperty<FundsTransfer> fundsTransferProperty = new SimpleObjectProperty<>(new FundsTransfer());
    private final ObjectProperty<FundsTransferIncomingMovement> incomingMovementProperty = new SimpleObjectProperty<>();
    private final ObjectProperty<SortedSet<FundsTransferOutgoingMovement>> outgoingMovementsProperty = new SimpleObjectProperty<>(new TreeSet<>());
    
    public FundsTransferItem() {
    }
    
    @ManyToOne(fetch = FetchType.LAZY)
    public FundsTransfer getFundsTransfer(){
        return fundsTransferProperty.get();
    }
    
    public void setFundsTransfer(FundsTransfer transfer){
        fundsTransferProperty.set(transfer);
    }

    @Transient
    public ObjectProperty<FundsTransfer> getFundsTransferProperty() {
        return fundsTransferProperty;
    }

    @OneToOne(fetch = FetchType.LAZY)
    public FundsTransferIncomingMovement getIncomingMovement(){
        return incomingMovementProperty.get();
    }
    
    public void setIncomingMovement(FundsTransferIncomingMovement movement){
        incomingMovementProperty.set(movement);
    }

    @Transient
    public ObjectProperty<FundsTransferIncomingMovement> getIncomingMovementProperty() {
        return incomingMovementProperty;
    }

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "fundsTransferItem")
    @Fetch(FetchMode.SELECT)
    @OrderBy(clause = "id")
    public SortedSet<FundsTransferOutgoingMovement> getOutgoingMovements(){
        return outgoingMovementsProperty.get();
    }
    
    public void setOutgoingMovements(SortedSet<FundsTransferOutgoingMovement> movements){
        this.outgoingMovementsProperty.set(movements);
    }
    
    public void addOutgoingMovement(FundsTransferOutgoingMovement movement){
        movement.setFundsTransferItem(this);
        this.outgoingMovementsProperty.get().add(movement);
    }
    
    public void removeOutgoingMovement(FundsTransferOutgoingMovement movement){
        this.outgoingMovementsProperty.get().remove(movement);
    }

    @Transient
    public ObjectProperty<SortedSet<FundsTransferOutgoingMovement>> getOutgoingMovementsProperty() {
        return outgoingMovementsProperty;
    }
    
}
