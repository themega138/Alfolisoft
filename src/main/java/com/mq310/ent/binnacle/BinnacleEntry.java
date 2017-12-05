/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mq310.ent.binnacle;

import com.mq310.ent.BaseEntity;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class BinnacleEntry extends BaseEntity{
    
    private final ObjectProperty<Binnacle> binnacleProperty = new SimpleObjectProperty<>(new Binnacle());
    private final StringProperty messageProperty = new SimpleStringProperty("");
    private final ObjectProperty<BinnacleEntryLevel> levelProperty = new SimpleObjectProperty<>(BinnacleEntryLevel.INFO);

    public BinnacleEntry() {
    }
    
    @ManyToOne(fetch = FetchType.LAZY)
    public Binnacle getBinnacle(){
        return this.binnacleProperty.get();
    }
    
    public void setBinnacle(Binnacle binnacle){
        this.binnacleProperty.set(binnacle);
    }

    @Transient
    public ObjectProperty<Binnacle> getBinnacleProperty() {
        return binnacleProperty;
    }
    
    public String getMessage(){
        return this.messageProperty.get();
    }
    
    public void setMessage(String message){
        this.messageProperty.set(message);
    }

    @Transient
    public StringProperty getMessageProperty() {
        return messageProperty;
    }
    
    public BinnacleEntryLevel getLevel(){
        return this.levelProperty.get();
    }
    
    public void setLevel(BinnacleEntryLevel level){
        this.levelProperty.set(level);
    }
    
}
