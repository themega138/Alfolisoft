/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mq310.alfolisoft.controllers.finders;

import static com.mq310.alfolisoft.controllers.finders.GeneralFinderDialog.loadController;
import com.mq310.dao.services.IMemberService;
import com.mq310.ent.persons.Member;
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
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Moises
 */
public class MemberFinderDialog extends GeneralFinderDialog<Member>{
    
    @FXML
    private AnchorPane mainContent;

    @FXML
    private TableView<Member> tableView;

    @FXML
    private TextField searchTF;
    
    private IMemberService memberService;

    @Override
    protected Parent createContent() {
        if (mainContent == null) {
            try {
                loadController("/fxml/general/finders/MemberFinderView.fxml", this);
            } catch (IOException ex) {
                Logger.getLogger(AccountFinderDialog.class.getName()).log(Level.SEVERE, null, ex);
            }
            initTableView();
            headTitle = "Listado de Miembros";
        }
        return mainContent;
    }
    
    private void initTableView(){
        TableColumn<Member, String> nameColumn = new TableColumn<>("Nombres:");
        nameColumn.setMinWidth(300);
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().getFirstnameProperty());
        TableColumn<Member, String> descriptionColumn = new TableColumn<>("Apellidos:");
        descriptionColumn.setMinWidth(300);
        descriptionColumn.setCellValueFactory(cellData -> cellData.getValue().getLastnameProperty());
        tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        tableView.getColumns().addAll(nameColumn, descriptionColumn);
        
        FilteredList<Member> filteredData = new FilteredList<>(entitiesList, p -> true);

        searchTF.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            filteredData.setPredicate((Member t) -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (t.getFirstname().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (t.getLastname().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                return false; // Does not match.
            });
        });
        
        tableView.setItems(filteredData);
    }
    
    @Override
    protected Member getSelection() {
        return tableView.getSelectionModel().getSelectedItem();
    }

    @Override
    @Transactional
    public void loadEntities() {
        entitiesList.clear();
        entitiesList.addAll(memberService.getMemberList());
    }

    public void setMemberService(IMemberService memberService) {
        this.memberService = memberService;
    }
    
}
