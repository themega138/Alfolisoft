/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mq310.alfolisoft.controllers.login;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import com.mq310.alfolisoft.controllers.GeneralController;
import com.mq310.alfolisoft.controllers.MainViewController;

/**
 * FXML Controller class
 *
 * @author Moises
 */
public class LoginMainViewController extends GeneralController {

    public static final String LOGINMAINVIEW_NAME = "loginMainView";
    public static final String LOGINMAINVIEW_PATH = MainViewController.MAINVIEWNAVIGATOR_PATH+DP+LOGINMAINVIEW_NAME;
    
    public static final String LOGINMAINVIEWCONTROLLER_NAME = "loginMainViewController";
    public static final String LOGINMAINNAVIGATOR_NAME = "loginMainNavigator";
    public static final String LOGINMAINNAVIGATOR_PATH = LOGINMAINVIEW_PATH+DS+LOGINMAINVIEWCONTROLLER_NAME+DP+LOGINMAINNAVIGATOR_NAME;

    public static LoginMainViewController create() throws IOException {
        return (LoginMainViewController) loadController("/fxml/login/LoginMainView.fxml");
    }
    
    @FXML
    private StackPane loginMainContent;

    @FXML
    private GridPane gridPane;
    
    @Override
    public void init(){
        gridPane.add(createNavigator(LOGINMAINNAVIGATOR_NAME), 1, 1);
    }

    public Parent getView() {
        return loginMainContent;
    }

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}


}
