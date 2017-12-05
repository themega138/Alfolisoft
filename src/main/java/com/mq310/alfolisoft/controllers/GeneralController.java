/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mq310.alfolisoft.controllers;

import com.mq310.alfolisoft.MainApp;
import com.mq310.alfolisoft.controllers.exceptions.UnloadedNavigatorException;
import com.mq310.alfolisoft.views.IVIewProvider;
import com.mq310.alfolisoft.views.navigation.NavigableComponent;
import com.mq310.alfolisoft.views.navigation.NavigationEvent;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.util.StringConverter;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

/**
 *
 * @author Moises
 */
public abstract class GeneralController implements ApplicationContextAware, Initializable, IVIewProvider, ApplicationEventPublisherAware {

    protected static final String DS = "/";
    protected static final String DP = ":";
    private ApplicationContext context;
    private Map<String, NavigableComponent> navigators = new HashMap<String, NavigableComponent>();
    private ApplicationEventPublisher eventPublisher;
    protected NumberFormat formatter = new DecimalFormat("#0.00");

    public void setApplicationContext(ApplicationContext ac) throws BeansException {
        this.context = ac;
    }

    public void setApplicationEventPublisher(ApplicationEventPublisher aep) {
        this.eventPublisher = aep;
    }

    /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public NavigableComponent getNavigator(String name) throws UnloadedNavigatorException {
        if (navigators.containsKey(name)) {
            return navigators.get(name);
        } else {
            throw new UnloadedNavigatorException(name);
        }
    }

    protected static Object loadController(String url) throws IOException {
        InputStream fxmlStream = null;
        try {
            fxmlStream = MainApp.class.getResourceAsStream(url);
            FXMLLoader loader = new FXMLLoader();
            loader.load(fxmlStream);
            return loader.getController();
        } finally {
            if (fxmlStream != null) {
                fxmlStream.close();
            }
        }
    }

    protected static Object loadController(String url, Object controller) throws IOException {
        InputStream fxmlStream = null;
        try {
            fxmlStream = MainApp.class.getResourceAsStream(url);
            FXMLLoader loader = new FXMLLoader();
            loader.setController(controller);
            loader.load(fxmlStream);
            return loader.getController();
        } finally {
            if (fxmlStream != null) {
                fxmlStream.close();
            }
        }
    }

    protected ImageView loadIcon(String url) {
        return new ImageView(MainApp.class.getResource(url).toString());
    }

    protected NavigableComponent createNavigator(String name) {
        NavigableComponent navigator = context.getBean(NavigableComponent.class);
        navigators.put(name, navigator);
        return navigators.get(name);
    }

    protected void navigateTo(String path) {
        eventPublisher.publishEvent(new NavigationEvent(this, path));
    }

    protected EventHandler<KeyEvent> numeric_Validation(final Integer max_Lengh) {
        return new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                TextField txt_TextField = (TextField) e.getSource();
                if (txt_TextField.getText().length() >= max_Lengh) {
                    e.consume();
                }
                if (e.getCharacter().matches("[0-9.]")) {
                    if (txt_TextField.getText().contains(".") && e.getCharacter().matches("[.]")) {
                        e.consume();
                    } else if (txt_TextField.getText().length() == 0 && e.getCharacter().matches("[.]")) {
                        e.consume();
                    }
                } else {
                    e.consume();
                }
            }
        };
    }

    protected StringConverter<Number> getConverter() {

        return new StringConverter<Number>() {

            @Override
            public String toString(Number object) {
                return object.toString();
            }

            @Override
            public Number fromString(String string) {
                Double result = 0.0;

                if ((string != null) && (!string.isEmpty())) {

                    result = Double.parseDouble(string.replace(',', '.'));
                }
                return result;
            }
        };

    }

    public abstract void init();

    public abstract void update();

}
