/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mq310.alfolisoft.controllers.finders;

import static com.mq310.alfolisoft.controllers.finders.GeneralFinderDialog.loadController;
import com.mq310.dao.services.IBankAccountsService;
import com.mq310.dao.services.IBankService;
import com.mq310.ent.org.Bank;
import com.mq310.ent.org.BankAccount;
import java.io.IOException;
import java.util.List;
import java.util.SortedSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Moises
 */
public class BankAccountFinderDialog extends GeneralFinderDialog<BankAccount> {

    @FXML
    private TableView<BankAccount> banksTable;

    @FXML
    private AnchorPane mainContent;

    @FXML
    private TextField searchTF;
    
    private IBankAccountsService bankAccountService;

    @Override
    protected Parent createContent() {
        if (mainContent == null) {
            try {
                loadController("/fxml/general/finders/BankAccountFinderView.fxml", this);
            } catch (IOException ex) {
                Logger.getLogger(BankAccountFinderDialog.class.getName()).log(Level.SEVERE, null, ex);
            }
            initTable();
            headTitle = "Listado de Cuentas Bancarias";
        }
        return mainContent;
    }

    private void initTable() {
        TableColumn<BankAccount, String> bankColumn = new TableColumn<>("Banco:");
        bankColumn.setMinWidth(400);
        bankColumn.setCellValueFactory(cellData -> cellData.getValue().getBank().getNameProperty());
        
        TableColumn<BankAccount, String> refColumn = new TableColumn<>("Referencia:");
        refColumn.setMinWidth(400);
        refColumn.setCellValueFactory(cellData -> cellData.getValue().getReferenceProperty());
        
        banksTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        banksTable.getColumns().addAll(bankColumn,refColumn);

        FilteredList<BankAccount> filteredData = new FilteredList<>(entitiesList, p -> true);

        searchTF.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            filteredData.setPredicate((BankAccount t) -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (t.getBank().getName().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (t.getReference().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                }
                return false; // Does not match.
            });
        });

        banksTable.setItems(filteredData);
    }

    @Override
    protected BankAccount getSelection() {
        return banksTable.getSelectionModel().getSelectedItem();
    }

    @Override
    public void loadEntities() {
        entitiesList.clear();
        entitiesList.addAll(bankAccountService.getAllBankAccounts());
    }

    public void setBankAccountService(IBankAccountsService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    

}
