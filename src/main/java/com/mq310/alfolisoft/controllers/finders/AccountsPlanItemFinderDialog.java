/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mq310.alfolisoft.controllers.finders;

import com.mq310.dao.services.IAccountsPlanItemService;
import com.mq310.ent.org.accounts.AccountsPlanItem;
import java.io.IOException;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
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
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Moises
 */
public class AccountsPlanItemFinderDialog extends GeneralFinderDialog<AccountsPlanItem> {

    @FXML
    private AnchorPane mainContent;

    @FXML
    private TableView<AccountsPlanItem> tableView;

    @FXML
    private TextField searchTF;
    
    private IAccountsPlanItemService accountsPlanItemService;

    @Override
    protected Parent createContent() {
        if (mainContent == null) {
            try {
                loadController("/fxml/general/finders/AccountsPlanItemFinderView.fxml", this);
            } catch (IOException ex) {
                Logger.getLogger(AccountsPlanItemFinderDialog.class.getName()).log(Level.SEVERE, null, ex);
            }
            initTableView();
            headTitle = "Listado de Cuentas";
        }
        return mainContent;
    }

    private void initTableView() {
        TableColumn<AccountsPlanItem, String> nameColumn = new TableColumn<>("Cuenta:");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().getAccount().getNameProperty());
        tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        tableView.getColumns().addAll(nameColumn);

        FilteredList<AccountsPlanItem> filteredData = new FilteredList<>(entitiesList, p -> true);

        searchTF.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            filteredData.setPredicate((AccountsPlanItem t) -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (t.getAccount().getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                }
                return false; // Does not match.
            });
        });

        tableView.setItems(filteredData);
    }

    @Override
    @Transactional
    public void loadEntities() {
        entitiesList.clear();
        entitiesList.addAll(accountsPlanItemService.getAccountsPlanItemList());
    }

    @Override
    protected AccountsPlanItem getSelection() {
        return tableView.getSelectionModel().getSelectedItem();
    }

    public void setAccountsPlanItemService(IAccountsPlanItemService accountsPlanItemService) {
        this.accountsPlanItemService = accountsPlanItemService;
    }
    
}
