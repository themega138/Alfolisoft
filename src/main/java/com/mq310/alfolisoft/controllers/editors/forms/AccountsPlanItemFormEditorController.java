/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mq310.alfolisoft.controllers.editors.forms;

import com.mq310.alfolisoft.controllers.editors.GeneralEditorDialog;
import com.mq310.alfolisoft.controllers.finders.AccountFinderDialog;
import com.mq310.alfolisoft.controllers.finders.GeneralFinderDialog;
import com.mq310.dao.services.IAccountsGroupService;
import com.mq310.ent.org.accounts.Account;
import com.mq310.ent.org.accounts.AccountsPlanItem;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

/**
 * FXML Controller class
 *
 * @author Moises
 */
public class AccountsPlanItemFormEditorController extends GeneralFormController<AccountsPlanItem> {

   @FXML
    private Label descriptionLB;

    @FXML
    private Label groupLB;

    @FXML
    private Label percentLB;

    @FXML
    private Label accountNameLB;

    @FXML
    private Slider percentSL;

    private GeneralFinderDialog<Account> accountFinder;
    private GeneralEditorDialog<Account> accountEditor;
    private IAccountsGroupService accountsGroupService;

    private ChangeListener<String> changeListener = (ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
        notValidProperty.set(accountNameLB.getText().trim().isEmpty());
    };

    @FXML
    void onAddAccount(ActionEvent event) {
        Account account = accountFinder.findEntity();
        if (account != null) {
            this.entity.setAccount(account);
            bindEntity();
        }
    }
    
    @FXML
    void onNewAccount(ActionEvent event) {
        accountEditor.setHeadTitle("Creacion de nueva cuenta...");
        Account account = accountEditor.editEntity(new Account());
        if (account != null) {
            this.entity.setAccount(accountsGroupService.saveAccount(account));
            bindEntity();
        }
    }

    @Override
    public void setEditableEntity(AccountsPlanItem entity) {
        this.entity = entity;
        if (entity != null) {
            bindEntity();
            bindPercent();
        }
    }

    private void bindEntity() {
        accountNameLB.textProperty().bind(this.entity.getAccount().getNameProperty());
        groupLB.textProperty().bind(this.entity.getAccount().getAccountsGroup().getNameProperty());
        descriptionLB.textProperty().bind(this.entity.getAccount().getDescriptionProperty());
    }

    private void unBind() {
        accountNameLB.textProperty().unbind();
        groupLB.textProperty().unbind();
        descriptionLB.textProperty().unbind();
    }

    private void bindPercent() {
        percentSL.setMax(entity.getPercent());
        percentSL.setValue(entity.getPercent());
    }

    @Override
    public void init() {
        try {
            loadController("/fxml/general/forms/AccountsPlanItemFormEditor.fxml", this);
        } catch (IOException ex) {
            Logger.getLogger(AccountsPlanItemFormEditorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        percentSL.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                Double value = (Double) Math.ceil(((Double) newValue) * 10) / 10;
                entity.setPercent(value);
                percentLB.setText(value + "%");
            }
        });
        notValidProperty.set(true);
        accountNameLB.textProperty().addListener(changeListener);
    }

    @Override
    public void update() {
    }

    public void setAccountFinder(AccountFinderDialog accountFinder) {
        this.accountFinder = accountFinder;
    }

    public void setAccountEditor(GeneralEditorDialog<Account> accountEditor) {
        this.accountEditor = accountEditor;
    }

    public void setAccountsGroupService(IAccountsGroupService accountsGroupService) {
        this.accountsGroupService = accountsGroupService;
    }
    
    @Override
    public void clear() {
        unBind();
    }

}
