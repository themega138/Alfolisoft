/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mq310.alfolisoft.controllers.finders;

import com.mq310.alfolisoft.MainApp;
import com.mq310.dao.Dao;
import com.mq310.ent.BaseEntity;
import java.io.IOException;
import java.io.InputStream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.ImageView;
import org.controlsfx.control.ButtonBar;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.DefaultDialogAction;
import org.controlsfx.dialog.Dialog;

/**
 *
 * @author Moises
 */
public abstract class GeneralFinderDialog<E extends BaseEntity> {

    protected String headTitle = "";
    protected String dialogTitle = "Buscador";
    protected Dao dao;
    private Dialog dialog;
    protected String imageUrl = "";
    protected SelectionMode selectionMode;
    protected ObservableList<E> entitiesList = FXCollections.observableArrayList();

    private final Action actionSelection = new DefaultDialogAction("Seleccionar",
            Dialog.ActionTrait.CLOSING, Dialog.ActionTrait.DEFAULT) {
                {
                    ButtonBar.setType(this, ButtonBar.ButtonType.OK_DONE);
                }

                @Override
                public void handle(ActionEvent ae) {
                    Dialog dlg = (Dialog) ae.getSource();
                    dlg.setResult(this);
                }

                public String toString() {
                    return "SELECT";
                }
            ;

    };
    
    protected ImageView loadIcon(String url) {
        return new ImageView(MainApp.class.getResource(url).toString());
    }

    private void init() {
        dialog = new Dialog(null, dialogTitle, false);
        if (!imageUrl.equals("")) {
            dialog.setGraphic(new ImageView(MainApp.class.getResource(imageUrl)
                    .toString()));
        }
        dialog.setContent(createContent());
        dialog.setMasthead(headTitle);
        dialog.setResizable(false);
        dialog.setIconifiable(false);
        dialog.getActions().addAll(actionSelection);
    }

    public E findEntity() {
        init();
        loadEntities();
        Action action = dialog.show();
        if (action != null) {
            if (getSelection() != null) {
                return (E) dao.getDeatached(getSelection().getClass(), getSelection().getId());
            } else {
                return null;
            }
        } else {
            return null;
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

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setDao(Dao dao) {
        this.dao = dao;
    }
    
    protected abstract Parent createContent();
    
    public abstract void loadEntities();

    protected abstract E getSelection();
}
