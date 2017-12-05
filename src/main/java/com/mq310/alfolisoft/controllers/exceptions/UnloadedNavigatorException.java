/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mq310.alfolisoft.controllers.exceptions;

/**
 *
 * @author Moises
 */
public class UnloadedNavigatorException extends Exception{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1567178209373969641L;
	
	private String navigatorName = "none";

    public UnloadedNavigatorException(String navigatorName) {
        super("El navigator '"+navigatorName+"' no ha sido agregado a este Controller...");
        this.navigatorName = navigatorName;
    }

    public String getNavigatorName() {
        return navigatorName;
    }
}
