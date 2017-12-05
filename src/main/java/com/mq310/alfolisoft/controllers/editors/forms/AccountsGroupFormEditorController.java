/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mq310.alfolisoft.controllers.editors.forms;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import com.mq310.ent.org.accounts.AccountsGroup;

/**
 * FXML Controller class
 *
 * @author Moises
 */
public class AccountsGroupFormEditorController extends GeneralFormController<AccountsGroup> {

    @FXML
    private TextField nameTextField;
    @FXML
    private TextArea descriptionTextArea;

    private final ChangeListener<String> changeListener
            = (ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                notValidProperty.set(nameTextField.getText().trim().isEmpty());
            };

    @Override
    public void init() {
        try {
            loadController("/fxml/general/forms/AccountsGroupFormEditor.fxml", this);
        } catch (IOException ex) {
            Logger.getLogger(AccountsGroupFormEditorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        notValidProperty.set(true);
        nameTextField.textProperty().addListener(changeListener);
    }

    @Override
    public void setEditableEntity(AccountsGroup entity) {
        super.entity = entity;
        nameTextField.textProperty().bindBidirectional(super.entity.getNameProperty());
        descriptionTextArea.textProperty().bindBidirectional(super.entity.getDescriptionProperty());
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub

    }

    @Override
    public void clear() {
    }

}
