package com.mq310.alfolisoft.controllers.editors.forms;

import com.mq310.alfolisoft.controllers.editors.GeneralEditorDialog;
import com.mq310.alfolisoft.controllers.finders.GeneralFinderDialog;
import com.mq310.dao.services.IMemberService;
import com.mq310.dao.services.IPacketService;
import com.mq310.ent.org.counting.Packet;
import com.mq310.ent.org.counting.SpecificOffering;
import com.mq310.ent.persons.Member;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author Moises
 */
public class PacketFormEditorController extends GeneralFormController<Packet> {

    @FXML
    private Label firstnameLB;

    @FXML
    private Label lastnameLB;

    @FXML
    private Label packetNumberLB;

    @FXML
    private Label totalLB;

    @FXML
    private TableView<SpecificOffering> tableView;

    @FXML
    private TextField offeringTF;

    @FXML
    private TextField titheTF;

    private GeneralFinderDialog<Member> memberFinderDialog;
    private GeneralEditorDialog<Member> memberEditorDialog;
    private GeneralEditorDialog<SpecificOffering> specificOfferingEditorDialog;
    private IPacketService packetService;
    private IMemberService memberService;
    private final ObservableList<SpecificOffering> offerings = FXCollections.observableArrayList();
    private final List<SpecificOffering> toRemove = new ArrayList<>();

    @FXML

    void onSearchPerson(ActionEvent event) {
        Member member = memberFinderDialog.findEntity();
        if (member != null) {
            unBindMember();
            this.firstnameLB.textProperty().unbind();
            this.entity.setMember(member);
            bindMember();
        }
    }

    @FXML
    void onAddPerson(ActionEvent event) {
        memberEditorDialog.setHeadTitle("Crear nuevo miembro.");
        Member member = memberEditorDialog.editEntity(new Member());
        if (member != null) {
            unBindMember();
            member = memberService.saveMember(member);
            this.entity.setMember(member);
            bindMember();
        }
    }

    @FXML
    void onEditSpecialOffering(ActionEvent event) {
        if (tableView.getSelectionModel().getSelectedIndex() >= 0) {
            specificOfferingEditorDialog.setHeadTitle("Editar ofrenda especifica.");
            SpecificOffering offering = tableView.getSelectionModel().getSelectedItem();
            offering = specificOfferingEditorDialog.editEntity(offering);
            if (offering != null) {
                offerings.remove(tableView.getSelectionModel().getSelectedIndex());
                offerings.add(offering);
            }
        }
    }

    @FXML
    void onRemoveSpecialOffering(ActionEvent event) {
        if (tableView.getSelectionModel().getSelectedIndex() >= 0) {
            SpecificOffering removed = offerings.remove(tableView.getSelectionModel().getSelectedIndex());
            if (removed.getId() != 0) {
                removed.setPacket(null);
                toRemove.add(removed);
            }
        }
    }

    @FXML
    void onAddSpecialOffering(ActionEvent event) {
        specificOfferingEditorDialog.setHeadTitle("Crear nueva ofrenda especifica.");
        SpecificOffering offering = specificOfferingEditorDialog.editEntity(new SpecificOffering());
        if (offering != null) {
            offering.setPacket(this.entity);
            offerings.add(offering);
        }
    }

    @FXML
    void onClear(ActionEvent event) {
        this.entity.setMember(new Member());
        this.entity.setOffering(0.0);
        this.entity.setTithe(0.0);
        offerings.clear();
    }

    @Override
    public void setEditableEntity(Packet entity) {
        this.entity = entity;
        if (this.entity != null) {
            bind();
        }
    }

    private void bind() {
        packetNumberLB.textProperty().bind(this.entity.getNumberProperty().asString());
        bindMember();
        titheTF.textProperty().bindBidirectional(this.entity.getTitheProperty(), getConverter());
        this.entity.getTitheProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            totalize();
        });
        offeringTF.textProperty().bindBidirectional(this.entity.getOfferingProperty(), getConverter());
        this.entity.getOfferingProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            totalize();
        });
        offerings.clear();
        offerings.addAll(this.entity.getSpecificOfferings());
    }

    private void unBind() {
        packetNumberLB.textProperty().unbind();
        unBindMember();
        Bindings.unbindBidirectional(titheTF.textProperty(), this.entity.getTitheProperty());
        Bindings.unbindBidirectional(offeringTF.textProperty(), this.entity.getOfferingProperty());
    }

    private void bindMember() {
        lastnameLB.textProperty().bind(this.entity.getMember().getLastnameProperty());
        firstnameLB.textProperty().bind(this.entity.getMember().getFirstnameProperty());
        validate();
    }

    private void unBindMember() {
        lastnameLB.textProperty().unbind();
        firstnameLB.textProperty().unbind();
    }

    private void totalize() {
        totalLB.setText(getTotal().toString());
        validate();
    }

    private Double getTotal() {
        Double total = 0.0;
        total += this.entity.getTithe() + this.entity.getOffering();
        total = offerings.stream().map((specificOffering) -> specificOffering.getAmount()).reduce(total, (accumulator, _item) -> accumulator + _item);
        return total;
    }

    private void validate() {
        notValidProperty.set(
                (getTotal() == 0.0)
                || (firstnameLB.getText().isEmpty())
        );
    }

    @Override
    public void clear() {
        unBind();
        toRemove.clear();
    }

    @Override
    public void init() {
        try {
            loadController("/fxml/general/forms/PacketFormEditor.fxml", this);
        } catch (IOException ex) {
            Logger.getLogger(AccountsGroupFormEditorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        notValidProperty.set(true);
        titheTF.addEventHandler(KeyEvent.KEY_TYPED, numeric_Validation(Integer.MAX_VALUE));
        titheTF.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            totalize();
        });
        titheTF.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            titheTF.setText(formatter.format(this.entity.getTithe()));
        });
        offeringTF.addEventHandler(KeyEvent.KEY_TYPED, numeric_Validation(Integer.MAX_VALUE));
        offeringTF.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            totalize();
        });
        offeringTF.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            offeringTF.setText(formatter.format(this.entity.getOffering()));
        });
        offerings.addListener((ListChangeListener.Change<? extends SpecificOffering> c) -> {
            totalize();
        });
        initSOTableView();
    }

    private void initSOTableView() {
        TableColumn<SpecificOffering, String> accountNameColumn = new TableColumn<>("Cuenta:");
        accountNameColumn.setCellValueFactory(cellData -> cellData.getValue().getAccountsPlanItem().getAccount().getNameProperty());
        TableColumn<SpecificOffering, Number> amountColumn = new TableColumn<>("Monto:");
        amountColumn.setCellValueFactory(cellData -> cellData.getValue().getAmountProperty());
        tableView.getColumns().addAll(accountNameColumn, amountColumn);
        tableView.setItems(offerings);
    }

    @Override
    public Packet getEditedEntity() {
        this.entity.setSpecificOfferings(new TreeSet<>(offerings));
        toRemove.stream().forEach((specificOffering) -> {
            this.entity.getSpecificOfferings().add(specificOffering);
        });
        return super.getEditedEntity();
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

    public void setSpecificOfferingEditorDialog(GeneralEditorDialog<SpecificOffering> specificOfferingEditorDialog) {
        this.specificOfferingEditorDialog = specificOfferingEditorDialog;
    }

    public void setMemberService(IMemberService memberService) {
        this.memberService = memberService;
    }

    public void setPacketService(IPacketService packetService) {
        this.packetService = packetService;
    }

}
