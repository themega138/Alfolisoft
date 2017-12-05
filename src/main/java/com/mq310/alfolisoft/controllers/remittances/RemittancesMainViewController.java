/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mq310.alfolisoft.controllers.remittances;

import com.mq310.alfolisoft.controllers.AdministratorsViewController;
import com.mq310.alfolisoft.controllers.GeneralController;
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
public class RemittancesMainViewController extends GeneralController{

    public static final String VIEW_NAME = "remittancesMainView";
    public static final String VIEW_PATH = AdministratorsViewController.ADMINISTRATORSVIEWNAVIGATOR_PATH + DP + VIEW_NAME;

    public static final String CONTROLLER_NAME = "remittancesMainViewController";
    public static final String REMITTANCESMAINVIEWNAVIGATOR_NAME = "remittancesMainViewNavigator";
    public static final String REMITTANCESMAINVIEWNAVIGATOR_PATH = VIEW_PATH + DS + CONTROLLER_NAME + DP + REMITTANCESMAINVIEWNAVIGATOR_NAME;

    public static RemittancesMainViewController create() throws IOException {
        return (RemittancesMainViewController) loadController("/fxml/remittances/RemittancesMainView.fxml");
    }
    
    @FXML
    private BorderPane mainContent;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @Override
    public void init() {
        mainContent.setCenter(createNavigator(REMITTANCESMAINVIEWNAVIGATOR_NAME));
    }

    public Parent getView() {
        return mainContent;
    }

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}   
    
}
