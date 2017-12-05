/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mq310.alfolisoft.models;

import com.mq310.ent.sec.User;


/**
 *
 * @author Administrador
 */
public class LoginModel extends GeneralModel {

//    private User user;
//
//    public LoginModel() {
//    	this.user = new User();
//    }
//    
//    public void setUser(User user) {
//		this.user = user;
//	}
//    
//    public User getUser() {
//		return user;
//	}
    private User user = new User();

    public static final String PROP_USER = "user";

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        User oldUser = this.user;
        this.user = user;
        propertyChangeSupport.firePropertyChange(PROP_USER, oldUser, user);
    }
}
