package com.mq310.alfolisoft.controllers.countings;

import com.mq310.alfolisoft.controllers.GeneralController;
import com.mq310.alfolisoft.controllers.editors.GeneralEditorDialog;
import com.mq310.dao.services.ICountingService;
import com.mq310.ent.org.counting.Counting;
import com.mq310.ent.org.counting.Packet;
import com.mq310.ent.org.counting.docs.IncomingBankDocument;
import java.io.IOException;
import java.util.Objects;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeTableView;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author Moises
 */
public class CountingsMasterViewController extends GeneralController{
    
    public static final String VIEW_NAME = "countingsMasterView";
    public static final String VIEW_PATH = CountingsMainViewController.COUNTINGSMAINVIEWNAVIGATOR_PATH + DP + VIEW_NAME;

    public static CountingsMasterViewController create() throws IOException {
        return (CountingsMasterViewController) loadController("/fxml/countings/CountingsMasterView.fxml");
    }
    
    private Counting actualCounting;
    private ICountingService countingService;
    private GeneralEditorDialog<Packet> packetsEditor;
    private GeneralEditorDialog<IncomingBankDocument> documentsEditor;
    private final ObservableList<IncomingBankDocument> documents = FXCollections.observableArrayList();
    private final ObservableList<Packet> packets = FXCollections.observableArrayList();
    private final ObservableList<Counting> countings = FXCollections.observableArrayList();

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
    void onRemoveDocument(ActionEvent event) {

    }

    @FXML
    void onEditDocument(ActionEvent event) {
        IncomingBankDocument doc = documentsEditor.editEntity(documentsTableView.getSelectionModel().getSelectedItem());
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

    @FXML
    void onShowBinnacle(ActionEvent event) {

    }

    @FXML
    void onShowCountingReport(ActionEvent event) {

    }

    @FXML
    void onCloseReport(ActionEvent event) {

    }

    private void loadSelectedCounting() {
        if (countingsTreeTable.getSelectionModel().getSelectedItem().getValue() instanceof Counting) {
            if (this.actualCounting != null) {
                unBind();
            }
            /*aqui*/
            bind();
        }
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
    
    private void initCountingsTreeTableView(){
    
    }
    
    private void initPacketTableView(){
    
    }
    
    private void initDocumentTableView(){
    
    }
    
    private void totalize() {
        totalCashLB.setText(countingService.getCashTotal() + " Bs.");
        totalChecksLB.setText(countingService.getChecksTotal() + " Bs.");
        totalDepositsLB.setText(countingService.getDepositsTotal() + " Bs.");
        totalTransfersLB.setText(countingService.getTransfersTotal() + " Bs.");
        totalPacketsLB.setText(countingService.getPacketsTotal() + " Bs.");
        totalIncomingLB.setText(countingService.getGeneralTotal() + " Bs.");
        differenceLB.setText(countingService.getDiference() + " Bs.");
        quantityLB.setText(countingService.getPacketsCount().toString());
    }
    
    private void loadCountings(){
        countings.clear();
        countings.addAll(countingService.getAllCounting());
    }
    
    @Override
    public void init() {
        initCountingsTreeTableView();
        initPacketTableView();
        initDocumentTableView();
        loadCountings();
    }

    @Override
    public void update() {
    }

    @Override
    public Parent getView() {
        return mainContent;
    }

    public void setCountingService(ICountingService countingService) {
        this.countingService = countingService;
    }

    public void setPacketsEditor(GeneralEditorDialog<Packet> packetsEditor) {
        this.packetsEditor = packetsEditor;
    }

    public void setDocumentsEditor(GeneralEditorDialog<IncomingBankDocument> documentsEditor) {
        this.documentsEditor = documentsEditor;
    }
    
    @FXML
    private Label totalCashLB;

    @FXML
    private TextField TF_2;

    @FXML
    private TextField TF_1;

    @FXML
    private TextField countingSearchTF;

    @FXML
    private Label LB_10;

    @FXML
    private TextField TF_50;

    @FXML
    private Label LB_50;

    @FXML
    private Label LB_2;

    @FXML
    private Label LB_1;

    @FXML
    private TextField TF_10;

    @FXML
    private TextField TF_5;

    @FXML
    private Label LB_5;

    @FXML
    private TextField documentSearchTF;

    @FXML
    private Label LB_005;

    @FXML
    private Label LB_025;

    @FXML
    private Label LB_100;

    @FXML
    private TreeTableView<Counting> countingsTreeTable;

    @FXML
    private Label differenceLB;

    @FXML
    private TextField TF_010;

    @FXML
    private Label quantityLB;

    @FXML
    private TextField TF_050;

    @FXML
    private TextField packetSearchTf;

    @FXML
    private Label LB_0125;

    @FXML
    private Label LB_20;

    @FXML
    private Label totalChecksLB;

    @FXML
    private Label totalCashFormLB;

    @FXML
    private TextField TF_20;

    @FXML
    private TableView<IncomingBankDocument> documentsTableView;

    @FXML
    private TextField TF_005;

    @FXML
    private AnchorPane mainContent;

    @FXML
    private Label totalPacketsLB;

    @FXML
    private TableView<Packet> packetsTableView;

    @FXML
    private Label LB_010;

    @FXML
    private TextField TF_100;

    @FXML
    private Label totalDepositsLB;

    @FXML
    private Label totalTransfersLB;

    @FXML
    private TextField TF_025;

    @FXML
    private Label LB_050;

    @FXML
    private Label totalIncomingLB;

    @FXML
    private TextField TF_0125;
}
