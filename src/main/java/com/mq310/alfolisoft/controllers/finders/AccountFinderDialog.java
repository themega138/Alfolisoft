/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mq310.alfolisoft.controllers.finders;

import com.mq310.dao.services.IAccountsGroupService;
import com.mq310.ent.org.accounts.Account;
import com.mq310.ent.org.accounts.AccountsGroup;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Moises
 */
public class AccountFinderDialog extends GeneralFinderDialog<Account> {

    @FXML
    private AnchorPane mainContent;

    @FXML
    private TreeTableView<Object> treeTableView;

    @FXML
    private TextField searchTF;

    private IAccountsGroupService accountsGroupService;
    
    private ObservableList<AccountsGroup> accountsGroupList = FXCollections.observableArrayList();

    @Override
    protected Parent createContent() {
        if (mainContent == null) {
            try {
                loadController("/fxml/general/finders/AccountFinderView.fxml", this);
            } catch (IOException ex) {
                Logger.getLogger(AccountFinderDialog.class.getName()).log(Level.SEVERE, null, ex);
            }
            initTableView();
            headTitle = "Listado de Cuentas";
            searchTF.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                loadEntities();
            });
        }
        return mainContent;
    }

    private void initTableView() {

        TreeTableColumn<Object, String> nameColumn = new TreeTableColumn<>("Nombre:");
        nameColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<Object, String> param) -> {
            Object o = param.getValue().getValue();
            if (o instanceof Account) {
                return ((Account) o).getNameProperty();
            } else if (o instanceof String) {
                return new SimpleStringProperty((String) o);
            } else if (o instanceof AccountsGroup) {
                return new SimpleStringProperty(((AccountsGroup)o).getName());
            } else {
                return null;
            }
        });
        TreeTableColumn<Object, String> descriptionColumn = new TreeTableColumn<>("Descripcion:");
        descriptionColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<Object, String> param) -> {
            Object o = param.getValue().getValue();
            if (o instanceof Account) {
                return ((Account) o).getDescriptionProperty();
            } else {
                return null;
            }
        });
        treeTableView.getColumns().addAll(nameColumn, descriptionColumn);
    }

    private void updateTreeTableView() {
        TreeItem<Object> root = new TreeItem<>("Arbol de Cuentas...");
        root.setGraphic(loadIcon("/images/accountsPlan_16x16.png"));
        root.setExpanded(true);
        TreeItem<Object> groupItem = null;
        for (AccountsGroup accountsGroup : accountsGroupList) {
            groupItem = new TreeItem<>(accountsGroup);
            groupItem.setExpanded(true);
            groupItem.setGraphic(loadIcon("/images/accounts_16x16.png"));
            TreeItem<Object> accountItem = null;
            for (Account account : accountsGroup.getAccounts()) {
                if (searchTF.getText().isEmpty() || account.getName().toLowerCase().contains(searchTF.getText().toLowerCase())) {
                    accountItem = new TreeItem<>(account);
                    groupItem.getChildren().add(accountItem);
                }
            }
            if (!groupItem.getChildren().isEmpty()) {
                root.getChildren().add(groupItem);
            }
        }
        treeTableView.setRoot(root);
    }

    @Override
    public void loadEntities() {
        accountsGroupList.clear();
        accountsGroupList.addAll(accountsGroupService.getAccountsGroupList());
        updateTreeTableView();
    }

    @Override
    protected Account getSelection() {
        Object o = treeTableView.getSelectionModel().getSelectedItem().getValue();
        if (o instanceof Account) {
            return (Account) o;
        } else {
            return null;
        }
    }

    public void setAccountsGroupService(IAccountsGroupService accountsGroupService) {
        this.accountsGroupService = accountsGroupService;
    }

}
