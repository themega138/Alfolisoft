/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mq310.alfolisoft.controllers;

import com.mq310.alfolisoft.controllers.reports.ReportsInitialViewController;
import com.mq310.alfolisoft.controllers.remittances.RemittancesInitialViewController;
import com.mq310.alfolisoft.controllers.members.MembersInitialViewController;
import com.mq310.alfolisoft.controllers.expenses.ExpensesInitialViewController;
import com.mq310.alfolisoft.controllers.bankAccounts.BankAccountsInitialViewController;
import com.mq310.alfolisoft.controllers.countings.CountingsInitialViewController;
import com.mq310.alfolisoft.controllers.accountsPlan.AccountsPlanInitialViewController;
import com.mq310.alfolisoft.controllers.accounts.AccountsInitialViewController;
import com.mq310.alfolisoft.views.InitialPanelData;
import java.io.IOException;

/**
 * FXML Controller class
 *
 * @author Moises
 */
public class AdministratorsInitialViewController extends GeneralInitViewController {

    private InitialPanelData[] data = new InitialPanelData[]{
        new InitialPanelData(
        "/images/initial/countings.png",
        "Conteos",
        CountingsInitialViewController.VIEW_PATH
        ),
        new InitialPanelData(
        "/images/initial/remittanses.png",
        "Remesas",
        RemittancesInitialViewController.VIEW_PATH
        ),
        new InitialPanelData(
        "/images/initial/accounts.png",
        "Cuentas",
        AccountsInitialViewController.VIEW_PATH
        ),
        new InitialPanelData(
        "/images/initial/accountsPlan.png",
        "Plan de Cuentas",
        AccountsPlanInitialViewController.VIEW_PATH
        ),
        new InitialPanelData(
        "/images/initial/expenses.png",
        "Gastos",
        ExpensesInitialViewController.VIEW_PATH
        ),
        new InitialPanelData(
        "/images/initial/reports.png",
        "Informes",
        ReportsInitialViewController.VIEW_PATH
        ),
        new InitialPanelData(
        "/images/initial/members.png",
        "Miembros",
        MembersInitialViewController.VIEW_PATH
        ),
        new InitialPanelData(
        "/images/initial/bankAccounts.png",
        "Cuentas Bancarias",
        BankAccountsInitialViewController.VIEW_PATH
        ),
        new InitialPanelData(
        "/images/initial/help.png",
        "Ayuda",
        AdministratorsInitialViewController.VIEW_PATH
        )
    };
    public static final String VIEW_NAME = "administratorsInitialView";
    public static final String VIEW_PATH = AdministratorsViewController.ADMINISTRATORSVIEWNAVIGATOR_PATH + DP + VIEW_NAME;

    public static AdministratorsInitialViewController create() throws IOException {
        return (AdministratorsInitialViewController) loadInitController(new AdministratorsInitialViewController());
    }

    @Override
    public void init() {
        setTitle("Alfolisoft - Inicio");
        initComponents(data);
    }

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
}
