package UI;

import Domain.DrugProduct;
import Domain.Pacient;
import Domain.Transaction;
import Service.ServicePacient;
import Service.ServiceProduct;
import Service.ServiceTransaction;
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

    public Spinner spnId;
    public TextField txtCNP;
    public TextField txtFirstName;
    public TextField txtLastName;
    public TextField txtBirthDate;
    public TextField txtRegistrationDate;
    public TableColumn tableColumnId;
    public TableColumn tableColumnCNP;
    public TableColumn tableColumnFirstName;
    public TableColumn tableColumnLastName;
    public TableColumn tableColumnBirthDate;
    public TableColumn tableColumnRegDate;
    public TableView tblPacients;
    public Button btnPacientAdd;
    public Button btnPacientDelete;
    public Button btnClose;
    public TableView tblProduct;
    public TableColumn tableColumnIdP;
    public TableColumn tableColumnName;
    public TableColumn tableColumnManufacturer;
    public TableColumn tableColumnPrice;
    public TableColumn tableColumnNeedPrescription;
    public Spinner spnIdP;
    public TextField txtName;
    public TextField txtManufacturer;
    public TextField txtPrice;
    public CheckBox chkNeedPrescription;
    public Button btnProductAdd;
    public Button btnProductDelete;
    public Button btnCloseP;
    public TableView tblTransaction;
    public TableColumn tableColumnIdT;
    public TableColumn tableColumnIdMed;
    public TableColumn tableColumnIdCard;
    public TableColumn tableColumnQuantity;
    public TableColumn tableColumnDate;
    public TableColumn tableColumnBasePrice;
    public TableColumn tableColumnDiscount;
    public TableColumn tableColumnFinalPrice;
    public Spinner spnIdT;
    public TextField txtIdMed;
    public TextField txtIdCard;
    public TextField txtQuantity;
    public TextField txtDate;
    public Button btnTransactionAdd;
    public Button btnTransactionDelete;
    public Button btnCloseT;
    public Tab transactionTabPage;
    public Tab pacientTabPage;
    public Tab productTabPage;
    public Button btnProductSearch;
    public TextField txtProductSearch;
    public Button btnPacientSearch;
    public TextField txtPacientSearch;
    public Button btnBestSeller;
    public Button btnTransactionsInIntervall;
    public TextField txtDate1;
    public TextField txtDate2;
    public Button btnDeleteFromIntervall;
    public Button btnShowAll;
    public Button btnShowAllPacients;
    public Button btnShowAllProducts;
    public Button btnIncreasePrice;
    public TextField txtPercentage;
    public Button btnShowAllProductsAboveAveragePrice;
    public Button btnShowAllTransactions;
    public Button btnMostDiscounts;
    public Button btnUndo;
    public Button btnRedo;
    public Button btnUndoP;
    public Button btnRedoP;
    public Button btnUndoT;
    public Button btnRedoT;

    private ServicePacient servicePacient;
    private ServiceProduct serviceProduct;
    private ServiceTransaction serviceTransaction;

    private ObservableList<Pacient> pacients = FXCollections.observableArrayList();
    private ObservableList<DrugProduct> products = FXCollections.observableArrayList();
    private ObservableList<Transaction> transactions = FXCollections.observableArrayList();


    public void setServices(ServicePacient servicePacient, ServiceProduct serviceProduct, ServiceTransaction serviceTransaction) {
        this.servicePacient = servicePacient;
        this.serviceProduct = serviceProduct;
        this.serviceTransaction = serviceTransaction;
    }

    @FXML
    private void initialize() {

        Platform.runLater(() -> {
                pacients.addAll(servicePacient.showPacients());
                tblPacients.setItems(pacients);
                products.addAll(serviceProduct.showProducts());
                tblProduct.setItems(products);
                transactions.addAll(serviceTransaction.showAllTransactions());
                tblTransaction.setItems(transactions);
        });
    }


    public void btnPacientAddClick(ActionEvent actionEvent) {

        try {
            int id = Integer.parseInt(spnId.getValue().toString());
            String CNP = txtCNP.getText();
            String firstName = txtFirstName.getText();
            String lastName = txtLastName.getText();
            String dateOfBirth = txtBirthDate.getText();
            String dateOfRegistration = txtRegistrationDate.getText();

            servicePacient.addOrUpdate(id, CNP, firstName, lastName, dateOfBirth, dateOfRegistration);
            pacients.clear();
            pacients.addAll(servicePacient.showPacients());

        } catch (Exception ex) {
            PopUp.createPopup(ex.getMessage());
        }
    }

    public void btnCloseClick(ActionEvent actionEvent) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    public void btnShowAllTransactionsClick(ActionEvent actionEvent) {
        transactions.clear();
        transactions.addAll(serviceTransaction.showAllTransactions());
    }

    public void btnShowAllPacientsClick(ActionEvent actionEvent) {
        pacients.clear();
        pacients.addAll(servicePacient.showPacients());
    }

    public void btnShowAllProductsClick(ActionEvent actionEvent) {
        products.clear();
        products.addAll(serviceProduct.showProducts());
    }

    public void btnPacientDeleteClick(ActionEvent actionEvent) {
        Pacient selectedP = (Pacient) tblPacients.getSelectionModel().getSelectedItem();

        if (selectedP != null) {
            try {
                servicePacient.remove(selectedP.getId());
                pacients.clear();
                pacients.addAll(servicePacient.showPacients());
            } catch (Exception ex) {
                PopUp.createPopup(ex.getMessage());
            }
        }
    }

    public void btnPacientSearchClick(ActionEvent actionEven) {
        try {
            String search = txtPacientSearch.getText().toLowerCase();
            pacients.clear();
            pacients.addAll(servicePacient.fullTextSearch(search));
        } catch (Exception ex) {
            PopUp.createPopup("Not Found!");
        }
    }

    public void btnProductAddClick(ActionEvent actionEven) {
        try {
            int id = Integer.parseInt(spnIdP.getValue().toString());
            String name = txtName.getText();
            String manufacturer = txtManufacturer.getText();
            double price = Double.parseDouble(txtPrice.getText());
            boolean needPresciption = chkNeedPrescription.isSelected();

            serviceProduct.addOrUpdate(id, name, manufacturer, price, needPresciption);
            products.clear();
            products.addAll(serviceProduct.showProducts());

        } catch (Exception ex) {
            PopUp.createPopup(ex.getMessage());
        }
    }

    public void btnProductSearchClick(ActionEvent actionEven) {
        try {
            String search = txtProductSearch.getText().toLowerCase();
            products.clear();
            products.addAll(serviceProduct.fullTextSearch(search));
        } catch (Exception ex) {
            PopUp.createPopup("Not Found!");
        }
    }

    public void btnShowAllProductsAboveAveragePriceClick(ActionEvent actionEvent) {
        products.clear();
        products.addAll(serviceProduct.priceAboveAverage());
    }

    public void btnIncreasePriceClick(ActionEvent actionEven) {
        serviceProduct.priceIncrease(Integer.parseInt(txtPercentage.getText()));
        products.clear();
        products.addAll(serviceProduct.showProducts());
    }

    public void btnProductDeleteClick(ActionEvent actionEvent) {
        DrugProduct selectedP = (DrugProduct) tblProduct.getSelectionModel().getSelectedItem();

        if (selectedP != null) {
            try {
                serviceProduct.remove(selectedP.getId());
                products.clear();
                products.addAll(serviceProduct.showProducts());
            } catch (Exception ex) {
                PopUp.createPopup(ex.getMessage());
            }
        }
    }

    public void btnTransactionAddClick(ActionEvent actionEven) {
        try {
            int id = Integer.parseInt(spnIdT.getValue().toString());
            int idMed = Integer.parseInt(txtIdMed.getText());
            int idCard = Integer.parseInt(txtIdCard.getText());
            int quantity = Integer.parseInt(txtQuantity.getText());
            String date = txtDate.getText();

            serviceTransaction.addOrUpdate(id, idMed, idCard, quantity, date);
            transactions.clear();
            transactions.addAll(serviceTransaction.showAllTransactions());
            products.clear();
            products.addAll(serviceProduct.showProducts());

        } catch (Exception ex) {
            PopUp.createPopup(ex.getMessage());
        }
    }

    public void btnTransactionDeleteClick(ActionEvent actionEvent) {
        Transaction selected = (Transaction) tblTransaction.getSelectionModel().getSelectedItem();

        if (selected != null) {
            try {
                serviceTransaction.remove(selected.getId());
                transactions.clear();
                transactions.addAll(serviceTransaction.showAllTransactions());
            } catch (Exception ex) {
                PopUp.createPopup(ex.getMessage());
            }
        }
    }

    public void btnBestSellerClick(ActionEvent actionEvent) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/main/resources/BestSeller.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 400);
        Stage stage = new Stage();
        stage.setTitle("Sort Products By Amount Sold");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);

        ControllerBestSeller bestSellerController = fxmlLoader.getController();
        bestSellerController.setServices(serviceProduct,serviceTransaction);

        stage.showAndWait();


    }

    public void btnMostDiscountsClick(ActionEvent actionEvent) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/main/resources/MostDiscounts.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 400);
        Stage stage = new Stage();
        stage.setTitle("Pacient Cards with most discounts");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);

        ControllerMostDiscount mostDiscountController = fxmlLoader.getController();
        mostDiscountController.setService(serviceTransaction);

        stage.showAndWait();

    }

    public void btnTransactionsInIntervallClick(ActionEvent actionEvent) {
        try {
            transactions.clear();
            transactions.addAll(serviceTransaction.showAllTransactions());
            String d1 = txtDate1.getText();
            String d2 = txtDate2.getText();
            transactions.clear();
            transactions.addAll(serviceTransaction.restrictTransactions(d1,d2));
        } catch (Exception ex) {
            PopUp.createPopup("Not found!");
        }
    }

    public void btnDeleteFromIntervallClick(ActionEvent actionEvent) {
        try {
            transactions.clear();
            transactions.addAll(serviceTransaction.showAllTransactions());
            String d1 = txtDate1.getText();
            String d2 = txtDate2.getText();
            transactions.removeAll(serviceTransaction.restrictTransactions(d1,d2));
        } catch (Exception ex) {
            PopUp.createPopup(ex.getMessage());
        }
    }

    public void btnUndoClick(ActionEvent actionEvent) {
        serviceProduct.undo();
        products.clear();
        products.addAll(serviceProduct.showProducts());
    }

    public void btnRedoClick(ActionEvent actionEvent) {
        serviceProduct.redo();
        products.clear();
        products.addAll(serviceProduct.showProducts());
    }

    public void btnUndoTClick(ActionEvent actionEvent) {
        serviceTransaction.undo();
        transactions.clear();
        transactions.addAll(serviceTransaction.showAllTransactions());
    }

    public void btnRedoTClick(ActionEvent actionEvent) {
        serviceTransaction.redo();
        transactions.clear();
        transactions.addAll(serviceTransaction.showAllTransactions());
    }

    public void btnUndoPClick(ActionEvent actionEvent) {
        servicePacient.undo();
        pacients.clear();
        pacients.addAll(servicePacient.showPacients());
    }

    public void btnRedoPClick(ActionEvent actionEvent) {
        servicePacient.redo();
        pacients.clear();
        pacients.addAll(servicePacient.showPacients());
    }
}