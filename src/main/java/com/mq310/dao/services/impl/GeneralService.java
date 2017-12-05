package com.mq310.dao.services.impl;

import com.mq310.alfolisoft.MainApp;
import com.mq310.dao.Dao;
import javafx.scene.image.ImageView;
import org.springframework.transaction.support.TransactionTemplate;

public abstract class GeneralService {

    protected Dao dao;
    protected TransactionTemplate txTemplate;

    public void setDao(Dao dao) {
        this.dao = dao;
    }

    public void setTxTemplate(TransactionTemplate txTemplate) {
        this.txTemplate = txTemplate;
    }
    
    protected ImageView loadIcon(String url) {
        return new ImageView(MainApp.class.getResource(url).toString());
    }

}
