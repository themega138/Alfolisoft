/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mq310.alfolisoft.controllers.members;

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
public class MembersMainViewController extends GeneralController{

    public static final String VIEW_NAME = "membersMainView";
    public static final String VIEW_PATH = AdministratorsViewController.ADMINISTRATORSVIEWNAVIGATOR_PATH + DP + VIEW_NAME;

    public static final String CONTROLLER_NAME = "membersMainViewController";
    public static final String MEMBERSMAINVIEWNAVIGATOR_NAME = "membersMainViewNavigator";
    public static final String MEMBERSMAINVIEWNAVIGATOR_PATH = VIEW_PATH + DS + CONTROLLER_NAME + DP + MEMBERSMAINVIEWNAVIGATOR_NAME;

    public static MembersMainViewController create() throws IOException {
        return (MembersMainViewController) loadController("/fxml/members/MembersMainView.fxml");
    }
    
    @FXML
    private BorderPane mainContent;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @Override
    public void init() {
        mainContent.setCenter(createNavigator(MEMBERSMAINVIEWNAVIGATOR_NAME));
    }

    public Parent getView() {
        return mainContent;
    }

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}   
    
}
