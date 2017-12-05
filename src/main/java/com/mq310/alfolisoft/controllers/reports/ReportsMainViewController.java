/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mq310.alfolisoft.controllers.reports;

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
public class ReportsMainViewController extends GeneralController{

    public static final String VIEW_NAME = "reportsMainView";
    public static final String VIEW_PATH = AdministratorsViewController.ADMINISTRATORSVIEWNAVIGATOR_PATH + DP + VIEW_NAME;

    public static final String CONTROLLER_NAME = "reportsMainViewController";
    public static final String REPORTSMAINVIEWNAVIGATOR_NAME = "reportsMainViewNavigator";
    public static final String REPORTSMAINVIEWNAVIGATOR_PATH = VIEW_PATH + DS + CONTROLLER_NAME + DP + REPORTSMAINVIEWNAVIGATOR_NAME;

    public static ReportsMainViewController create() throws IOException {
        return (ReportsMainViewController) loadController("/fxml/reports/ReportsMainView.fxml");
    }

    @FXML
    private BorderPane mainContent;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @Override
    public void init() {
        mainContent.setCenter(createNavigator(REPORTSMAINVIEWNAVIGATOR_NAME));
    }

    public Parent getView() {
        return mainContent;
    }

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
    
}
