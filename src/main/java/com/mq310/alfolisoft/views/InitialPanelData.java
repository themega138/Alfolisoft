/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mq310.alfolisoft.views;

/**
 *
 * @author Moises
 */
public class InitialPanelData {
    
    private String lorem = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam eleifend eleifend aliquam. Curabitur in lorem quis orci dignissim dictum non non elit. Cras ipsum felis, consequat vel diam ut, ornare elementum odio. Curabitur a faucibus lectus, id vulputate risus. Phasellus facilisis eleifend pulvinar. Vivamus vel nisi nibh. Sed ornare arcu nec enim iaculis dapibus. ";
    
    private String url;
    
    private String label;
    
    private String info;
    
    private String path;
    
    public InitialPanelData(String url, String label, String path) {
        this.url = url;
        this.label = label;
        this.info = lorem;
        this.path = path;
    }

    public InitialPanelData(String url, String label, String info, String path) {
        this.url = url;
        this.label = label;
        this.info = info;
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public String getInfo() {
        return info;
    }

    public String getLabel() {
        return label;
    }

    public String getUrl() {
        return url;
    }
    
}
