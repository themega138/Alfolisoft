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
public class UnloadedControllerException extends Exception{

    /**
	 * 
	 */
	private static final long serialVersionUID = 4511976241306544647L;
	
	private String controllerName = "none";
    
    public UnloadedControllerException(String controllerName) {
        super("El controller '"+controllerName+"' no a sido creado o cargado al contexto...");
        this.controllerName = controllerName;
    }

    public String getControllerName() {
        return controllerName;
    }
}
