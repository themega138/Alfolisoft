/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mq310.alfolisoft.controllers.editors.forms;

import com.mq310.alfolisoft.controllers.GeneralController;
import com.mq310.ent.BaseEntity;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ResourceBundle;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.Parent;

public abstract class GeneralFormController<E extends BaseEntity> extends GeneralController {

    @FXML
    protected Parent mainContent;

    protected BooleanProperty notValidProperty = new SimpleBooleanProperty(false);

    protected E entity;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public Parent getView() {
        return mainContent;
    }

    public abstract void setEditableEntity(E entity);

    public E getEditedEntity() {
        return entity;
    }

    public BooleanProperty getNotValidProperty() {
        return notValidProperty;
    }

    public abstract void clear();

}
