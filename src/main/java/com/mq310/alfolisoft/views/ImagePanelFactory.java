/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mq310.alfolisoft.views;

import com.mq310.alfolisoft.MainApp;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author Moises
 */
public class ImagePanelFactory {
    
    public static final String BACKGROUND_1024X768 = "background_1024X768";
    public static final String BACKGROUND_800X600 = "background_800X600";
    public static final String BACKGROUND_512X384 = "background_512X384";
    public static final String BIGADD_400X400 = "bigAdd_400X400";
    public static final String BIGADD_600X600 = "bigAdd_600X600";
    public static final String BIGADD_800X800 = "bigAdd_800X800";
    
    public Parent getBackground_1024X768(){
        return constructImagePanel("/images/Fondo_1024x768.png");
    }
    
    public Parent getBackground_800X600(){
        return constructImagePanel("/images/Fondo_800x600.png");
    }
    
    public Parent getBackground_512X384(){
        return constructImagePanel("/images/Fondo_512x384.png");
    }
    
    public Parent getBigAdd_400X400(){
        return constructImagePanel("/images/bigadd_400x400.png");
    }
    
    public Parent getBigAdd_600X600(){
        return constructImagePanel("/images/bigadd_600x600.png");
    }
    
    public Parent getBigAdd_800X800(){
        return constructImagePanel("/images/bigadd_800x800.png");
    }
    
    private Parent constructImagePanel(String imageUrl){
        BorderPane pane = new BorderPane(new ImageView(MainApp.class.getResource(imageUrl).toString()));
        return pane;
    }
    
}
