/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mq310.alfolisoft.controllers;

import com.mq310.alfolisoft.views.InitialPanelData;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import org.controlsfx.control.InfoOverlay;

/**
 *
 * @author Moises
 */
public abstract class GeneralInitViewController extends GeneralController{

    @FXML
    private BorderPane mainContent;
    
    @FXML
    private TilePane tilePane;

    @FXML
    private Label title;
    
    protected void setTitle(String title){
        this.title.setText(title);
    }

    
    protected void initComponents(InitialPanelData[] data) {
        for (InitialPanelData initialPanelData : data) {
            tilePane.getChildren().add(createInfoOverlay(initialPanelData));
        }
    }

    protected InfoOverlay createInfoOverlay(final InitialPanelData data) {
        VBox box = new VBox();
        box.setAlignment(Pos.BASELINE_CENTER);
        box.setEffect(new DropShadow(25, Color.BLACK));
        box.setPadding(new Insets(10, 10, 60, 10));
        box.setSpacing(5);
        ImageView image = new ImageView(new Image(data.getUrl()));
        image.setFitHeight(150);
        image.setFitWidth(150);
        image.setPreserveRatio(false);
        box.getChildren().add(image);
        Button btn = new Button(data.getLabel());
        btn.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                navigateTo(data.getPath());
            }
        });
        box.getChildren().add(btn);
        InfoOverlay info = new InfoOverlay(box, data.getInfo());
        info.showOnHoverProperty().set(false);
        return info;
    }
    
    protected static Object loadInitController(Object controller) throws IOException{
        return loadController("/fxml/GeneralInitialView.fxml",controller);
    }
    
    public Parent getView() {
        return mainContent;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
    }
}
