package com.mq310.alfolisoft.controllers.countings;

import com.mq310.alfolisoft.controllers.AdministratorsInitialViewController;
import com.mq310.alfolisoft.controllers.GeneralController;
import com.mq310.alfolisoft.controllers.editors.GeneralEditorDialog;
import com.mq310.dao.services.ICountingService;
import com.mq310.ent.org.counting.Counting;
import com.mq310.ent.org.counting.Packet;
import com.mq310.ent.org.counting.docs.BankDocumentType;
import com.mq310.ent.org.counting.docs.IncomingBankDocument;
import com.mq310.ent.persons.Member;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Objects;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialogs;

public class CountingsCreationViewController extends GeneralController {

    public static final String VIEW_NAME = "countingsCreationView";
    public static final String VIEW_PATH = CountingsMainViewController.COUNTINGSMAINVIEWNAVIGATOR_PATH + DP + VIEW_NAME;

    public static CountingsCreationViewController create() throws IOException {
        return (CountingsCreationViewController) loadController("/fxml/countings/CountingsCreationView.fxml");
    }

    @FXML
    private Label differenceLB;
    @FXML
    private Label quantityLB;
    @FXML
    private Label totalChecksLB;
    @FXML
    private Label totalCashFormLB;
    @FXML
    private Label totalCashLB;
    @FXML
    private Label totalPacketsLB;
    @FXML
    private Label totalDepositsLB;
    @FXML
    private Label totalTransfersLB;
    @FXML
    private Label totalIncomingLB;

    @FXML
    private Label LB_100;
    @FXML
    private Label LB_50;
    @FXML
    private Label LB_20;
    @FXML
    private Label LB_10;
    @FXML
    private Label LB_5;
    @FXML
    private Label LB_2;
    @FXML
    private Label LB_1;
    @FXML
    private Label LB_050;
    @FXML
    private Label LB_025;
    @FXML
    private Label LB_0125;
    @FXML
    private Label LB_010;
    @FXML
    private Label LB_005;
    @FXML
    private TextField TF_100;
    @FXML
    private TextField TF_50;
    @FXML
    private TextField TF_20;
    @FXML
    private TextField TF_10;
    @FXML
    private TextField TF_5;
    @FXML
    private TextField TF_2;
    @FXML
    private TextField TF_1;
    @FXML
    private TextField TF_050;
    @FXML
    private TextField TF_025;
    @FXML
    private TextField TF_0125;
    @FXML
    private TextField TF_010;
    @FXML
    private TextField TF_005;

    @FXML
    private TextField searchPacketTF;

    @FXML
    private TableView<Packet> packetsTableView;
    @FXML
    private TableView<IncomingBankDocument> depositsTableView;
    @FXML
    private TableView<IncomingBankDocument> checksTableView;
    @FXML
    private TableView<IncomingBankDocument> transfersTableView;

    @FXML
    private AnchorPane mainContent;

    private Counting actualCounting;
    private ICountingService countingService;
    private GeneralEditorDialog<Packet> packetsEditor;
    private GeneralEditorDialog<IncomingBankDocument> documentsEditor;
    private final ObservableList<IncomingBankDocument> documents = FXCollections.observableArrayList();
    private final ObservableList<Packet> packets = FXCollections.observableArrayList();

    @FXML
    void onAddPacket(ActionEvent event) {
        packetsEditor.setHeadTitle("Crear nuevo Sobre.");
        Packet p = new Packet();
        while (p != null) {
            p.setCounting(actualCounting);
            p.setNumber(countingService.getNewPacketNumber());
            p = packetsEditor.editEntity(p);
            if (p != null) {
                countingService.savePacket(p);
                packets.add(p);
                totalize();
                p = new Packet();
            }
        }
    }

    @FXML
    void onEditPacket(ActionEvent event) {
        if (packetsTableView.getSelectionModel().getSelectedIndex() >= 0) {
            Packet packet = packetsTableView.getSelectionModel().getSelectedItem();
            packetsEditor.setHeadTitle("Editar Sobre.");
            packet = packetsEditor.editEntity(countingService.getPacketById(packet.getId()));
            if (packet != null) {
                packets.remove(packetsTableView.getSelectionModel().getFocusedIndex());
                countingService.updatePacket(packet);
                packet = countingService.getPacketById(packet.getId());
                packets.add((packet.getNumber()-1), packet);
                totalize();
            }
        }
    }

    @FXML
    void onChangeNumber(ActionEvent event) {

    }

    @FXML
    void onEditCheck(ActionEvent event) {
        if (checksTableView.getSelectionModel().getSelectedIndex() >= 0) {
            documentsEditor.setHeadTitle("Edicion de cheques.");
            editBankDocument(checksTableView.getSelectionModel().getSelectedItem());
        }
    }

    @FXML
    void onRemoveCheck(ActionEvent event) {
        if(askForElimination()){
            deleteBankDocument(checksTableView.getSelectionModel().getSelectedItem());
        }
    }

    @FXML
    void onAddCheck(ActionEvent event) {
        documentsEditor.setHeadTitle("Creacion de nuevo cheque.");
        editBankDocument(new IncomingBankDocument(BankDocumentType.CHECK, actualCounting));
    }

    @FXML
    void onEditDeposit(ActionEvent event) {
        if (depositsTableView.getSelectionModel().getFocusedIndex() >= 0) {
            documentsEditor.setHeadTitle("Edicion de deposito.");
            editBankDocument(depositsTableView.getSelectionModel().getSelectedItem());
        }
    }

    @FXML
    void onRemoveDeposit(ActionEvent event) {
        if(askForElimination()){
            deleteBankDocument(depositsTableView.getSelectionModel().getSelectedItem());
        }
    }

    @FXML
    void onAddDeposit(ActionEvent event) {
        documentsEditor.setHeadTitle("Creacion de nuevo deposito.");
        editBankDocument(new IncomingBankDocument(BankDocumentType.DEPOSIT, actualCounting));
    }

    @FXML
    void onEdiTransfer(ActionEvent event) {
        if (transfersTableView.getSelectionModel().getSelectedIndex() >= 0) {
            documentsEditor.setHeadTitle("Edicion de transferencia.");
            editBankDocument(transfersTableView.getSelectionModel().getSelectedItem());
        }
    }

    @FXML
    void onRemoveTransfer(ActionEvent event) {
        if(askForElimination()){
            deleteBankDocument(transfersTableView.getSelectionModel().getSelectedItem());
        }
    }

    @FXML
    void onAddTransfer(ActionEvent event) {
        documentsEditor.setHeadTitle("Creacion de nueva transferencia.");
        editBankDocument(new IncomingBankDocument(BankDocumentType.TRANSFER, actualCounting));
    }

    private void deleteBankDocument(IncomingBankDocument document){
        countingService.deletedDocument(document);
        documents.remove(document);
        totalize();
    }
    
    private void editBankDocument(IncomingBankDocument document) {
        IncomingBankDocument doc = documentsEditor.editEntity(document);
        if (doc != null) {
            if (doc.getId() != 0) {
                documents.removeIf((IncomingBankDocument t) -> {
                    return Objects.equals(t.getId(), doc.getId());
                });
            }
            documents.add(countingService.saveDocument(doc));
            totalize();
        }
    }
    
    private Boolean askForElimination(){
        Action action = Dialogs.create().message("¿Realmente desea eliminar este documento?").title("Confirmacion...").showConfirm();
        if (action != null) {
            return action.toString().equals("YES");
        } else {
            return false;
        }
    }

    @FXML
    void onShowReports(ActionEvent event) {

    }

    @FXML
    void onFinishCounting(ActionEvent event) {
        Double diff = countingService.getDiference();
        Long packQuantity = countingService.getPacketsCount();
        if (diff != 0.0 || packQuantity == 0) {
            if (diff < 0.0) {
                
            } else if (diff > 0.0) {
                
            } else if (packQuantity == 0) {
                
            }
        } else {
            if (countingService.finishCounting()) {
                navigateTo(AdministratorsInitialViewController.VIEW_PATH);
//                actualCounting.getPackets().clear();
//                actualCounting.getPackets().addAll(packets);
//                actualCounting.getIncomingBankDocuments().clear();
//                actualCounting.getIncomingBankDocuments().addAll(documents);
                loadActualCounting();
            }
        }
    }

    @Override
    public Parent getView() {
        return mainContent;
    }

    @Override
    public void init() {
        initPacketsTableView();
        loadActualCounting();
        initCashCountView();
        initTables();
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub

    }
    
    private void initTables() {

        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");

        TableColumn<IncomingBankDocument, String> refColumnChecks = new TableColumn<>("Ref.:");
        refColumnChecks.setCellValueFactory(cellData -> cellData.getValue().getReferenceProperty());
        TableColumn<IncomingBankDocument, String> personColumnChecks = new TableColumn<>("Persona:");
        personColumnChecks.setCellValueFactory((TableColumn.CellDataFeatures<IncomingBankDocument, String> param) -> new SimpleStringProperty(param.getValue().getPerson().getFirstname() + " " + param.getValue().getPerson().getLastname()));
        TableColumn<IncomingBankDocument, String> dateColumnChecks = new TableColumn<>("Fecha:");
        dateColumnChecks.setCellValueFactory((TableColumn.CellDataFeatures<IncomingBankDocument, String> param) -> new SimpleStringProperty(dateFormatter.format(param.getValue().getCreationDate())));
        TableColumn<IncomingBankDocument, String> amountColumnChecks = new TableColumn<>("Monto:");
        amountColumnChecks.setCellValueFactory((TableColumn.CellDataFeatures<IncomingBankDocument, String> param) -> new SimpleStringProperty(param.getValue().getAmount() + " Bs."));
        checksTableView.getColumns().addAll(refColumnChecks, dateColumnChecks, personColumnChecks, amountColumnChecks);

        TableColumn<IncomingBankDocument, String> refColumnDeposits = new TableColumn<>("Ref.:");
        refColumnDeposits.setCellValueFactory(cellData -> cellData.getValue().getReferenceProperty());
        TableColumn<IncomingBankDocument, String> personColumnDeposits = new TableColumn<>("Persona:");
        personColumnDeposits.setCellValueFactory((TableColumn.CellDataFeatures<IncomingBankDocument, String> param) -> new SimpleStringProperty(param.getValue().getPerson().getFirstname() + " " + param.getValue().getPerson().getLastname()));
        TableColumn<IncomingBankDocument, String> dateColumnDeposits = new TableColumn<>("Fecha:");
        dateColumnDeposits.setCellValueFactory((TableColumn.CellDataFeatures<IncomingBankDocument, String> param) -> new SimpleStringProperty(dateFormatter.format(param.getValue().getCreationDate())));
        TableColumn<IncomingBankDocument, String> amountColumnDeposits = new TableColumn<>("Monto:");
        amountColumnDeposits.setCellValueFactory((TableColumn.CellDataFeatures<IncomingBankDocument, String> param) -> new SimpleStringProperty(param.getValue().getAmount() + " Bs."));
        depositsTableView.getColumns().addAll(refColumnDeposits, dateColumnDeposits, personColumnDeposits, amountColumnDeposits);

        TableColumn<IncomingBankDocument, String> refColumnTranfers = new TableColumn<>("Ref.:");
        refColumnTranfers.setCellValueFactory(cellData -> cellData.getValue().getReferenceProperty());
        TableColumn<IncomingBankDocument, String> personColumnTranfers = new TableColumn<>("Persona:");
        personColumnTranfers.setCellValueFactory((TableColumn.CellDataFeatures<IncomingBankDocument, String> param) -> new SimpleStringProperty(param.getValue().getPerson().getFirstname() + " " + param.getValue().getPerson().getLastname()));
        TableColumn<IncomingBankDocument, String> dateColumnTranfers = new TableColumn<>("Fecha:");
        dateColumnTranfers.setCellValueFactory((TableColumn.CellDataFeatures<IncomingBankDocument, String> param) -> new SimpleStringProperty(dateFormatter.format(param.getValue().getCreationDate())));
        TableColumn<IncomingBankDocument, String> amountColumnTranfers = new TableColumn<>("Monto:");
        amountColumnTranfers.setCellValueFactory((TableColumn.CellDataFeatures<IncomingBankDocument, String> param) -> new SimpleStringProperty(param.getValue().getAmount() + " Bs."));
        transfersTableView.getColumns().addAll(refColumnTranfers, dateColumnTranfers, personColumnTranfers, amountColumnTranfers);

        FilteredList<IncomingBankDocument> checks = new FilteredList<>(documents, p -> true);
        checks.setPredicate((IncomingBankDocument t) -> {
            return t.getType() == BankDocumentType.CHECK;
        });

        FilteredList<IncomingBankDocument> deposits = new FilteredList<>(documents, p -> true);
        deposits.setPredicate((IncomingBankDocument t) -> {
            return t.getType() == BankDocumentType.DEPOSIT;
        });

        FilteredList<IncomingBankDocument> transfers = new FilteredList<>(documents, p -> true);
        transfers.setPredicate((IncomingBankDocument t) -> {
            return t.getType() == BankDocumentType.TRANSFER;
        });

        checksTableView.setItems(checks);
        depositsTableView.setItems(deposits);
        transfersTableView.setItems(transfers);

    }

    private void initCashCountView() {
        EventHandler<KeyEvent> handler = (KeyEvent e) -> {
            if (!e.getCharacter().matches("[0-9]")) {
                e.consume();
            }
        };
        TF_100.addEventHandler(KeyEvent.KEY_TYPED, handler);
        TF_50.addEventHandler(KeyEvent.KEY_TYPED, handler);
        TF_20.addEventHandler(KeyEvent.KEY_TYPED, handler);
        TF_10.addEventHandler(KeyEvent.KEY_TYPED, handler);
        TF_5.addEventHandler(KeyEvent.KEY_TYPED, handler);
        TF_2.addEventHandler(KeyEvent.KEY_TYPED, handler);
        TF_1.addEventHandler(KeyEvent.KEY_TYPED, handler);
        TF_050.addEventHandler(KeyEvent.KEY_TYPED, handler);
        TF_025.addEventHandler(KeyEvent.KEY_TYPED, handler);
        TF_0125.addEventHandler(KeyEvent.KEY_TYPED, handler);
        TF_010.addEventHandler(KeyEvent.KEY_TYPED, handler);
        TF_005.addEventHandler(KeyEvent.KEY_TYPED, handler);

        TF_100.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (!newValue) {
                TF_100.setText(this.actualCounting.getCashCount().getCant_100().toString());
                updateCashCount();
            }
        });
        TF_50.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (!newValue) {
                TF_50.setText(this.actualCounting.getCashCount().getCant_50().toString());
                updateCashCount();
            }
        });
        TF_20.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (!newValue) {
                TF_20.setText(this.actualCounting.getCashCount().getCant_20().toString());
                updateCashCount();
            }
        });
        TF_10.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (!newValue) {
                TF_10.setText(this.actualCounting.getCashCount().getCant_10().toString());
                updateCashCount();
            }
        });
        TF_5.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (!newValue) {
                TF_5.setText(this.actualCounting.getCashCount().getCant_5().toString());
                updateCashCount();
            }
        });
        TF_2.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (!newValue) {
                TF_2.setText(this.actualCounting.getCashCount().getCant_2().toString());
                updateCashCount();
            }
        });
        TF_1.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (!newValue) {
                TF_1.setText(this.actualCounting.getCashCount().getCant_1().toString());
                updateCashCount();
            }
        });
        TF_050.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (!newValue) {
                TF_050.setText(this.actualCounting.getCashCount().getCant_050().toString());
                updateCashCount();
            }
        });
        TF_025.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (!newValue) {
                TF_025.setText(this.actualCounting.getCashCount().getCant_025().toString());
                updateCashCount();
            }
        });
        TF_0125.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (!newValue) {
                TF_0125.setText(this.actualCounting.getCashCount().getCant_0125().toString());
                updateCashCount();
            }
        });
        TF_010.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (!newValue) {
                TF_010.setText(this.actualCounting.getCashCount().getCant_010().toString());
                updateCashCount();
            }
        });
        TF_005.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (!newValue) {
                TF_005.setText(this.actualCounting.getCashCount().getCant_005().toString());
                updateCashCount();
            }
        });
        totalCashFormLB.textProperty().bind(totalCashLB.textProperty());
    }

    private void updateCashCount() {
        countingService.updateCashCount();
        totalize();
    }

    private void initPacketsTableView() {

        TableColumn<Packet, Integer> numberColums = new TableColumn<>("#");
        numberColums.setMinWidth(30);
        numberColums.setMaxWidth(30);
        numberColums.setCellValueFactory((TableColumn.CellDataFeatures<Packet, Integer> param) -> new SimpleObjectProperty<>(param.getValue().getNumber()));
        TableColumn<Packet, String> memberColumn = new TableColumn<>("Miembro:");
        memberColumn.setMinWidth(300);
        memberColumn.setCellValueFactory((TableColumn.CellDataFeatures<Packet, String> param) -> {
            Member m = param.getValue().getMember();
            String name = m.getFirstname() + " " + m.getLastname();
            return new SimpleStringProperty(name);
        });
        TableColumn<Packet, String> totalColumn = new TableColumn<>("Total:");
        totalColumn.setMinWidth(100);
        totalColumn.setCellValueFactory((TableColumn.CellDataFeatures<Packet, String> param) -> {
            return new SimpleStringProperty(param.getValue().getPacketTotal().toString() + " Bs");
        });
        packetsTableView.getColumns().addAll(numberColums, memberColumn, totalColumn);

        FilteredList<Packet> filteredData = new FilteredList<>(packets, p -> true);

        searchPacketTF.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            filteredData.setPredicate((Packet t) -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (t.getMember().getFirstname().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (t.getMember().getLastname().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (t.getPacketTotal().toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                return false; // Does not match.
            });
        });

        packetsTableView.setItems(filteredData);
    }

    private void loadActualCounting() {
        if (this.actualCounting != null) {
            unBind();
        }
        this.actualCounting = countingService.getActualCounting();
        bind();
    }

    private void bind() {
        StringConverter<Number> converter = new StringConverter<Number>() {

            @Override
            public String toString(Number object) {
                return object.toString();
            }

            @Override
            public Number fromString(String string) {
                Integer result = 0;

                if ((string != null) && (!string.isEmpty())) {

                    result = Integer.parseInt(string);
                }
                return result;
            }
        };
        
        TF_100.textProperty().bindBidirectional(this.actualCounting.getCashCount().getCant_100Property(), converter);
        TF_50.textProperty().bindBidirectional(this.actualCounting.getCashCount().getCant_50Property(), converter);
        TF_20.textProperty().bindBidirectional(this.actualCounting.getCashCount().getCant_20Property(), converter);
        TF_10.textProperty().bindBidirectional(this.actualCounting.getCashCount().getCant_10Property(), converter);
        TF_5.textProperty().bindBidirectional(this.actualCounting.getCashCount().getCant_5Property(), converter);
        TF_2.textProperty().bindBidirectional(this.actualCounting.getCashCount().getCant_2Property(), converter);
        TF_1.textProperty().bindBidirectional(this.actualCounting.getCashCount().getCant_1Property(), converter);
        TF_050.textProperty().bindBidirectional(this.actualCounting.getCashCount().getCant_050Property(), converter);
        TF_025.textProperty().bindBidirectional(this.actualCounting.getCashCount().getCant_025Property(), converter);
        TF_0125.textProperty().bindBidirectional(this.actualCounting.getCashCount().getCant_0125Property(), converter);
        TF_010.textProperty().bindBidirectional(this.actualCounting.getCashCount().getCant_010Property(), converter);
        TF_005.textProperty().bindBidirectional(this.actualCounting.getCashCount().getCant_005Property(), converter);
        LB_100.textProperty().bind(this.actualCounting.getCashCount().getCant_100Property().multiply(100.0).asString());
        LB_50.textProperty().bind(this.actualCounting.getCashCount().getCant_50Property().multiply(50.0).asString());
        LB_20.textProperty().bind(this.actualCounting.getCashCount().getCant_20Property().multiply(20.0).asString());
        LB_10.textProperty().bind(this.actualCounting.getCashCount().getCant_10Property().multiply(10.0).asString());
        LB_5.textProperty().bind(this.actualCounting.getCashCount().getCant_5Property().multiply(5.0).asString());
        LB_2.textProperty().bind(this.actualCounting.getCashCount().getCant_2Property().multiply(2.0).asString());
        LB_1.textProperty().bind(this.actualCounting.getCashCount().getCant_1Property().multiply(1.0).asString());
        LB_050.textProperty().bind(this.actualCounting.getCashCount().getCant_050Property().multiply(0.50).asString());
        LB_025.textProperty().bind(this.actualCounting.getCashCount().getCant_025Property().multiply(0.25).asString());
        LB_0125.textProperty().bind(this.actualCounting.getCashCount().getCant_0125Property().multiply(0.125).asString());
        LB_010.textProperty().bind(this.actualCounting.getCashCount().getCant_010Property().multiply(0.10).asString());
        LB_005.textProperty().bind(this.actualCounting.getCashCount().getCant_005Property().multiply(0.05).asString());
        packets.clear();
        packets.addAll(this.actualCounting.getPackets());
        documents.clear();
        documents.addAll(this.actualCounting.getIncomingBankDocuments());
        totalize();
    }

    private void unBind() {
        Bindings.unbindBidirectional(TF_100.textProperty(), this.actualCounting.getCashCount().getCant_100Property());
        Bindings.unbindBidirectional(TF_50.textProperty(), this.actualCounting.getCashCount().getCant_50Property());
        Bindings.unbindBidirectional(TF_20.textProperty(), this.actualCounting.getCashCount().getCant_20Property());
        Bindings.unbindBidirectional(TF_10.textProperty(), this.actualCounting.getCashCount().getCant_10Property());
        Bindings.unbindBidirectional(TF_5.textProperty(), this.actualCounting.getCashCount().getCant_5Property());
        Bindings.unbindBidirectional(TF_2.textProperty(), this.actualCounting.getCashCount().getCant_2Property());
        Bindings.unbindBidirectional(TF_1.textProperty(), this.actualCounting.getCashCount().getCant_1Property());
        Bindings.unbindBidirectional(TF_050.textProperty(), this.actualCounting.getCashCount().getCant_050Property());
        Bindings.unbindBidirectional(TF_025.textProperty(), this.actualCounting.getCashCount().getCant_025Property());
        Bindings.unbindBidirectional(TF_0125.textProperty(), this.actualCounting.getCashCount().getCant_0125Property());
        Bindings.unbindBidirectional(TF_010.textProperty(), this.actualCounting.getCashCount().getCant_010Property());
        Bindings.unbindBidirectional(TF_005.textProperty(), this.actualCounting.getCashCount().getCant_005Property());
        LB_100.textProperty().unbind();
        LB_50.textProperty().unbind();
        LB_20.textProperty().unbind();
        LB_10.textProperty().unbind();
        LB_5.textProperty().unbind();
        LB_2.textProperty().unbind();
        LB_1.textProperty().unbind();
        LB_050.textProperty().unbind();
        LB_025.textProperty().unbind();
        LB_0125.textProperty().unbind();
        LB_010.textProperty().unbind();
        LB_005.textProperty().unbind();
    }

    public void totalize() {
        totalCashLB.setText(countingService.getCashTotal() + " Bs.");
        totalChecksLB.setText(countingService.getChecksTotal() + " Bs.");
        totalDepositsLB.setText(countingService.getDepositsTotal() + " Bs.");
        totalTransfersLB.setText(countingService.getTransfersTotal() + " Bs.");
        totalPacketsLB.setText(countingService.getPacketsTotal() + " Bs.");
        totalIncomingLB.setText(countingService.getGeneralTotal() + " Bs.");
        differenceLB.setText(countingService.getDiference() + " Bs.");
        quantityLB.setText(countingService.getPacketsCount().toString());
    }

    public void setCountingService(ICountingService countingService) {
        this.countingService = countingService;
    }

    public void setDocumentsEditor(GeneralEditorDialog<IncomingBankDocument> documentsEditor) {
        this.documentsEditor = documentsEditor;
    }

    public void setPacketsEditor(GeneralEditorDialog<Packet> packetsEditor) {
        this.packetsEditor = packetsEditor;
    }

}
