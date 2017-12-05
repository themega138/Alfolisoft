/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mq310.alfolisoft.controllers.accountsPlan;

import com.mq310.alfolisoft.controllers.GeneralController;
import com.mq310.alfolisoft.controllers.editors.GeneralEditorDialog;
import com.mq310.dao.services.IAccountsPlanService;
import com.mq310.ent.BaseEntity;
import com.mq310.ent.org.accounts.AccountsGroup;
import com.mq310.ent.org.accounts.AccountsPlan;
import com.mq310.ent.org.accounts.AccountsPlanItem;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialogs;

/**
 * FXML Controller class
 *
 * @author Moises
 */
public class AccountsPlanMasterViewController extends GeneralController {

    public static final String VIEW_NAME = "accountsPlanMasterView";
    public static final String VIEW_PATH = AccountsPlanMainViewController.ACCOUNTSPLANMAINVAVIGATOR_PATH + DP + VIEW_NAME;

    public static AccountsPlanMasterViewController create() throws IOException {
        return (AccountsPlanMasterViewController) loadController("/fxml/accountsPlan/AccountsPlanMasterView.fxml");
    }

    @FXML
    private TreeView<BaseEntity> accountsTreeView;

    @FXML
    private ListView<AccountsPlan> versionedListView;

    @FXML
    private AnchorPane mainContent;

    @FXML
    private PieChart porcentChart;

    private final ObjectProperty<AccountsPlan> actualVersionProperty = new SimpleObjectProperty<AccountsPlan>();
    
    private IAccountsPlanService service;
    private GeneralEditorDialog<AccountsPlan> accountsPlanEditor;
    private GeneralEditorDialog<AccountsPlan> initializerBalancesEditor;
    private final ObservableList<AccountsPlan> versions = FXCollections.observableArrayList();
    private final ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

    @FXML
    void onStatusChange(ActionEvent event) {

    }

    @FXML
    void onAddNewVewsion(ActionEvent event) {
        createNewVersion();
    }

    @FXML
    void onEditAccountsTree(ActionEvent event) {

    }

    @Override
    public void init() {
        loadPlanVersions();
        initListView();
        initTreeView();
        initChart();
        initOtherComponents();
    }

    private void initListView() {
        versionedListView.setItems(versions);
        versionedListView.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends AccountsPlan> observable, AccountsPlan oldValue, AccountsPlan newValue) -> {
            loadVersionById(newValue.getId());
        });
    }
    
    private void initChart() {
        porcentChart.setLegendVisible(false);
        porcentChart.setData(pieChartData);
    }

    private void initTreeView() {
        accountsTreeView.setEditable(false);
        accountsTreeView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }
    
    private void initOtherComponents(){
        actualVersionProperty.addListener((ObservableValue<? extends AccountsPlan> observable, AccountsPlan oldValue, AccountsPlan newValue) -> {
            update();
        });
    }

    private void loadPlanVersions() {
        List<AccountsPlan> plans = service.getAccountsPlanList();
        if (plans.isEmpty()) {
            createInitialVersion();
        } else {
            versions.clear();
            versions.addAll(plans);
            loadActualVersion();
            update();
        }
    }
    
    private void loadActualVersion(){
        AccountsPlan plan = service.getActiveAccountsPlan();
        actualVersionProperty.set(plan);
    }
    
    private void loadVersionById(Integer id){
        AccountsPlan plan = service.getAccountsPlanById(id);
        actualVersionProperty.set(plan);
    }

    private void createInitialVersion() {
        accountsPlanEditor.setHeadTitle("Crear nuevo plan de cuentas...");
        AccountsPlan plan = accountsPlanEditor.editEntity(new AccountsPlan());
        if (plan != null) {
            initializerBalancesEditor.setHeadTitle("Ingrese los saldos iniciales de todas las Cuentas...");
            plan = initializerBalancesEditor.editEntity(plan);
            if (plan != null) {
                service.createNewAccountsPlan(plan);
                loadPlanVersions();
            }
        }
    }

    private void createNewVersion() {
        final String option_1 = "Copia de version anterior con cuentas activas...";
        final String option_2 = "Nueva version con cuentas activas...";
        final String option_3 = "Nueva version con saldo en cuenta comun...";
        final Dialogs.CommandLink command_1 = new Dialogs.CommandLink(
                                option_1,
                                "Se toma una version anterior del plan de cuentas y se agregan al nuevo plan todas las cuentas activas de dicho plan y todas las cuentas activas del plan actual que posean saldo.");
        command_1.setDisabled((versionedListView.getSelectionModel().getSelectedIndex() < 0) || (versionedListView.getSelectionModel().getSelectedItem().getEnable()));
        List<Dialogs.CommandLink> links = Arrays
                .asList(command_1,
                        new Dialogs.CommandLink(
                                option_2,
                                "Se crea un plan nuevo solo con las cuentas de plan actual que esten activas."),
                        new Dialogs.CommandLink(option_3,
                                "Se crea un plan nuevo y se transfiere todo el saldo a la cuenta base de ingresos."));

        Action response = Dialogs.create()
                .title("Creation de nuevo plan de cuentas")
                .message("¿Como desea crear el nuevo plan de cuentas?")
                .owner(mainContent)
                .showCommandLinks(links.get(1), links);
        if (response != null) {
            String option = (String) response.getProperties().get("text");
            switch (option) {
                case option_1:
                    createFromOldVersion();
                    break;
                case option_2:
                    createNewWithActive();
                    break;
                case option_3:
                    createAndTransfer();
                    break;
            }
        }
    }
    
    private void createFromOldVersion(){
        
    }
    
    private void createNewWithActive(){
    
    }
    
    private void createAndTransfer(){
    
    }
    
    @Override
    public void update() {
        updateChart();
        updateTreeView();
    }
    
    private void updateChart(){
    
        pieChartData.clear();
        actualVersionProperty.get().getAccountsPlanItems().stream().forEach((accountsPlanItem) -> {
            pieChartData.add(new PieChart.Data(accountsPlanItem.getAccount().getName(), accountsPlanItem.getPercent()));
        });

        porcentChart.getData().stream().forEach((data) -> {
            data.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
                
            });
            data.getNode().addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
                
            });
        });
    }
    
    private void updateTreeView(){
        TreeItem<BaseEntity> rootItem = new TreeItem<BaseEntity>((BaseEntity)actualVersionProperty.get(), loadIcon("/images/accountsPlan_16x16.png"));
        rootItem.setExpanded(true);
        for (AccountsPlanItem accountsPlanItem : actualVersionProperty.get().getAccountsPlanItems()) {
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

    @Override
    public Parent getView() {
        return mainContent;
    }

    public void setService(IAccountsPlanService service) {
        this.service = service;
    }

    public void setAccountsPlanEditor(GeneralEditorDialog<AccountsPlan> accountsPlanEditor) {
        this.accountsPlanEditor = accountsPlanEditor;
    }

    public void setInitializerBalancesEditor(GeneralEditorDialog<AccountsPlan> initializerBalancesEditor) {
        this.initializerBalancesEditor = initializerBalancesEditor;
    }
    
}
