/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mq310.alfolisoft.controllers.expenses;

import com.mq310.alfolisoft.controllers.GeneralInitViewController;
import com.mq310.alfolisoft.views.InitialPanelData;
import java.io.IOException;

/**
 * FXML Controller class
 *
 * @author Moises
 */
public class ExpensesInitialViewController extends GeneralInitViewController{
    
    public static final String VIEW_NAME = "expensesInitialView";
    public static final String VIEW_PATH = ExpensesMainViewController.EXPENSESMAINVIEWNAVIGATOR_PATH + DP + VIEW_NAME;
    
    private InitialPanelData[] data = new InitialPanelData[]{
        new InitialPanelData(
                "/images/initial/accounts.png",
                "Maestro de Gastos",
                "masterExpensesBTN"
        ),
        new InitialPanelData(
                "/images/initial/help.png",
                "Ayuda",
                "helpBTN"
        ),
    };
    
    public static ExpensesInitialViewController create() throws IOException {
        return (ExpensesInitialViewController) loadInitController(new ExpensesInitialViewController());
    }

    @Override
    public void init() {
        setTitle("Alfolisoft - Gastos");
        initComponents(data);
    }

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
