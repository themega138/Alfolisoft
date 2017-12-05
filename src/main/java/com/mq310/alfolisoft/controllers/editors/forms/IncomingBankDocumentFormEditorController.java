/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mq310.alfolisoft.controllers.editors.forms;

import com.mq310.alfolisoft.controllers.editors.GeneralEditorDialog;
import com.mq310.alfolisoft.controllers.finders.GeneralFinderDialog;
import com.mq310.dao.services.IMemberService;
import com.mq310.ent.org.BankAccount;
import com.mq310.ent.org.counting.docs.IncomingBankDocument;
import com.mq310.ent.persons.Member;
import com.mq310.ent.persons.Person;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author Moises
 */
public class IncomingBankDocumentFormEditorController extends GeneralFormController<IncomingBankDocument> {

    @FXML
    private TextField refTF;

    @FXML
    private Label personLB;

    @FXML
    private DatePicker dateDF;

    @FXML
    private Label accountLB;

    @FXML
    private TextField amountTF;

    private GeneralEditorDialog<Member> memberEditorDialog;

    private GeneralFinderDialog<Member> memberFinderDialog;

    private GeneralFinderDialog<BankAccount> bankAccountFinderDialog;

    private IMemberService memberService;

    @FXML
    void onSearchAccount(ActionEvent event) {
        BankAccount account = bankAccountFinderDialog.findEntity();
        if (account != null) {
            this.entity.setDestinationBankAccount(account);
            validate();
        }
    }

    @FXML
    void onSearchPerson(ActionEvent event) {
        Member member = memberFinderDialog.findEntity();
        if (member != null) {
            this.entity.setPerson(member);
            validate();
        }
    }

    @FXML
    void onAddPerson(ActionEvent event) {
        memberEditorDialog.setHeadTitle("Crear nueva persona.");
        Member member = memberEditorDialog.editEntity(new Member());
        if (member != null) {
            member = memberService.saveMember(member);
            this.entity.setPerson(member);
            validate();
        }
    }

    @Override
    public void setEditableEntity(IncomingBankDocument entity) {
        this.entity = entity;
        if (this.entity != null) {
            bind();
            validate();
        }
    }

    @Override
    public void clear() {
        unBind();
    }

    @Override
    public void init() {
        try {
            loadController("/fxml/general/forms/IncomingBankDocumentFormEditor.fxml", this);
        } catch (IOException ex) {
            Logger.getLogger(IncomingBankDocumentFormEditorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        notValidProperty.set(true);
        amountTF.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            validate();
        });
        amountTF.addEventHandler(KeyEvent.KEY_TYPED, (KeyEvent event) -> {
            if (!event.getCharacter().matches("[0-9.]")) {
                event.consume();
            }
        });
        amountTF.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (!newValue) {
                amountTF.setText(formatter.format(this.entity.getAmount()));
            }
        });
        dateDF.valueProperty().addListener((ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) -> {
            this.entity.getCreationDateProperty().set(Date.from(newValue.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
            System.out.println(this.entity.getCreationDate());
        });
    }

    private void bind() {
        refTF.textProperty().bindBidirectional(this.entity.getReferenceProperty());
        bindPerson();
        bindAccount();
        amountTF.textProperty().bindBidirectional(this.entity.getAmountProperty(), getConverter());
        dateDF.setValue(new Date(this.entity.getCreationDate().getTime()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        
    }

    private void unBind() {
        refTF.textProperty().unbind();
        unBindAccount();
        unBindPerson();
        Bindings.unbindBidirectional(amountTF.textProperty(), this.entity.getAmountProperty());
    }

    private void bindPerson() {
        personLB.textProperty().bindBidirectional(this.entity.getPersonProperty(), new StringConverter<Person>() {

            @Override
            public String toString(Person object) {
                return object.getFirstname() + " " + object.getLastname();
            }

            @Override
            public Person fromString(String string) {
                return null;
            }
        });
    }

    private void unBindPerson() {
        Bindings.unbindBidirectional(personLB.textProperty(), this.entity.getPersonProperty());

    }

    private void bindAccount() {
        accountLB.textProperty().bindBidirectional(this.entity.getDestinationBankAccountProperty(), new StringConverter<BankAccount>() {

            @Override
            public String toString(BankAccount object) {
                String result = "";
                if (object != null) {
                    result = object.getReference();
                }
                return result;
            }

            @Override
            public BankAccount fromString(String string) {
                return null;
            }
        });
    }

    private void unBindAccount() {
        Bindings.unbindBidirectional(accountLB.textProperty(), this.entity.getDestinationBankAccountProperty());
    }

    private void validate() {
        notValidProperty.set(
                (refTF.getText().isEmpty())
                || (personLB.getText().isEmpty())
                || (accountLB.getText().isEmpty())
                || (this.entity.getAmount()==0.0)
        );
    }

    @Override
    public void update() {
    }

    public void setMemberEditorDialog(GeneralEditorDialog<Member> memberEditorDialog) {
        this.memberEditorDialog = memberEditorDialog;
    }

    public void setMemberFinderDialog(GeneralFinderDialog<Member> memberFinderDialog) {
        this.memberFinderDialog = memberFinderDialog;
    }

    public void setBankAccountFinderDialog(GeneralFinderDialog<BankAccount> bankAccountFinderDialog) {
        this.bankAccountFinderDialog = bankAccountFinderDialog;
    }

    public void setMemberService(IMemberService memberService) {
        this.memberService = memberService;
    }

    @Override
    public IncomingBankDocument getEditedEntity() {
        this.entity.getCreationDateProperty().set(Date.from(dateDF.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
        return super.getEditedEntity(); //To change body of generated methods, choose Tools | Templates.
    }
    
    

}
