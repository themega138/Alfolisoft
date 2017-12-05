/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mq310.alfolisoft.controllers.finders;

import static com.mq310.alfolisoft.controllers.finders.GeneralFinderDialog.loadController;
import com.mq310.dao.services.IAccountsGroupService;
import com.mq310.ent.org.accounts.AccountsGroup;
import java.io.IOException;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Moises
 */
public class AccountsGroupFinderDialog extends GeneralFinderDialog<AccountsGroup>{

    @FXML
    private AnchorPane mainContent;

    @FXML
    private TableView<AccountsGroup> tableView;

    @FXML
    private TextField searchTF;
    
    private IAccountsGroupService accountsGroupService;

    @Override
    protected Parent createContent() {
        if (mainContent == null) {
            try {
                loadController("/fxml/general/finders/AccountsGroupFinderView.fxml", this);
            } catch (IOException ex) {
                Logger.getLogger(AccountFinderDialog.class.getName()).log(Level.SEVERE, null, ex);
            }
            initTableView();
            headTitle = "Listado de Grupos de Cuentas";
        }
        return mainContent;
    }
    
    private void initTableView(){
        TableColumn<AccountsGroup, String> nameColumn = new TableColumn<>("Nombre:");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<AccountsGroup, String> descriptionColumn = new TableColumn<>("Descripcion:");
        descriptionColumn.setMinWidth(500);
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        tableView.getColumns().addAll(nameColumn, descriptionColumn);
        
        FilteredList<AccountsGroup> filteredData = new FilteredList<>(entitiesList, p -> true);

        searchTF.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            filteredData.setPredicate((AccountsGroup t) -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (t.getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (t.getDescription().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                return false; // Does not match.
            });
        });
        
        tableView.setItems(filteredData);
    }
    
    @Override
    protected AccountsGroup getSelection() {
        return tableView.getSelectionModel().getSelectedItem();
    }

    @Override
    public void loadEntities() {
        this.entitiesList.clear();
        this.entitiesList.addAll(accountsGroupService.getAccountsGroupList());
    }

    public void setAccountsGroupService(IAccountsGroupService accountsGroupService) {
        this.accountsGroupService = accountsGroupService;
    }

}
