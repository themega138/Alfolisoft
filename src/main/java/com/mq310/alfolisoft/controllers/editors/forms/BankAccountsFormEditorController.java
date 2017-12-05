/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mq310.alfolisoft.controllers.editors.forms;

import com.mq310.alfolisoft.controllers.finders.GeneralFinderDialog;
import com.mq310.ent.org.Bank;
import com.mq310.ent.org.BankAccount;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyEvent;

public class BankAccountsFormEditorController extends GeneralFormController<BankAccount> {

    @FXML
    private TextField accountNumberTF;

    @FXML
    private Label bankNameLB;

    @FXML
    private TextArea descriptionTA;

    private GeneralFinderDialog<Bank> bankFinderDialog;

    @FXML
    void onAddBank(ActionEvent event) {
        Bank bank = bankFinderDialog.findEntity();
        if (bank != null) {
            entity.setBank(bank);
            bindEntity();
            validate();
        }
    }

    @Override
    public void setEditableEntity(BankAccount entity) {
        this.entity = entity;
        if (entity != null) {
            bindEntity();
            validate();
        }
    }

    private void bindEntity() {
        accountNumberTF.textProperty().bindBidirectional(entity.getReferenceProperty());
        bankNameLB.textProperty().bind(entity.getBank().getNameProperty());
        descriptionTA.textProperty().bindBidirectional(entity.getDescriptionProperty());
    }

    private void unbind() {
        Bindings.unbindBidirectional(accountNumberTF.textProperty(), entity.getReferenceProperty());
        Bindings.unbindBidirectional(descriptionTA.textProperty(), entity.getDescriptionProperty());
        bankNameLB.textProperty().unbind();
    }

    @Override
    public void clear() {
        unbind();
    }

    @Override
    public void init() {
        try {
            loadController("/fxml/general/forms/BankAccountsFormEditor.fxml", this);
        } catch (IOException ex) {
            Logger.getLogger(AccountsPlanItemFormEditorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        notValidProperty.set(true);
        descriptionTA.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            validate();
        });
        accountNumberTF.addEventHandler(KeyEvent.KEY_TYPED, (KeyEvent e) -> {
            TextField txt_TextField = (TextField) e.getSource();
            if (txt_TextField.getText().length() >= 23 || !e.getCharacter().matches("[0-9-]")) {
                e.consume();
            }
        });
        accountNumberTF.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            validate();
        });
        accountNumberTF.setTooltip(new Tooltip("Ingrese el numero de Cuenta con el formato de chequera..."));
    }

    private void validate() {
        Boolean isValid = (accountNumberTF.getText().length() < 23)
                || (bankNameLB.getText().isEmpty())
                || (descriptionTA.getText().isEmpty());
        notValidProperty.set(isValid);
    }

    @Override
    public void update() {
    }

    public void setBankFinderDialog(GeneralFinderDialog<Bank> bankFinderDialog) {
        this.bankFinderDialog = bankFinderDialog;
    }

}
