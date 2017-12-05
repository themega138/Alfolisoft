/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mq310.alfolisoft.controllers.finders;

import static com.mq310.alfolisoft.controllers.finders.GeneralFinderDialog.loadController;
import com.mq310.dao.services.IBankService;
import com.mq310.ent.org.Bank;
import java.io.IOException;
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
public class BankFinderDialog extends GeneralFinderDialog<Bank> {

    @FXML
    private TableView<Bank> banksTable;

    @FXML
    private AnchorPane mainContent;

    @FXML
    private TextField searchTF;
    
    private IBankService bankService;

    @Override
    protected Parent createContent() {
        if (mainContent == null) {
            try {
                loadController("/fxml/general/finders/BankFinderView.fxml", this);
            } catch (IOException ex) {
                Logger.getLogger(AccountFinderDialog.class.getName()).log(Level.SEVERE, null, ex);
            }
            initTable();
            headTitle = "Listado de Bancos";
        }
        return mainContent;
    }

    private void initTable() {
        TableColumn<Bank, String> nameColumn = new TableColumn<Bank, String>("Nombre:");
        nameColumn.setMinWidth(400);
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
        banksTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        banksTable.getColumns().addAll(nameColumn);

        FilteredList<Bank> filteredData = new FilteredList<>(entitiesList, p -> true);

        searchTF.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            filteredData.setPredicate((Bank t) -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (t.getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                }
                return false; // Does not match.
            });
        });

        banksTable.setItems(filteredData);
    }

    @Override
    protected Bank getSelection() {
        return banksTable.getSelectionModel().getSelectedItem();
    }

    @Override
    public void loadEntities() {
        SortedSet<Bank> banks = bankService.getBankList();
        entitiesList.clear();
        entitiesList.addAll(banks);
    }

    public void setBankService(IBankService bankService) {
        this.bankService = bankService;
    }

}
