/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mq310.alfolisoft.controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Moises
 */
public class MainViewController extends GeneralController {
    
    public static final String MAINVIEWCONTROLLER_NAME = "mainViewController";
    public static final String MAINVIEWNAVIGATOR_NAME = "mainViewNavigator";
    public static final String MAINVIEWNAVIGATOR_PATH = MAINVIEWCONTROLLER_NAME+DP+MAINVIEWNAVIGATOR_NAME;
    
    @FXML
    private BorderPane mainPanel;

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void init(){
        mainPanel.setCenter(createNavigator(MAINVIEWNAVIGATOR_NAME));
        
        
    }

    public Parent getView() {
        return mainPanel;
    }

    public static MainViewController create() throws IOException {
        return (MainViewController) loadController("/fxml/MainView.fxml");
    }

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
