/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mq310.alfolisoft.controllers.editors.forms;

import com.mq310.ent.BaseEntity;
import com.mq310.ent.org.accounts.AccountsGroup;
import com.mq310.ent.org.accounts.AccountsPlan;
import com.mq310.ent.org.accounts.AccountsPlanItem;
import com.mq310.ent.org.accounts.movements.InitialMovement;
import com.mq310.ent.org.accounts.movements.Movement;
import java.io.IOException;
import java.util.Iterator;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Moises
 */
public class InitializerBalancesFormEditorController extends GeneralFormController<AccountsPlan> {
    
    private final Node rootIcon = loadIcon("/images/accountsPlan_16x16.png");
    
    @FXML
    private TreeView<BaseEntity> treeView;

    @FXML
    private Label totalBalanceLB;
    
    @FXML
    private Button asingBTN;

    @FXML
    private TextField initialBalanceTF;

    @FXML
    private PieChart pieChart;
    
    private final ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
    
    @FXML
    void onAsing(ActionEvent event) {
        AccountsPlanItem item = (AccountsPlanItem) treeView.getSelectionModel().getSelectedItem().getValue();
        Double amount = Math.ceil(Double.parseDouble(initialBalanceTF.getText())*10)/10;
        if (item.getMovements().isEmpty()) {
            InitialMovement move = new InitialMovement();
            move.setAccountsPlanItem(item);
            move.setAmount(amount);
            item.addMovement(move);
        } else {
            for (Movement movement : item.getMovements()) {
                if (movement instanceof InitialMovement) {
                    movement.setAmount(amount);
                    break;
                }
            }
        }
        initialBalanceTF.setText("");
        update();
    }

    @Override
    public void setEditableEntity(AccountsPlan entity) {
        this.entity = entity;
        if (entity != null) {
            update();
        }
    }

    @Override
    public void clear() {
        
    }

    @Override
    public void init() {
        try {
            loadController("/fxml/general/forms/InitializerBalancesFormEditor.fxml", this);
        } catch (IOException ex) {
            Logger.getLogger(AccountsGroupFormEditorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        initTreeView();
        initChart();
        initOtherComponents();
    }

    @Override
    public void update() {
        updateTreeView();
        updatePieChart();
        updateTotalBalance();
        validateForm();
    }
    
    private void initTreeView() {
        treeView.setEditable(false);
        treeView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        treeView.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends TreeItem<BaseEntity>> observable, TreeItem<BaseEntity> oldValue, TreeItem<BaseEntity> newValue) -> {
            validate();
        });
    }
    
    private void validate(){
        if (treeView.getSelectionModel().getSelectedIndex() < 0) {
            asingBTN.setDisable(true);
        } else {
            asingBTN.setDisable((initialBalanceTF.getText().isEmpty() || !(treeView.getSelectionModel().getSelectedItem().getValue() instanceof AccountsPlanItem)));
        }
    }

    private void initChart() {
        pieChart.setData(pieChartData);
    }
    
    private void initOtherComponents(){
        initialBalanceTF.addEventHandler(KeyEvent.KEY_TYPED, numeric_Validation(20));
        initialBalanceTF.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            validate();
        });
        notValidProperty.set(true);
    }
    
    private void updatePieChart() {
        
        pieChartData.clear();
        entity.getAccountsPlanItems().stream().forEach((accountsPlanItem) -> {
            if (accountsPlanItem.getAccountBalance() > 0.0) {
                pieChartData.add(new PieChart.Data(accountsPlanItem.getAccount().getName(), accountsPlanItem.getAccountBalance()));
            }
        });
        if (pieChartData.size()==0) {
            pieChartData.add(new PieChart.Data("Sin Asignar.", 1));
        }
        pieChart.getData().stream().forEach((data) -> {
            data.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
                
            });
            data.getNode().addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
                
            });
        });

    }
    
    private void updateTreeView(){
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
                groupItem = new TreeItem<>(accountsGroup, loadIcon("/images/accounts_16x16.png"));
                rootItem.getChildren().add(groupItem);
            }
            groupItem.getChildren().add(new TreeItem<>(accountsPlanItem));
        }
        rootItem.getChildren().stream().forEach((treeItem) -> {
            treeItem.setExpanded(true);
        });
        treeView.setRoot(rootItem);
    }
    
    private void updateTotalBalance(){
        totalBalanceLB.setText(getTotalBalance()+" Bs.");
    }
    
    private Double getTotalBalance(){
        Double balance = 0.0;
        balance = entity.getAccountsPlanItems().stream().map((accountsPlanItem) -> accountsPlanItem.getAccountBalance()).reduce(balance, (accumulator, _item) -> accumulator + _item);
        return balance;
    }
    
    private void validateForm(){
        boolean find = false;
        for (AccountsPlanItem accountsPlanItem : entity.getAccountsPlanItems()) {
            if (accountsPlanItem.getAccountBalance() <= 0.0) {
                notValidProperty.set(true);
                find = true;
                break;
            }
        }
        
        if (!find) {
            notValidProperty.set(false);
        }
    }
}
