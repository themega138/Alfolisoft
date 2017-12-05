/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mq310.alfolisoft.controllers.bankAccounts;

import com.mq310.alfolisoft.controllers.GeneralController;
import com.mq310.alfolisoft.controllers.editors.GeneralEditorDialog;
import com.mq310.dao.services.IBankAccountsService;
import com.mq310.ent.BaseEntity;
import com.mq310.ent.org.Bank;
import com.mq310.ent.org.BankAccount;
import com.mq310.ent.org.accounts.movements.Movement;
import com.mq310.ent.org.counting.docs.BankDocument;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class BankAccountsMasterViewController extends GeneralController {

    public static final String VIEW_NAME = "bankAccountsMasterView";
    public static final String VIEW_PATH = BankAccountsMainViewController.BANKACCOUNTSMAINVIEWNAVIGATOR_PATH + DP + VIEW_NAME;

    public static BankAccountsMasterViewController create() throws IOException {
        return (BankAccountsMasterViewController) loadController("/fxml/bankAccounts/BankAccountsMasterView.fxml");
    }
    
    @FXML
    private ImageView banksLogo;

    @FXML
    private TableView<Movement> movementsTable;

    @FXML
    private TableView<BankDocument> documentsTB;

    @FXML
    private AnchorPane mainContent;

    @FXML
    private Label accountNumberLB;

    @FXML
    private Label bankNameLB;

    @FXML
    private ListView<Bank> banksLisView;

    @FXML
    private TreeView<BaseEntity> bankAccountsTreeView;
    
    private GeneralEditorDialog<BankAccount> bankAccountsEditor;
    private final ObservableList<Bank> bankList = FXCollections.observableArrayList();
    private final ObservableList<BankAccount> bankAccounts = FXCollections.observableArrayList();
    
    private IBankAccountsService service;

    @FXML
    void onEditAccount(ActionEvent event) {

    }

    @FXML
    void onRemoveAccount(ActionEvent event) {

    }

    @FXML
    void onAddAccount(ActionEvent event) {
        bankAccountsEditor.setHeadTitle("Nueva Cuenta Bancaria.");
        BankAccount account = bankAccountsEditor.editEntity(new BankAccount());
        if (account != null) {
            service.createNewBankAccount(account);
            update();
        }
    }

    @Override
    public void init() {
        initBankAccountTreeView();
        initBankListView();

    }
    
    private void initBankAccountTreeView(){
    
    }
    
    private void initBankListView(){
    
    }
    
    @Override
    public void update() {
        loadBankAccountsList();
    }
    
    public void updateBankAccountsTreeView(){
        
    }
    
    private void loadBanksList(){
    
    }
    
    private void loadBankAccountsList(){
        service.getAllBankAccounts();
    }

    @Override
    public Parent getView() {
        return mainContent;
    }

    public void setBankAccountsEditor(GeneralEditorDialog<BankAccount> bankAccountsEditor) {
        this.bankAccountsEditor = bankAccountsEditor;
    }

    public void setService(IBankAccountsService service) {
        this.service = service;
    }
    
}
