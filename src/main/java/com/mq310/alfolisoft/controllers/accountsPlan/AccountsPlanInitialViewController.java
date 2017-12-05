/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mq310.alfolisoft.controllers.accountsPlan;

import com.mq310.alfolisoft.controllers.GeneralInitViewController;
import java.io.IOException;

import javafx.event.ActionEvent;

import com.mq310.alfolisoft.views.InitialPanelData;

/**
 * FXML Controller class
 *
 * @author Moises
 */
public class AccountsPlanInitialViewController extends GeneralInitViewController{
    
    public static final String VIEW_NAME = "accountsPlanInitialView";
    public static final String VIEW_PATH = AccountsPlanMainViewController.ACCOUNTSPLANMAINVAVIGATOR_PATH + DP + VIEW_NAME;
    
    private final InitialPanelData[] data = new InitialPanelData[]{
        new InitialPanelData(
                "/images/initial/accountsPlan.png",
                "Maestro de Plan de Cuentas",
                AccountsPlanMasterViewController.VIEW_PATH
        ),
        new InitialPanelData(
                "/images/initial/accountsPlan.png",
                "Balance de Cuentas",
                "accountsBalanceBTN"
        ),
        new InitialPanelData(
                "/images/initial/accountsPlan.png",
                "Informes",
                "reportsBTN"
        ),
        new InitialPanelData(
                "/images/initial/help.png",
                "Ayuda",
                "helpBTN"
        ),
    };
    
    public static AccountsPlanInitialViewController create() throws IOException {
        return (AccountsPlanInitialViewController) loadInitController(new AccountsPlanInitialViewController());
    }

    @Override
    public void init() {
        setTitle("Alfolisoft - Plan de Cuentas");
        initComponents(data);
    }

    public void handle(ActionEvent event) {
        
    }

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
    
}
