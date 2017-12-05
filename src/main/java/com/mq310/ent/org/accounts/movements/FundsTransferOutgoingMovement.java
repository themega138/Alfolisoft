/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mq310.ent.org.accounts.movements;

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
public class FundsTransferOutgoingMovement extends OutgoingMovement{
    
    private ObjectProperty<FundsTransferItem> fundsTransferItemProperty = new SimpleObjectProperty<>(new FundsTransferItem());

    public FundsTransferOutgoingMovement() {
    }
    
    @ManyToOne(fetch = FetchType.LAZY)
    public FundsTransferItem getFundsTransferItem(){
        return fundsTransferItemProperty.get();
    }
    
    public void setFundsTransferItem(FundsTransferItem item){
        fundsTransferItemProperty.set(item);
    }

    @Transient
    public ObjectProperty<FundsTransferItem> getFundsTransferItemProperty() {
        return fundsTransferItemProperty;
    }

}
