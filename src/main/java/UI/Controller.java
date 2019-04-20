package UI;

import Domain.Borrow;
import Domain.Car;
import Service.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    
    public Button btnClose;
    public Button btnCloseP;
    public Tab carTabPage;
    public TableView tblCars;
    public TableColumn tableColumnIdPC;
    public TableColumn tableColumnModel;
    public TableColumn tableColumnKmInitial;
    public TableColumn tableColumnPricePerDay;
    public Spinner spnIdC;
    public TextField txtModel;
    public TextField txtKmInitial;
    public TextField txtPricePerDay;
    public Tab borrowTabPage;
    public TableView tblBorrow;
    public TableColumn tableColumnIdB;
    public TableColumn tableColumnIDCar;
    public TableColumn tableColumnDaysBorrowed;
    public TableColumn tableColumnKm;
    public Button btnAddCar;
    public Button btnAddBorrow;
    public Spinner spnId;
    public TextField txtIdCar;
    public TextField txtNoDays;
    public TextField txtKm;
    public Button btnReportIncome;
    public Button btnReportKm;
    public Button btnReportPopularity;
    public TextField txtIdCarI;
    public TextField txtIdCarKm;


    private ServiceCar serviceCar;
    private ServiceBorrow serviceBorrow;

    private ObservableList<Car> cars = FXCollections.observableArrayList();
    private ObservableList<Borrow> borrows = FXCollections.observableArrayList();


    public void setServices(ServiceBorrow serviceBorrow, ServiceCar serviceCar) {
        this.serviceBorrow = serviceBorrow;
        this.serviceCar = serviceCar;
    }

    @FXML
    private void initialize() {

        Platform.runLater(() -> {
                //tableColumnQuantity.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
                cars.addAll(serviceCar.showCars());
                tblCars.setItems(cars);
                borrows.addAll(serviceBorrow.showBorrows());
                tblBorrow.setItems(borrows);
        });
    }


    public void btnCarAddClick(ActionEvent actionEvent) {

        try {
            int id = Integer.parseInt(spnIdC.getValue().toString());
            String model = txtModel.getText();
            Double kmInitial = Double.parseDouble(txtKmInitial.getText());
            Double pricePerDay = Double.parseDouble(txtPricePerDay.getText());

            serviceCar.addOrUpdate(id, model,kmInitial,pricePerDay);
            cars.clear();
            cars.addAll(serviceCar.showCars());

        } catch (Exception ex) {
            PopUp.createPopup(ex.getMessage());
        }
    }

    public void btnCloseClick(ActionEvent actionEvent) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }


    public void btnBorrowAddClick(ActionEvent actionEven) {
        try {
            int id = Integer.parseInt(spnId.getValue().toString());
            int idCar = Integer.parseInt(txtIdCar.getText());
            int noDays = Integer.parseInt(txtNoDays.getText());
            double km = Double.parseDouble(txtKm.getText());

            serviceBorrow.addOrUpdate(id, idCar, noDays,km);
            borrows.clear();
            borrows.addAll(serviceBorrow.showBorrows());

        } catch (Exception ex) {
            PopUp.createPopup(ex.getMessage());
        }
    }

    public void btnReportIncomeClick(ActionEvent actionEvent) {
        try {
            int carAssessed = Integer.parseInt(txtIdCarI.getText());
            PopUp.createPopup(serviceBorrow.incomePerCar(carAssessed).toString());
        } catch (Exception ex) {
            PopUp.createPopup(ex.getMessage());
        }
    }

    public void btnReportKmClick(ActionEvent actionEvent) {
        try {
            int carAssessed = Integer.parseInt(txtIdCarKm.getText());
            PopUp.createPopup(serviceBorrow.kmPerCar(carAssessed).toString());
        } catch (Exception ex) {
            PopUp.createPopup(ex.getMessage());
        }
    }

    public void btnReportPopularityClick(ActionEvent actionEvent) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/PopularityCars.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 400);
        Stage stage = new Stage();
        stage.setTitle("Cars borrowed as per no of days");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);

        ControllerPopularityReport bestSellerController = fxmlLoader.getController();
        bestSellerController.setServices(serviceCar,serviceBorrow);

        stage.showAndWait();


    }

}