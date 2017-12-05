/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mq310.alfolisoft.controllers.editors.forms;

import com.mq310.ent.persons.Member;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Moises
 */
public class MemberFormEditorController extends GeneralFormController<Member>{

    @FXML
    private TextField lastnameTF;

    @FXML
    private TextField firstnameTF;

    private final ChangeListener<String> changeListener = (ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
        validate();
    };
    
    @Override
    public void setEditableEntity(Member entity) {
        this.entity = entity;
        if (entity != null) {
            bind();
        }
    }

    @Override
    public void init() {
        try {
            loadController("/fxml/general/forms/MemberFormEditor.fxml", this);
        } catch (IOException ex) {
            Logger.getLogger(AccountsGroupFormEditorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        notValidProperty.set(true);
        firstnameTF.textProperty().addListener(changeListener);
        lastnameTF.textProperty().addListener(changeListener);
    }
    
    private void bind(){
        Bindings.bindBidirectional(lastnameTF.textProperty(), this.entity.getLastnameProperty());
        Bindings.bindBidirectional(firstnameTF.textProperty(), this.entity.getFirstnameProperty());
    }
    
    private void unBind(){
        Bindings.unbindBidirectional(lastnameTF.textProperty(), this.entity.getLastnameProperty());
        Bindings.unbindBidirectional(firstnameTF.textProperty(), this.entity.getFirstnameProperty());
    }
    
    private void validate(){
        notValidProperty.set(
                (firstnameTF.getText().isEmpty()) ||
                (lastnameTF.getText().isEmpty())
        );
    }
    
    @Override
    public void clear() {
        unBind();
    }

    @Override
    public void update() {
        
    }
    
}
