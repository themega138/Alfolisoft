/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mq310.alfolisoft.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import org.controlsfx.control.SegmentedButton;

/**
 * FXML Controller class
 *
 * @author Moises
 */
public class SystemMainViewController extends GeneralController implements EventHandler<ActionEvent> {

    public static final String SYSTEMMAINVIEW_NAME = "systemMainView";
    public static final String SYSTEMMAINVIEW_PATH = MainViewController.MAINVIEWNAVIGATOR_PATH + DP + SYSTEMMAINVIEW_NAME;

    public static final String SYSTEMMAINVIEWCONTROLLER_NAME = "systemMainViewController";
    public static final String SYSTEMMAINNAVIGATOR_NAME = "systemMainNavigator";
    public static final String SYSTEMMAINNAVIGATOR_PATH = SYSTEMMAINVIEW_PATH + DS + SYSTEMMAINVIEWCONTROLLER_NAME + DP + SYSTEMMAINNAVIGATOR_NAME;

    public static SystemMainViewController create() throws IOException {
        return (SystemMainViewController) loadController("/fxml/SystemMainView.fxml");
    }

    @FXML
    private ToolBar toolBar;

    @FXML
    private BorderPane mainContent;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    public void onChange(ActionEvent event) {

    }

    @Override
    public void init() {
        mainContent.setCenter(createNavigator(SYSTEMMAINNAVIGATOR_NAME));
        initToolBar();
    }

    public void initToolBar() {
        ToggleButton dashboardBTN = new ToggleButton("Dashboard");
        dashboardBTN.setId("dashboardBTN");
        dashboardBTN.setOnAction(this);
        ToggleButton administratorsBTN = new ToggleButton("Administradores");
        administratorsBTN.setId("administratorsBTN");
        administratorsBTN.setOnAction(this);
        administratorsBTN.setSelected(true);
        ToggleButton configurationBTN = new ToggleButton("Configuracion");
        configurationBTN.setId("configurationBTN");
        configurationBTN.setOnAction(this);
        SegmentedButton segmentedButton = new SegmentedButton(dashboardBTN, administratorsBTN, configurationBTN);
        toolBar.getItems().add(segmentedButton);
    }

    public Parent getView() {
        return mainContent;
    }

    public void handle(ActionEvent event) {
        String id = ((ToggleButton) event.getSource()).getId();
        if ("dashboardBTN".equals(id)) {
            navigateTo(DashboardViewController.DASHBOARDVIEW_PATH);
        } else if ("administratorsBTN".equals(id)) {
            navigateTo(AdministratorsInitialViewController.VIEW_PATH);
        } else if ("configurationBTN".equals(id)) {

        }
    }

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
