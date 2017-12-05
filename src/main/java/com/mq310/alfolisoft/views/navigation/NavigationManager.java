package com.mq310.alfolisoft.views.navigation;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;

import com.mq310.alfolisoft.controllers.GeneralController;
import com.mq310.alfolisoft.controllers.exceptions.UnloadedControllerException;
import com.mq310.alfolisoft.controllers.exceptions.UnloadedNavigatorException;
import com.mq310.alfolisoft.controllers.login.LoginFormViewController;

public class NavigationManager implements ApplicationContextAware, ApplicationListener<NavigationEvent> {

    private ApplicationContext context;

    private void navigateTo(String navpath) throws UnloadedControllerException {
        String[] paths = navpath.split("/");
        GeneralController controller; NavigableComponent nav;
        String contName; String navName; String viewName;
        for (String path : paths) {
            contName = path.split(":")[0];
            navName = path.split(":")[1];
            viewName = path.split(":")[2];
            if (context.containsBean(contName)) {
                controller = context.getBean(contName, GeneralController.class);
            } else {
                return;
            }
            try {
                nav = controller.getNavigator(navName);
            } catch (UnloadedNavigatorException ex) {
                Logger.getLogger(NavigationManager.class.getName()).log(Level.SEVERE, null, ex);
                return;
            }
            nav.showScreen(viewName);
        }
    }
    
    public void init() throws UnloadedControllerException{
        navigateTo(LoginFormViewController.LOGINFORMVIEW_PATH);
    }

    public void onApplicationEvent(NavigationEvent event) {
        try {
            navigateTo(event.getPath());
        } catch (UnloadedControllerException ex) {
            Logger.getLogger(NavigationManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setApplicationContext(ApplicationContext ac) throws BeansException {
        this.context = ac;
    }

}
