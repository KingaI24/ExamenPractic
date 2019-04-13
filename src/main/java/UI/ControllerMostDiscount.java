package UI;

import Domain.PacientDiscountVM;
import Service.ServiceTransaction;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DoubleStringConverter;

public class ControllerMostDiscount {

    public TableColumn tableColumnPacientId;
    public TableView tblMostDiscount;
    public TableColumn tableColumnCumulatedDiscount;

    private ObservableList<PacientDiscountVM> discounts = FXCollections.observableArrayList();
    private ServiceTransaction serviceTransaction;

    public void setService(ServiceTransaction serviceTransaction) {
        this.serviceTransaction = serviceTransaction;
    }

    @FXML
    public void initialize(){
        Platform.runLater(() -> {
            tableColumnCumulatedDiscount.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
            discounts.addAll(serviceTransaction.sortDescDiscountsReceived());
            tblMostDiscount.setItems(discounts);
        });
    }
}
