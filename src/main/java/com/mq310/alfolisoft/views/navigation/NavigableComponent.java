package com.mq310.alfolisoft.views.navigation;

import java.util.HashMap;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class NavigableComponent  extends StackPane implements ApplicationContextAware{

    private ApplicationContext context;
    
    private String actualView = "";
    
    private final HashMap<String, Node> screens = new HashMap<String, Node>();
    
    public NavigableComponent() {
        super();
    }

    public void addScreen(String name, Node screen) {
    	if (!screens.containsKey(screen)) {
    		screens.put(name, screen);
		}
    }
    
    public Boolean containScreen(String name){
        return screens.containsKey(name);
    }

    public boolean showScreen(final String name) {
        if (!containScreen(name)) {
            addScreen(name, (Node) context.getBean(name));
        }
        if ((screens.get(name) != null) && (!actualView.equals(name))) {   //screen loaded
            final DoubleProperty opacity = opacityProperty();

            if (!getChildren().isEmpty()) {    //if there is more than one screen
                Timeline fade = new Timeline(
                        new KeyFrame(Duration.ZERO, new KeyValue(opacity, 1.0)),
                        new KeyFrame(new Duration(500), new EventHandler<ActionEvent>() {
                            public void handle(ActionEvent t) {
                                getChildren().remove(0);                    //remove the displayed screen
                                getChildren().add(0, screens.get(name));     //add the screen
                                Timeline fadeIn = new Timeline(
                                        new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
                                        new KeyFrame(new Duration(500), new KeyValue(opacity, 1.0)));
                                fadeIn.play();
                            }
                        }, new KeyValue(opacity, 0.0)));
                fade.play();

            } else {
                setOpacity(0.0);
                getChildren().add(screens.get(name));       //no one else been displayed, then just show
                Timeline fadeIn = new Timeline(
                        new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
                        new KeyFrame(new Duration(1000), new KeyValue(opacity, 1.0)));
                fadeIn.play();
            }
            actualView = name;
            return true;
        } else {
            return false;
        }
    }

    public boolean unloadScreen(String name) {
        if (screens.remove(name) == null) {
            return false;
        } else {
            return true;
        }
    }

    public void setApplicationContext(ApplicationContext ac) throws BeansException {
        this.context = ac;
    }
}

