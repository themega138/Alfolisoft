/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mq310.alfolisoft.controllers.countings;

import com.mq310.alfolisoft.controllers.GeneralInitViewController;
import java.io.IOException;

import javafx.event.ActionEvent;

import com.mq310.alfolisoft.views.InitialPanelData;

/**
 * FXML Controller class
 *
 * @author Moises
 */
public class CountingsInitialViewController extends GeneralInitViewController{
    
    public static final String VIEW_NAME = "countingsInitialView";
    public static final String VIEW_PATH = CountingsMainViewController.COUNTINGSMAINVIEWNAVIGATOR_PATH + DP + VIEW_NAME;

    private InitialPanelData[] data = new InitialPanelData[]{
        new InitialPanelData(
                "/images/initial/countings.png",
                "Nuevo Conteo",
                CountingsCreationViewController.VIEW_PATH
        ),
        new InitialPanelData(
                "/images/initial/countings.png",
                "Maestro de Conteos",
                CountingsMasterViewController.VIEW_PATH
        ),
        new InitialPanelData(
                "/images/initial/countings.png",
                "Informes",
                "reportsBTN"
        ),
        new InitialPanelData(
                "/images/initial/help.png",
                "Ayuda",
                "helpBTN"
        ),
    };

    public static CountingsInitialViewController create() throws IOException {
        return (CountingsInitialViewController) loadInitController(new CountingsInitialViewController());
    }
    
    @Override
    public void init() {
        setTitle("Alfolisoft - Conteos");
        initComponents(data);
    }

    public void handle(ActionEvent event) {
        
    }

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

      
    
}
