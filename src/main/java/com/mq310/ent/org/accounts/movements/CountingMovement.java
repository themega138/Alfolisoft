/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mq310.ent.org.accounts.movements;

import com.mq310.ent.org.counting.Counting;
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
public class CountingMovement extends IncomingMovement{
    
    private final ObjectProperty<Counting> countingProperty = new SimpleObjectProperty<>(new Counting());

    public CountingMovement() {
    }
    
    @ManyToOne(fetch = FetchType.LAZY)
    public Counting getCounting(){
        return countingProperty.get();
    }
    
    public void setCounting(Counting counting){
        countingProperty.set(counting);
    }

    @Transient
    public ObjectProperty<Counting> getCountingProperty() {
        return countingProperty;
    }

}
