/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mq310.ent.binnacle;

import com.mq310.ent.org.counting.Counting;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
public class CountingBinnacle extends Binnacle{
    
    private final ObjectProperty<Counting> countingProperty = new SimpleObjectProperty<>();

    public CountingBinnacle() {
        super();
    }
    
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "binnacle")
    public Counting getCounting(){
        return this.countingProperty.get();
    }
    
    public void setCounting(Counting counting){
        this.countingProperty.set(counting);
    }
    
    @Transient
    public ObjectProperty<Counting> getCountingProperty() {
        return countingProperty;
    }
    
}
