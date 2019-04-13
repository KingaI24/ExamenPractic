package UI;

import Domain.ProductSoldVM;
import Service.ServiceProduct;
import Service.ServiceTransaction;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.ArrayList;

public class ControllerBestSeller {
    public TableView tblBestSeller;
    public TableColumn tableColumnProductName;
    public TableColumn colAmount;

    private ObservableList<ProductSoldVM> sold = FXCollections.observableArrayList();
    private ServiceTransaction serviceTransaction;
    private ServiceProduct serviceProduct;

    public void setServices(ServiceProduct serviceProduct, ServiceTransaction serviceTransaction) {
        this.serviceProduct = serviceProduct;
        this.serviceTransaction = serviceTransaction;
    }

    @FXML
    public void initialize(){
        Platform.runLater(() -> {
            sold.addAll(serviceTransaction.sortDescSell());
            tblBestSeller.setItems(sold);
        });
    }

}
