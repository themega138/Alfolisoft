/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mq310.alfolisoft.controllers.accounts;

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
public class AccountsMainViewController extends GeneralController {

    public static final String VIEW_NAME = "accountsMainView";
    public static final String VIEW_PATH = AdministratorsViewController.ADMINISTRATORSVIEWNAVIGATOR_PATH + DP + VIEW_NAME;

    public static final String CONTROLLER_NAME = "accountsMainViewController";
    public static final String ACCOUNTSMAINVIEWNAVIGATOR_NAME = "accountsMainViewNavigator";
    public static final String ACCOUNTSMAINVIEWNAVIGATOR_PATH = VIEW_PATH + DS + CONTROLLER_NAME + DP + ACCOUNTSMAINVIEWNAVIGATOR_NAME;
    
    public static AccountsMainViewController create() throws IOException {
        return (AccountsMainViewController) loadController("/fxml/accounts/AccountsMainView.fxml");
    }

    @FXML
    private BorderPane mainContent;

    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @Override
    public void init() {
        mainContent.setCenter(createNavigator(ACCOUNTSMAINVIEWNAVIGATOR_NAME));
    }

    public Parent getView() {
        return mainContent;
    }

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
