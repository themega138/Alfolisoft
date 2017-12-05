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
public class DashboardViewController extends GeneralController{
    
    public static final String DASHBOARDVIEW_NAME = "dashboardView";
    public static final String DASHBOARDVIEW_PATH = SystemMainViewController.SYSTEMMAINNAVIGATOR_PATH+DP+DASHBOARDVIEW_NAME;

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
        
    }

    public Parent getView() {
        return mainContent;
    }
    
    public static DashboardViewController create() throws IOException {
        return (DashboardViewController) loadController("/fxml/DashboardView.fxml");
    }

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
}
