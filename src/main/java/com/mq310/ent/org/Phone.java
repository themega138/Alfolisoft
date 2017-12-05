/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mq310.ent.org;

import com.mq310.ent.BaseEntity;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.persistence.Entity;
import javax.persistence.Transient;

/**
 *
 * @author Moises
 */
@Entity
public class Phone extends BaseEntity{
    
    private StringProperty phoneNumberProperty = new SimpleStringProperty("");

    public Phone() {
    }

    public Phone(String phone) {
        super();
        phoneNumberProperty.set(phone);
    }
    
    public String getPhoneNumber(){
        return phoneNumberProperty.get();
    }
    
    public void setPhoneNumber(String phone){
        phoneNumberProperty.set(phone);
    }

    @Transient
    public StringProperty getPhoneNumberProperty() {
        return phoneNumberProperty;
    }

}
