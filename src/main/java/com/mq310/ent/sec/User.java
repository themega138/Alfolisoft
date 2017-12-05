/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mq310.ent.sec;

import com.mq310.ent.BaseEntity;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.persistence.Transient;

/**
 *
 * @author Administrador
 */
public class User extends BaseEntity {

    /**
     *
     */
    private static final long serialVersionUID = -482137622588335354L;

    private StringProperty usernameProperty = new SimpleStringProperty("");
    private StringProperty passwordProperty = new SimpleStringProperty("");

    public String getUsername() {
        return usernameProperty.get();
    }

    public void setUsername(String username) {
        this.usernameProperty.set(username);
    }

    @Transient
    public StringProperty getUsernameProperty() {
        return usernameProperty;
    }

    public void setUsernameProperty(StringProperty usernameProperty) {
        this.usernameProperty = usernameProperty;
    }

    public String getPassword() {
        return passwordProperty.get();
    }

    public void setPassword(String password) {
        this.passwordProperty.set(password);
    }

    @Transient
    public StringProperty getPasswordProperty() {
        return passwordProperty;
    }

    public void setPasswordProperty(StringProperty passwordProperty) {
        this.passwordProperty = passwordProperty;
    }

}
