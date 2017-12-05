/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mq310.alfolisoft.controllers.editors.forms;

import com.mq310.alfolisoft.controllers.editors.GeneralEditorDialog;
import com.mq310.alfolisoft.controllers.finders.GeneralFinderDialog;
import com.mq310.dao.services.IAccountsGroupService;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import com.mq310.ent.org.accounts.Account;
import com.mq310.ent.org.accounts.AccountsGroup;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;

/**
 * FXML Controller class
 *
 * @author Moises
 */
public class AccountFormEditorController extends GeneralFormController<Account> {

    @FXML
    private TextField nameTF;

    @FXML
    private Label groupLabel;

    @FXML
    private TextArea descriptionTA;

    @FXML
    void addAccountsGroup(ActionEvent event) {
        AccountsGroup group = accountsGroupFinder.findEntity();
        if (group != null) {
            this.entity.setAccountsGroup(group);
        }
    }
    
    @FXML
    void newAccountsGroup(ActionEvent event) {
        accountsGroupEditor.setHeadTitle("Creacion de nuevo grupo de cuentas...");
        AccountsGroup group = accountsGroupEditor.editEntity(new AccountsGroup());
        if (group != null) {
            this.entity.setAccountsGroup(accountsGroupService.saveAccountGroup(group));
        }
    }
    
    private GeneralEditorDialog<AccountsGroup> accountsGroupEditor;
    private GeneralFinderDialog<AccountsGroup> accountsGroupFinder;
    private IAccountsGroupService accountsGroupService;
    
    private ChangeListener<String> changeListener = (ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
        notValidProperty.set(nameTF.getText().trim().isEmpty());
    };

    @Override
    public void setEditableEntity(Account entity) {
        this.entity = entity;
        if (entity != null) {
            nameTF.textProperty().bindBidirectional(this.entity.getNameProperty());
            descriptionTA.textProperty().bindBidirectional(this.entity.getDescriptionProperty());
            entity.getAccountsGroupProperty().addListener((ObservableValue<? extends AccountsGroup> observable, AccountsGroup oldValue, AccountsGroup newValue) -> {
                groupLabel.textProperty().bind(this.entity.getAccountsGroup().getNameProperty());
                validate();
            });
        }
    }
    
    public void validate(){
        notValidProperty.set(
                (groupLabel.getText().isEmpty()) ||
                (nameTF.getText().isEmpty())
        );
    }
    
    public void unBind(){
        Bindings.unbindBidirectional(nameTF.textProperty(), this.entity.getNameProperty());
        Bindings.unbindBidirectional(descriptionTA.textProperty(), this.entity.getDescriptionProperty());
        groupLabel.textProperty().unbind();
    }

    @Override
    public void init() {
        try {
            loadController("/fxml/general/forms/AccountFormEditor.fxml", this);
        } catch (IOException ex) {
            Logger.getLogger(AccountsGroupFormEditorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        nameTF.textProperty().addListener(changeListener);
        notValidProperty.set(true);
    }

    @Override
    public void update() {
    }

    @Override
    public void clear() {
        unBind();
    }

    public void setAccountsGroupFinder(GeneralFinderDialog<AccountsGroup> accountsGroupFinder) {
        this.accountsGroupFinder = accountsGroupFinder;
    }

    public void setAccountsGroupEditor(GeneralEditorDialog<AccountsGroup> accountsGroupEditor) {
        this.accountsGroupEditor = accountsGroupEditor;
    }

    public void setAccountsGroupService(IAccountsGroupService accountsGroupService) {
        this.accountsGroupService = accountsGroupService;
    }
    
}
