/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mq310.alfolisoft.controllers.countings;

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
public class CountingsMainViewController extends GeneralController{

    public static final String VIEW_NAME = "countingsMainView";
    public static final String VIEW_PATH = AdministratorsViewController.ADMINISTRATORSVIEWNAVIGATOR_PATH + DP + VIEW_NAME;

    public static final String CONTROLLER_NAME = "countingsMainViewController";
    public static final String COUNTINGSMAINVIEWNAVIGATOR_NAME = "countingsMainViewNavigator";
    public static final String COUNTINGSMAINVIEWNAVIGATOR_PATH = VIEW_PATH + DS + CONTROLLER_NAME + DP + COUNTINGSMAINVIEWNAVIGATOR_NAME;
    
    public static CountingsMainViewController create() throws IOException {
        return (CountingsMainViewController) loadController("/fxml/countings/CountingsMainView.fxml");
    }

    @FXML
    private BorderPane mainContent;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @Override
    public void init() {
        mainContent.setCenter(createNavigator(COUNTINGSMAINVIEWNAVIGATOR_NAME));
    }

    public Parent getView() {
        return mainContent;
    }

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}   
    
}
