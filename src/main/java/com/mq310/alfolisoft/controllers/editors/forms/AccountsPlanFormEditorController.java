package com.mq310.alfolisoft.controllers.editors.forms;

import com.mq310.alfolisoft.controllers.editors.GeneralEditorDialog;
import com.mq310.ent.BaseEntity;
import com.mq310.ent.org.accounts.AccountsGroup;
import com.mq310.ent.org.accounts.AccountsPlan;
import com.mq310.ent.org.accounts.AccountsPlanItem;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialogs;

public class AccountsPlanFormEditorController extends GeneralFormController<AccountsPlan> {

    private final Node rootIcon = loadIcon("/images/accountsPlan_16x16.png");

    @FXML
    private TreeView<BaseEntity> accountsTreeView;

    @FXML
    private PieChart porcentChart;

    private GeneralEditorDialog<AccountsPlanItem> accountsPlanItemEditor;

    private final ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

    @FXML
    void onRemove(ActionEvent event) {
        BaseEntity entitySelected = accountsTreeView.getSelectionModel().getSelectedItem().getValue();
        if (entitySelected instanceof AccountsGroup) {

        } else if (entitySelected instanceof AccountsPlanItem) {
            //TODO Recuerda validar si se puede eliminar...
            Action response
                    = Dialogs.create()
                    .title("Confirmacion...")
                    .masthead("¿Realmente desea eliminar esta cuenta del plan de cuentas?")
                    .message("El porcentaje asignado a esta cuenta debera ser reasignado a otra cuenta.")
                    .actions(new Action[]{Dialog.Actions.YES, Dialog.Actions.NO})
                    .showConfirm();
            if (response.toString().equals("YES")) {
                removeAccountsPlanItem((AccountsPlanItem) entitySelected);
                update();
            }
        }
    }

    @FXML
    void onAdd(ActionEvent event) {
        if (getRemainder() > 0) {
            accountsPlanItemEditor.setHeadTitle("Nuevo item del plan de cuentas:");
            AccountsPlanItem item = accountsPlanItemEditor.editEntity(new AccountsPlanItem(getRemainder()));
            if (item != null && !existItem(item)) {
                addAccountsPlanItem(item);
                update();
            }
        }
    }

    @FXML
    void onEdit(ActionEvent event) {
        BaseEntity entitySelected = accountsTreeView.getSelectionModel().getSelectedItem().getValue();
        if (entitySelected instanceof AccountsPlanItem) {
            accountsPlanItemEditor.setHeadTitle("Nuevo item del plan de cuentas:");
            AccountsPlanItem item = (AccountsPlanItem) entitySelected;
            item.setPercent(item.getPercent()+getRemainder());
            item = accountsPlanItemEditor.editEntity(item);
            if (item != null) {
                replaceWithNew(item);
                update();
            }
        } else {
            
        }
    }

    @Override
    public void setEditableEntity(AccountsPlan entity) {
        this.entity = entity;
        if (entity != null) {
            update();
        }
    }

    @Override
    public void init() {
        try {
            loadController("/fxml/general/forms/AccountsPlanFormEditor.fxml", this);
        } catch (IOException ex) {
            Logger.getLogger(AccountsGroupFormEditorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        initChart();
        initTreeView();
    }

    private void initTreeView() {
        accountsTreeView.setEditable(false);
        accountsTreeView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    private void initChart() {
        porcentChart.setLegendVisible(false);
        porcentChart.setLabelLineLength(10);
        porcentChart.setData(pieChartData);
    }

    private void updateChart() {
        
        pieChartData.clear();
        entity.getAccountsPlanItems().stream().forEach((accountsPlanItem) -> {
            pieChartData.add(new PieChart.Data(accountsPlanItem.getAccount().getName(), accountsPlanItem.getPercent()));
        });
        if (getRemainder() > 0) {
            pieChartData.add(new PieChart.Data("Sin Asignar.", getRemainder()));
        }

        porcentChart.getData().stream().forEach((data) -> {
            data.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
                
            });
            data.getNode().addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
                
            });
        });

    }

    private Double getRemainder() {
        Double quatity = 0.0;
        quatity = entity.getAccountsPlanItems().stream().map((accountsPlanItem) -> accountsPlanItem.getPercent()).reduce(quatity, (accumulator, _item) -> accumulator + _item);
        return 100 - quatity;
    }

    private void updateTreeView() {
        TreeItem<BaseEntity> rootItem = new TreeItem<BaseEntity>(new AccountsPlan(), rootIcon);
        rootItem.setExpanded(true);
        for (AccountsPlanItem accountsPlanItem : entity.getAccountsPlanItems()) {
            AccountsGroup accountsGroup = accountsPlanItem.getAccount().getAccountsGroup();
            Integer accountGroupId = accountsGroup.getId();
            TreeItem<BaseEntity> groupItem = null;
            for (TreeItem<BaseEntity> groupRootItem : rootItem.getChildren()) {
                AccountsGroup group = (AccountsGroup) groupRootItem.getValue();
                if (Objects.equals(group.getId(), accountGroupId)) {
                    groupItem = groupRootItem;
                    break;
                }
            }
            if (groupItem == null) {
                groupItem = new TreeItem<BaseEntity>(accountsGroup, loadIcon("/images/accounts_16x16.png"));
                rootItem.getChildren().add(groupItem);
            }
            groupItem.getChildren().add(new TreeItem<BaseEntity>(accountsPlanItem));
        }
        rootItem.getChildren().stream().forEach((treeItem) -> {
            treeItem.setExpanded(true);
        });
        
        accountsTreeView.setRoot(rootItem);
    }

    private Boolean existItem(AccountsPlanItem item) {
        Boolean response = false;
        for (AccountsPlanItem accountsPlanItem : entity.getAccountsPlanItems()) {
            if (accountsPlanItem.getAccount().getId().equals(item.getAccount().getId())) {
                response = true;
                itemExist(item);
                break;
            }
        }
        return response;
    }

    private void itemExist(AccountsPlanItem item) {
        AccountsPlanItem localItem = item;
        final String option_1 = "Conservar la antigua...";
        final String option_2 = "Conservar la nueva...";
        final String option_3 = "Otras opciones...";
        List<Dialogs.CommandLink> links = Arrays
                .asList(new Dialogs.CommandLink(
                                option_1,
                                "Conserva la entrada antigua eliminando la nueva."),
                        new Dialogs.CommandLink(
                                option_2,
                                "Reemplaza la antigua con la nueva entrada."),
                        new Dialogs.CommandLink(
                                option_3,
                                "Presenta otras opciones para elegir."));

        Action response = Dialogs.create()
                .title("Creacion de nuevo item en plan de cuentas")
                .message("La cuenta existe y desea...")
                .owner(mainContent)
                .showCommandLinks(links.get(1), links);
        String option = response.textProperty().get();
        switch (option) {
            case option_1:
                conserveOld();
                break;
            case option_2:
                replaceWithNew(localItem);
                break;
            case option_3:
                showOptions();
                break;
        }
    }

    private void replaceWithNew(AccountsPlanItem item) {
        removeAccountsPlanItem(item);
        addAccountsPlanItem(item);
        update();
    }

    private void removeAccountsPlanItem(AccountsPlanItem item) {
        for (Iterator<AccountsPlanItem> it = entity.getAccountsPlanItems().iterator(); it.hasNext();) {
            AccountsPlanItem accountsPlanItem = it.next();
            if (Objects.equals(item.getAccount().getId(), accountsPlanItem.getAccount().getId())) {
                it.remove();
                break;
            }
        }
    }

    private void addAccountsPlanItem(AccountsPlanItem item) {
        item.setAccountsPlan(entity);
        entity.addAccountPlanItem(item);
    }

    private void conserveOld() {

    }

    private void showOptions() {

    }

    public void setAccountsPlanItemEditor(GeneralEditorDialog<AccountsPlanItem> accountsPlanItemEditor) {
        this.accountsPlanItemEditor = accountsPlanItemEditor;
    }

    @Override
    public void update() {
        updateChart();
        updateTreeView();
    }

    @Override
    public void clear() {
        this.entity = null;
        this.accountsTreeView.setRoot(null);
    }

}
