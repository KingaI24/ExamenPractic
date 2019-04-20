package UI;

import Domain.PopularityReportVM;
import Service.ServiceBorrow;
import Service.ServiceCar;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ControllerPopularityReport {
    public TableView tblPopularityCars;
    public TableColumn tableColumnCarID;
    public TableColumn tableColumnCarModel;
    public TableColumn tableColumnDaysBorrowed;

    private ObservableList<PopularityReportVM> popularity = FXCollections.observableArrayList();
    private ServiceCar serviceCar;
    private ServiceBorrow serviceBorrow;

    public void setServices(ServiceCar serviceCar, ServiceBorrow serviceBorrow) {
        this.serviceCar = serviceCar;
        this.serviceBorrow = serviceBorrow;
    }

    @FXML
    public void initialize(){
        Platform.runLater(() -> {
            popularity.addAll(serviceBorrow.reportPopularity());
            tblPopularityCars.setItems(popularity);
        });
    }

}
