/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mq310.alfolisoft.controllers.accountsPlan;

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
public class AccountsPlanMainViewController extends GeneralController{
    
    public static final String VIEW_NAME = "accountsPlanMainView";
    public static final String VIEW_PATH = AdministratorsViewController.ADMINISTRATORSVIEWNAVIGATOR_PATH+DP+VIEW_NAME;
    
    public static final String CONTROLLER_NAME = "accountsPlanMainViewController";
    public static final String ACCOUNTSPLANMAINNAVIGATOR_NAME = "accountsPlanMainNavigator";
    public static final String ACCOUNTSPLANMAINVAVIGATOR_PATH = VIEW_PATH+DS+CONTROLLER_NAME+DP+ACCOUNTSPLANMAINNAVIGATOR_NAME;
    
    public static AccountsPlanMainViewController create() throws IOException {
        return (AccountsPlanMainViewController) loadController("/fxml/accountsPlan/AccountsPlanMainView.fxml");
    }
    
    @FXML
    private BorderPane mainContent;

    /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @Override
    public void init() {
        mainContent.setCenter(createNavigator(ACCOUNTSPLANMAINNAVIGATOR_NAME));
    }

    public Parent getView() {
        return mainContent;
    }

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
    
}
