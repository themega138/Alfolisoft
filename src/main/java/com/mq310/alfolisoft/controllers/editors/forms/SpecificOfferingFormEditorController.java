/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mq310.alfolisoft.controllers.editors.forms;

import com.mq310.alfolisoft.controllers.finders.GeneralFinderDialog;
import com.mq310.dao.services.IAccountsPlanItemService;
import com.mq310.ent.org.accounts.AccountsPlanItem;
import com.mq310.ent.org.counting.SpecificOffering;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Moises
 */
public class SpecificOfferingFormEditorController extends GeneralFormController<SpecificOffering> {

    @FXML
    private TextField amountTF;

    @FXML
    private Label accountNameLB;
    
    private GeneralFinderDialog<AccountsPlanItem> accountsPlanItemFinder;
    private IAccountsPlanItemService accountsPlanItemService;

    @FXML
    void onAddAccount(ActionEvent event) {
        AccountsPlanItem item = accountsPlanItemFinder.findEntity();
        if (item != null) {
            unBindAccount();
            this.entity.setAccountsPlanItem(accountsPlanItemService.getAccountsPlanItemById(item.getId()));
            bindAccount();
        }

    }

    private ChangeListener<String> changeListener = (ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
        validate();
    };

    @Override
    public void setEditableEntity(SpecificOffering entity) {
        this.entity = entity;
        if (entity != null) {
            bind();
        }
    }

    @Override
    public void init() {
        try {
            loadController("/fxml/general/forms/SpecificOfferingFormEditor.fxml", this);
        } catch (IOException ex) {
            Logger.getLogger(SpecificOfferingFormEditorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        notValidProperty.set(true);

    }

    private void bind() {
        bindAccount();
        amountTF.textProperty().bindBidirectional(this.entity.getAmountProperty(), getConverter());
        this.entity.getAmountProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            validate();
        });
    }

    private void bindAccount() {
        accountNameLB.textProperty().bind(this.entity.getAccountsPlanItem().getAccount().getNameProperty());
    }

    private void unBind() {
        unBindAccount();
        Bindings.unbindBidirectional(amountTF.textProperty(), this.entity.getAmountProperty());
    }

    private void unBindAccount() {
        accountNameLB.textProperty().unbind();
    }

    private void validate() {
        notValidProperty.set(
                (accountNameLB.getText().isEmpty())
                || (this.entity.getAmount() == 0.0)
        );
    }

    @Override
    public void clear() {
        unBind();
    }

    @Override
    public void update() {

    }

    public void setAccountsPlanItemService(IAccountsPlanItemService accountsPlanItemService) {
        this.accountsPlanItemService = accountsPlanItemService;
    }

    public void setAccountsPlanItemFinder(GeneralFinderDialog<AccountsPlanItem> accountsPlanItemFinder) {
        this.accountsPlanItemFinder = accountsPlanItemFinder;
    }
    
}
