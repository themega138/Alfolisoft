/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mq310.alfolisoft.controllers.bankAccounts;

import com.mq310.alfolisoft.controllers.GeneralInitViewController;
import java.io.IOException;

import javafx.event.ActionEvent;

import com.mq310.alfolisoft.views.InitialPanelData;

/**
 * FXML Controller class
 *
 * @author Moises
 */
public class BankAccountsInitialViewController extends GeneralInitViewController{
    
    public static final String VIEW_NAME = "bankAccountsInitialView";
    public static final String VIEW_PATH = BankAccountsMainViewController.BANKACCOUNTSMAINVIEWNAVIGATOR_PATH + DP + VIEW_NAME;
    
    private InitialPanelData[] data = new InitialPanelData[]{
        new InitialPanelData(
                "/images/initial/accounts.png",
                "Maestro de Cuentas Bancarias",
                BankAccountsMasterViewController.VIEW_PATH
        ),
        new InitialPanelData(
                "/images/initial/accounts.png",
                "Balance de Cuentas Bancarias",
                "bankAccountsBalanceBTN"
        ),
        new InitialPanelData(
                "/images/initial/accounts.png",
                "Conciliaciones",
                "conciliationBTN"
        ),
        new InitialPanelData(
                "/images/initial/accounts.png",
                "Informes",
                "reportsBTN"
        ),
        new InitialPanelData(
                "/images/initial/help.png",
                "Ayuda",
                "helpBTN"
        ),
    };
    
    public static BankAccountsInitialViewController create() throws IOException {
        return (BankAccountsInitialViewController) loadInitController(new BankAccountsInitialViewController());
    }

    @Override
    public void init() {
        setTitle("Alfolisoft - Cuentas Bancarias");
        initComponents(data);
    }

    public void handle(ActionEvent event) {
        
    }

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
    
}
