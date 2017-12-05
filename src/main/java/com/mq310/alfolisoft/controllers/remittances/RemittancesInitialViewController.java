/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mq310.alfolisoft.controllers.remittances;

import com.mq310.alfolisoft.controllers.GeneralInitViewController;
import java.io.IOException;
import javafx.event.ActionEvent;

/**
 * FXML Controller class
 *
 * @author Moises
 */
public class RemittancesInitialViewController extends GeneralInitViewController {

    public static final String VIEW_NAME = "remittancesInitialView";
    public static final String VIEW_PATH = RemittancesMainViewController.REMITTANCESMAINVIEWNAVIGATOR_PATH + DP + VIEW_NAME;

    public static RemittancesInitialViewController create() throws IOException {
        return (RemittancesInitialViewController) loadInitController(new RemittancesInitialViewController());
    }

    @Override
    public void init() {
        setTitle("Alfolisoft - Remesas");
    }

    public void handle(ActionEvent event) {

    }

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
