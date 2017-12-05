package com.mq310.alfolisoft.views.navigation;

import org.springframework.context.ApplicationEvent;

public class NavigationEvent extends ApplicationEvent {

    /**
     *
     */
    private static final long serialVersionUID = 8580550951701372053L;

    String path;

    public NavigationEvent(Object source, String path) {
        super(source);
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
