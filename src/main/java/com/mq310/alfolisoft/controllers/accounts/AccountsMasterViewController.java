/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mq310.alfolisoft.controllers.accounts;

import com.mq310.alfolisoft.controllers.GeneralController;
import com.mq310.alfolisoft.controllers.editors.GeneralEditorDialog;
import com.mq310.dao.services.IAccountsGroupService;
import com.mq310.ent.org.accounts.Account;
import com.mq310.ent.org.accounts.AccountsGroup;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialogs;

/**
 * FXML Controller class
 *
 * @author Moises
 */
public class AccountsMasterViewController extends GeneralController {

    public static final String VIEW_NAME = "accountsMasterView";
    public static final String VIEW_PATH = AccountsMainViewController.ACCOUNTSMAINVIEWNAVIGATOR_PATH + DP + VIEW_NAME;

    public static AccountsMasterViewController create() throws IOException {
        return (AccountsMasterViewController) loadController("/fxml/accounts/AccountsMasterView.fxml");
    }

    @FXML
    private AnchorPane mainContent;

    @FXML
    private TreeView<Object> accountsTree;

    @FXML
    private TextField searchTF;

    private IAccountsGroupService service;
    private GeneralEditorDialog<AccountsGroup> accountsGroupEditor;
    private GeneralEditorDialog<Account> accountEditor;

    @FXML
    void onEdit(ActionEvent event) {

    }

    @FXML
    void onRemove(ActionEvent event) {

    }

    @FXML
    void onAdd(ActionEvent event) {
        final String option_1 = "Cargar de una lista preestablecida...";
        final String option_2 = "Crear una nueva cuenta...";
        final String option_3 = "Crear un nuevo grupo de cuentas...";
        final Dialogs.CommandLink command_1 = new Dialogs.CommandLink(
                option_1,
                "Se toman cuentas o groupos de cuentas de listas preestablecidas por el sistemas...");
        command_1.setDisabled(true);
        List<Dialogs.CommandLink> links = Arrays
                .asList(command_1,
                        new Dialogs.CommandLink(
                                option_2,
                                "Se crea una nueva cuenta seleccionando a que grupo de cuentas va ha pertenecer. Si el grupo de cuentas no existe debe ser creado primero..."),
                        new Dialogs.CommandLink(option_3,
                                "Se crea un nuevo grupo de cuentas vacio."));

        Action response = Dialogs.create()
                .title("Creation de cuentas")
                .message("¿Que desea realizar?")
                .owner(mainContent)
                .showCommandLinks(links.get(1), links);
        if (response != null) {
            String option = response.textProperty().get();
            switch (option) {
                case option_1:
                    createFromList();
                    break;
                case option_2:
                    createNewAccount();
                    break;
                case option_3:
                    createNewGroup();
                    break;
            }
        }
    }

    @Override
    public void init() {
        initTreeView();
        initOtherComponents();
        update();
    }

    private void initTreeView() {

    }

    private void initOtherComponents() {

    }

    private void updateTreeView() {
        accountsTree.setRoot(service.getAccountTreeItemRoot());
    }

    private void createFromList() {

    }

    private void createNewGroup() {
        accountsGroupEditor.setHeadTitle("Creacion de nuevo grupo de cuentas.");
        AccountsGroup group = accountsGroupEditor.editEntity(new AccountsGroup());
        if (group != null) {
            service.saveAccountGroup(group);
            update();
        }
    }

    private void createNewAccount() {
        accountEditor.setHeadTitle("Creacion de nueva cuenta.");
        Account account = new Account();
        if (accountsTree.getSelectionModel().getSelectedIndex() >= 0) {
            Object entity = accountsTree.getSelectionModel().getSelectedItem();
            if (entity instanceof AccountsGroup) {
                account.setAccountsGroup((AccountsGroup) entity);
            }
        }
        account = accountEditor.editEntity(account);
        if (account != null) {
            service.saveAccount(account);
            update();
        }
    }

    public void setAccountsGroupEditor(GeneralEditorDialog<AccountsGroup> accountsGroupEditor) {
        this.accountsGroupEditor = accountsGroupEditor;
    }

    public void setAccountsGroupDaoService(IAccountsGroupService accountsGroupDaoService) {
        this.service = accountsGroupDaoService;
    }

    public void setAccountEditor(GeneralEditorDialog<Account> accountEditor) {
        this.accountEditor = accountEditor;
    }

    @Override
    public Parent getView() {
        return mainContent;
    }

    @Override
    public void update() {
        updateTreeView();

    }

}
