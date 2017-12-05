/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mq310.alfolisoft.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Moises
 */
public class ConfigurationsViewController extends GeneralController {

    public static final String CONFIGURATIONSVIEW_NAME = "configurationsView";
    public static final String CONFIGURATIONSVIEW_PATH = SystemMainViewController.SYSTEMMAINNAVIGATOR_PATH + DP + CONFIGURATIONSVIEW_NAME;

    public static final String CONFIGURATIONSVIEWCONTROLLER_NAME = "configurationsViewController";
    public static final String CONFIGURATIONSVIEWNAVIGATOR_NAME = "configurationsViewNavigator";
    public static final String CONFIGURATIONSVIEWNAVIGATOR_PATH = CONFIGURATIONSVIEW_PATH + DS + CONFIGURATIONSVIEWCONTROLLER_NAME + DP + CONFIGURATIONSVIEWNAVIGATOR_NAME;

    public static ConfigurationsViewController create() throws IOException {
        return (ConfigurationsViewController) loadController("/fxml/ConfigurationsView.fxml");
    }

    @FXML
    private BorderPane mainContent;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @Override
    public void init() {
        mainContent.setCenter(createNavigator(CONFIGURATIONSVIEWNAVIGATOR_NAME));
    }

    public Parent getView() {
        return mainContent;
    }

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
    
}
