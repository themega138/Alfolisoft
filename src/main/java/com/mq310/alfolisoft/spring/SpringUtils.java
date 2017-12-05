/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mq310.alfolisoft.spring;

import com.mq310.alfolisoft.MainApp;
import com.mq310.alfolisoft.managers.InitializerManager;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Moises
 */
public class SpringUtils {
    
    private static ApplicationContext context;
    
    
    
    public static void start(Stage stage){
        
        context = new ClassPathXmlApplicationContext(new String[]{
            "classpath:/appContext.xml",
            "classpath:/hibernateContext.xml",
            "classpath:/daoServicesContext.xml",
            "classpath:/securityContext.xml",
            "classpath:/utilContext.xml",
            "classpath:/controllersContext.xml",
            "classpath:/modelsContext.xml",
            "classpath:/viewsContext.xml"
        });
        InitializerManager initManager = context.getBean(InitializerManager.class);
        if (initManager.init()) {
            Parent p = (Parent) context.getBean("mainView");
            p.minHeight(600);
            p.minWidth(800);
            Scene scene = new Scene(p, 1024, 668);
            stage.setScene(scene);
            stage.show();
        }
        
    }
    
    public static ApplicationContext getContext(){
        return context;
    }
    
    
    
}
