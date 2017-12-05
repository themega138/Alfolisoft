/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mq310.alfolisoft.controllers.accountsPlan;

import com.mq310.alfolisoft.controllers.GeneralController;
import com.mq310.alfolisoft.controllers.accountsPlan.AccountsPlanMainViewController;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Moises
 */
public class AccountsBalanceMasterViewController extends GeneralController{

    public static final String VIEW_NAME = "accountsBalanceView";
    public static final String VIEW_PATH = AccountsPlanMainViewController.ACCOUNTSPLANMAINVAVIGATOR_PATH + DP + VIEW_NAME;
    
    @FXML
    private BorderPane mainContent;

    @Override
    public void init() {
        
    }

    public Parent getView() {
        return mainContent;
    }

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
}
