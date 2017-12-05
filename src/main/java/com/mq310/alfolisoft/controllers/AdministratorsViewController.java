/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mq310.alfolisoft.controllers;

import com.mq310.alfolisoft.controllers.reports.ReportsInitialViewController;
import com.mq310.alfolisoft.controllers.remittances.RemittancesInitialViewController;
import com.mq310.alfolisoft.controllers.members.MembersInitialViewController;
import com.mq310.alfolisoft.controllers.expenses.ExpensesInitialViewController;
import com.mq310.alfolisoft.controllers.countings.CountingsInitialViewController;
import com.mq310.alfolisoft.controllers.accountsPlan.AccountsPlanInitialViewController;
import com.mq310.alfolisoft.controllers.accounts.AccountsInitialViewController;
import com.mq310.alfolisoft.controllers.bankAccounts.BankAccountsInitialViewController;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import org.controlsfx.control.PopOver;

/**
 * FXML Controller class
 *
 * @author Moises
 */
public class AdministratorsViewController extends GeneralController implements EventHandler<MouseEvent> {

    public static final String ADMINISTRATORSVIEW_NAME = "administratorsView";
    public static final String ADMINISTRATORSVIEW_PATH = SystemMainViewController.SYSTEMMAINNAVIGATOR_PATH + DP + ADMINISTRATORSVIEW_NAME;

    public static final String ADMINISTRATORSVIEWCONTROLLER_NAME = "administratorsViewController";
    public static final String ADMINISTRATORSVIEWNAVIGATOR_NAME = "administratorsViewNavigator";
    public static final String ADMINISTRATORSVIEWNAVIGATOR_PATH = ADMINISTRATORSVIEW_PATH + DS + ADMINISTRATORSVIEWCONTROLLER_NAME + DP + ADMINISTRATORSVIEWNAVIGATOR_NAME;

    public static AdministratorsViewController create() throws IOException {
        return (AdministratorsViewController) loadController("/fxml/AdministratorsView.fxml");
    }

    @FXML
    private BorderPane mainContent;

    private PopOver popOver;

    private Parent startMenu;

    @FXML
    private Button startBTN;

    @Override
    public void init() {
        mainContent.setCenter(createNavigator(ADMINISTRATORSVIEWNAVIGATOR_NAME));
        startBTN.setGraphic(new ImageView(new Image("/images/menu.png")));
        createPopOverContent();
    }

    public Parent getView() {
        return mainContent;
    }

    @FXML
    public void onStart(ActionEvent event) {
        if (popOver != null && popOver.isShowing()) {
            popOver.hide();
        } else {
            popOver = createPopOver();
            popOver.show(startBTN);
        }
    }

    private PopOver createPopOver() {
        PopOver pop = new PopOver();
        pop.setAutoHide(true);
        pop.setDetachable(true);
        pop.setDetached(false);
        pop.arrowSizeProperty().set(12);
        pop.arrowIndentProperty().set(12);
        pop.arrowLocationProperty().set(PopOver.ArrowLocation.LEFT_TOP);
        pop.cornerRadiusProperty().set(25);
        pop.setContentNode(startMenu);
        return pop;
    }

    private void createPopOverContent() {
        TilePane tilepane = new TilePane(Orientation.HORIZONTAL);
        tilepane.setPrefColumns(3);
        tilepane.setPrefRows(3);
        tilepane.setPadding(new Insets(10));
        tilepane.setVgap(10);
        tilepane.setHgap(10);

        StartMenuImageData[] images = new StartMenuImageData[]{
            new StartMenuImageData("/images/start.png", "startImage", "Inicio"),
            new StartMenuImageData("/images/countings.png", "countingsImage", "Conteos"),
            new StartMenuImageData("/images/expenses.png", "expensesImage", "Gastos"),
            new StartMenuImageData("/images/remittanses.png", "remittansesImage", "Remesas"),
            new StartMenuImageData("/images/accounts.png", "accountsImage", "Cuentas"),
            new StartMenuImageData("/images/accountsPlan.png", "accountsPlanImage", "Plan de Cuentas"),
            new StartMenuImageData("/images/bankAccounts.png", "bankAccountsImage", "Cuentas Bancarias"),
            new StartMenuImageData("/images/reports.png", "reportsImage", "Informes"),
            new StartMenuImageData("/images/members.png", "membersImage", "Miembros")
        };

        for (int i = 0; i < images.length; i++) {
            VBox box = new VBox();
            box.setAlignment(Pos.BOTTOM_CENTER);
            box.setSpacing(10);
            box.setPadding(new Insets(5, 5, 5, 5));
            box.setEffect(new DropShadow(15, Color.SKYBLUE) );
            ImageView image = new ImageView(new Image(images[i].getUrl()));
            image.setId(images[i].getId());
            image.setOnMouseClicked(this);
            image.setEffect(new DropShadow(15, Color.BLACK));
            image.setCursor(Cursor.HAND);
            box.getChildren().add(image);
            box.getChildren().add(new Label(images[i].getLabel()));
            tilepane.getChildren().add(box);
        }

        startMenu = tilepane;
    }

    public void handle(MouseEvent event) {
        popOver.hide();
        String id = ((ImageView) event.getSource()).getId();
        if ("startImage".equals(id)) {
            navigateTo(AdministratorsInitialViewController.VIEW_PATH);
        } else if ("countingsImage".equals(id)) {
            navigateTo(CountingsInitialViewController.VIEW_PATH);
        } else if ("expensesImage".equals(id)) {
            navigateTo(ExpensesInitialViewController.VIEW_PATH);
        } else if ("remittansesImage".equals(id)) {
            navigateTo(RemittancesInitialViewController.VIEW_PATH);
        } else if ("accountsImage".equals(id)) {
            navigateTo(AccountsInitialViewController.VIEW_PATH);
        } else if ("accountsPlanImage".equals(id)) {
            navigateTo(AccountsPlanInitialViewController.VIEW_PATH);
        } else if ("bankAccountsImage".equals(id)) {
            navigateTo(BankAccountsInitialViewController.VIEW_PATH);
        } else if ("reportsImage".equals(id)) {
            navigateTo(ReportsInitialViewController.VIEW_PATH);
        } else if ("membersImage".equals(id)) {
            navigateTo(MembersInitialViewController.VIEW_PATH);
        }
    }

    private class StartMenuImageData {

        private String url;

        private String id;

        private String label;

        public StartMenuImageData(String url, String id, String label) {
            this.url = url;
            this.id = id;
            this.label = label;
        }

        public String getId() {
            return id;
        }

        public String getUrl() {
            return url;
        }

        public String getLabel() {
            return label;
        }

    }

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
