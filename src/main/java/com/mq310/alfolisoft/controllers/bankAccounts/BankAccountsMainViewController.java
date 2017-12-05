/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mq310.alfolisoft.controllers.bankAccounts;

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
public class BankAccountsMainViewController extends GeneralController{

    public static final String VIEW_NAME = "bankAccountsMainView";
    public static final String VIEW_PATH = AdministratorsViewController.ADMINISTRATORSVIEWNAVIGATOR_PATH + DP + VIEW_NAME;

    public static final String CONTROLLER_NAME = "bankAccountsMainViewController";
    public static final String BANKACCOUNTSMAINVIEWNAVIGATOR_NAME = "bankAccountsMainViewNavigator";
    public static final String BANKACCOUNTSMAINVIEWNAVIGATOR_PATH = VIEW_PATH + DS + CONTROLLER_NAME + DP + BANKACCOUNTSMAINVIEWNAVIGATOR_NAME;

    public static BankAccountsMainViewController create() throws IOException {
        return (BankAccountsMainViewController) loadController("/fxml/bankAccounts/BankAccountsMainView.fxml");
    }
    
    @FXML
    private BorderPane mainContent;

    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @Override
    public void init() {
        mainContent.setCenter(createNavigator(BANKACCOUNTSMAINVIEWNAVIGATOR_NAME));
    }

    public Parent getView() {
        return mainContent;
    }

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
    
}
