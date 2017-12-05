/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mq310.alfolisoft.controllers.reports;

import com.mq310.alfolisoft.controllers.GeneralInitViewController;
import java.io.IOException;

import javafx.event.ActionEvent;

/**
 * FXML Controller class
 *
 * @author Moises
 */
public class ReportsInitialViewController extends GeneralInitViewController{
    
    public static final String VIEW_NAME = "reportsInitialView";
    public static final String VIEW_PATH = ReportsMainViewController.REPORTSMAINVIEWNAVIGATOR_PATH + DP + VIEW_NAME;
    

    public static ReportsInitialViewController create() throws IOException {
        return (ReportsInitialViewController) loadInitController(new ReportsInitialViewController());
    }
    
    @Override
    public void init() {
        setTitle("Alfolisoft - Reportes");
    }

    public void handle(ActionEvent event) {
        
    }

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
}
