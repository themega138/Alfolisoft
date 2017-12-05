/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mq310.alfolisoft.controllers.accounts;

import java.io.IOException;

import javafx.event.ActionEvent;

import com.mq310.alfolisoft.controllers.GeneralInitViewController;
import com.mq310.alfolisoft.views.InitialPanelData;

/**
 * FXML Controller class
 *
 * @author Moises
 */
public class AccountsInitialViewController extends GeneralInitViewController{
    
    public static final String VIEW_NAME = "accountsInitialView";
    public static final String VIEW_PATH = AccountsMainViewController.ACCOUNTSMAINVIEWNAVIGATOR_PATH + DP + VIEW_NAME;
    
    private InitialPanelData[] data = new InitialPanelData[]{
        new InitialPanelData(
                "/images/initial/accounts.png",
                "Maestro de Cuentas",
                AccountsMasterViewController.VIEW_PATH
        ),
        new InitialPanelData(
                "/images/initial/help.png",
                "Ayuda",
                AccountsInitialViewController.VIEW_PATH
        ),
    };
    
    public static AccountsInitialViewController create() throws IOException {
        return (AccountsInitialViewController) loadInitController(new AccountsInitialViewController());
    }

    @Override
    public void init() {
        setTitle("Alfolisoft - Cuentas");
        initComponents(data);
    }

    public void handle(ActionEvent event) {
        
    }

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
    
}
