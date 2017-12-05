/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mq310.alfolisoft.controllers.login;

import com.mq310.alfolisoft.controllers.AdministratorsInitialViewController;
import com.mq310.alfolisoft.controllers.AdministratorsViewController;
import com.mq310.alfolisoft.controllers.DashboardViewController;
import com.mq310.alfolisoft.controllers.GeneralController;
import com.mq310.alfolisoft.controllers.accountsPlan.AccountsPlanMasterViewController;
import com.mq310.alfolisoft.managers.SecurityManager;
import com.mq310.ent.sec.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.security.core.AuthenticationException;

/**
 * FXML Controller class
 *
 * @author Moises
 */
public class LoginFormViewController extends GeneralController {

    public static final String LOGINFORMVIEW_NAME = "loginFormView";
    public static final String LOGINFORMVIEW_PATH = LoginMainViewController.LOGINMAINNAVIGATOR_PATH + DP + LOGINFORMVIEW_NAME;

    public static LoginFormViewController create() throws IOException {
        return (LoginFormViewController) loadController("/fxml/login/LoginFormView.fxml");
    }

    @FXML
    private PasswordField password;

    @FXML
    private ImageView userImage;

    @FXML
    private GridPane formView;

    @FXML
    private TextField username;
    
    Button btnYes, btnNo;

    private SecurityManager securityManager;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //userImage.setImage(new Image(MainApp.class.getResourceAsStream("/images/user.jpg")));
    }

    @FXML
    void onLogin(ActionEvent event) {
        User user = new User();
        user.setUsername(username.getText());
        user.setPassword(password.getText());
        navigateTo(AdministratorsInitialViewController.VIEW_PATH);
        
//        try {
//            securityManager.authenticate(user);
//            navigateTo(DashboardViewController.DASHBOARDVIEW_PATH);
//        } catch (AuthenticationException e) {
//            password.setPromptText(e.getMessage());
//            username.setFocusTraversable(true);
//            password.setText("");
//        }
    }

    public Parent getView() {
        return formView;
    }

    @Override
    public void init() {

    }

    public void setSecurityManager(SecurityManager securityManager) {
        this.securityManager = securityManager;
    }

    @Override
    public void update() {
		// TODO Auto-generated method stub

    }

}
