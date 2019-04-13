package UI;

import Domain.*;
import Service.ServicePacient;
import Service.ServiceProduct;
import Service.ServiceTransaction;

import java.util.Scanner;

public class ConsoleNL {
    private ServicePacient patientService;
    private ServiceProduct productService;
    private ServiceTransaction transactionService;
    private Scanner scanner;

    public ConsoleNL(ServicePacient patientService, ServiceProduct productService, ServiceTransaction transactionService) {
        this.patientService = patientService;
        this.productService = productService;
        this.transactionService = transactionService;

        this.scanner = new Scanner(System.in);
    }
    public void run() {
        System.out.println("Write requirement (type EXIT to finish)");
        System.out.println("Options: Add/Update | Remove | Show | Search");
        System.out.println("Obs: Add Product (id, name, manufacturer, price, needPresciption)");
        System.out.println("Obs: Add Pacient (id, CNP, firstName, lastName, birthDate (dd.MM.yyyy), registrationDate (dd.MM.yyyy))");
        System.out.println("Obs: Add Transaction (id, idMed, idCard, quantity, date)");
        System.out.println("Obs: Filter Transaction (date1(dd.MM.yyyy HH:mm),date2(dd.MM.yyyy HH:mm))");
        System.out.println("Obs: Delete Transaction in Interval -deleteInterval- (date1(dd.MM.yyyy HH:mm),date2(dd.MM.yyyy HH:mm))");
        System.out.println("Obs: Search(product/pacient) | text to search");
        System.out.println("Obs: Show best seller based on transaction");
        System.out.println("Obs: Show card with most discounts received based on transaction");
        System.out.println("Obs: Remove(id)");
        String r = scanner.nextLine();;
        do {
            interpretRequirements(r);
            r = scanner.nextLine();
        } while (!r.toLowerCase().contains("exit"));
    }

    public void interpretRequirements(String requirement) {
        String[] option = requirement.split(",");
        if (option[0].toLowerCase().contains("product")) {
            if (option[0].toLowerCase().contains("add")) {
                handleAddUpdateProduct(Integer.parseInt(option[1]), option[2], option[3], Double.parseDouble(option[4]), Boolean.parseBoolean(option[5]), Integer.parseInt(option[6]) );
            }
            if (option[0].toLowerCase().contains("remove")) {
                handleRemoveProduct(Integer.parseInt(option[1]));
            }
            if (option[0].toLowerCase().contains("show")) {
                handleViewProducts();
            }
            if (option[0].toLowerCase().contains("search")) {
                handleSearchProduct(option[1]);
            }
        }

        if (option[0].toLowerCase().contains("pacient")) {
            if (option[0].toLowerCase().contains("add")) {
                handleAddUpdatePacient(Integer.parseInt(option[1]), option[2], option[3], option[4], option[5], option[6] );
            }
            if (option[0].toLowerCase().contains("remove")) {
                handleRemovePacient(Integer.parseInt(option[1]));
            }
            if (option[0].toLowerCase().contains("show")) {
                handleViewPacient();
            }
            if (option[0].toLowerCase().contains("search")) {
                handleSearchPacient(option[1]);
            }
        }

        if (option[0].toLowerCase().contains("transaction")) {
            if (option[0].toLowerCase().contains("add")) {
                handleAddUpdateTransaction(Integer.parseInt(option[1]), Integer.parseInt(option[2]), Integer.parseInt(option[3]), Integer.parseInt(option[4]), option[5]);
            }
            if (option[0].toLowerCase().contains("remove")) {
                handleRemoveTransaction(Integer.parseInt(option[1]));
            }
            if (option[0].toLowerCase().contains("show")) {
                handleViewTransactions();
            }
            if (option[0].toLowerCase().contains("filter")) {
                restrictViewTransactions(option[1],option[2]);
            }
            if (option[0].toLowerCase().contains("deleteInterval")) {
                handleDeleteTransactionsFromInterval(option[1], option[2]);
            }
            if (option[0].toLowerCase().contains("bestseller")) {
                handleViewBestSellers();
            }
            if (option[0].toLowerCase().contains("mostdiscounts")) {
                handleViewMostDiscounts();
            }

            if (option[0].toLowerCase().contains("aboveAverage")) {
                handleViewAboveAveragePrices();
            }
        }
    }

    private void handleViewProducts() {
        for (DrugProduct product : productService.showProducts()) {
            System.out.println(product);
        }
    }

    private void handleViewAboveAveragePrices() {
        for (Transaction transaction : transactionService.aboveAveragePrice()) {
            System.out.println(transaction);
        }
    }

    private void handleViewBestSellers() {
        for (ProductSoldVM p: transactionService.sortDescSell()){
            System.out.println(p.getName() + "--->" + p.getAmount());
        }
    }

    private void handleViewMostDiscounts() {
        for (PacientDiscountVM d: transactionService.sortDescDiscountsReceived()){
            System.out.println(d.getIdCard() + "--->" + d.getDiscount());
        }
    }

    private void handleRemoveProduct(int id) {
        try {
            productService.remove(id);

            System.out.println("Product removed!");
        } catch (Exception ex) {
            System.out.println("Errors:\n" + ex.getMessage());
        }
    }

    private void handleAddUpdateProduct(int id, String name, String manufacturer, double price, boolean needPresciption, int stock){
        try {
            productService.addOrUpdate(id, name, manufacturer, price, needPresciption);
            System.out.println("Product added!");
        } catch (Exception ex) {
            System.out.println("Errors:\n" + ex.getMessage());
        }
    }

    private void handleSearchProduct(String text) {
        try {
            for (DrugProduct product : productService.fullTextSearch(text)) {
                System.out.println(product);
            }
        } catch (Exception ex) {
            System.out.println("Errors:\n" + ex.getMessage());
        }
    }

    private void handleViewPacient() {
        for (Pacient pacient : patientService.showPacients()) {
            System.out.println(pacient);
        }
    }

    private void handleRemovePacient(int id) {
        try {
            patientService.remove(id);
            System.out.println("Pacient info removed!");
        } catch (Exception ex) {
            System.out.println("Errors:\n" + ex.getMessage());
        }
    }

    private void handleAddUpdatePacient(int id, String CNP, String firstName, String lastName, String date, String regDate) {
        try {
            patientService.addOrUpdate(id, CNP, firstName, lastName, date, regDate);
            System.out.println("Pacient added!");
        } catch (Exception ex) {
            System.out.println("Errors:\n" + ex.getMessage());
        }
    }

    private void handleSearchPacient(String text) {
        try {
            for (Pacient pacient : patientService.fullTextSearch(text)) {
                System.out.println(pacient);
            }
        } catch (Exception ex) {
            System.out.println("Errors:\n" + ex.getMessage());
        }
    }

    private void handleViewTransactions() {
        for (Transaction transaction : transactionService.showAllTransactions()) {
            System.out.println(transaction);
        }
    }

    private void handleRemoveTransaction(int id) {
        try {
            transactionService.remove(id);

            System.out.println("Transaction removed!");
        } catch (Exception ex) {
            System.out.println("Errors:\n" + ex.getMessage());
        }
    }

    private void handleDeleteTransactionsFromInterval(String date1, String date2) {
        try {
            for (Transaction transaction : transactionService.restrictTransactions(date1, date2)) {
                transactionService.remove(transaction.getId());
            }
        } catch (Exception ex) {
            System.out.println("Errors:\n" + ex.getMessage());
        };
    }

    private void restrictViewTransactions(String date1, String date2) {
        try {
            for (Transaction transaction : transactionService.restrictTransactions(date1,date2)) {
                System.out.println(transaction);
            }
        } catch (Exception ex) {
            System.out.println("Errors:\n" + ex.getMessage());
        }
    }

    private void handleAddUpdateTransaction(int id, int idMed, int idCard, int quantity, String date) {
        try {
            Transaction transaction = transactionService.addOrUpdate(id, idMed, idCard, quantity, date);
            System.out.println(String.format("Added transaction id=%s, paid price=%f, discount=%f%%", transaction.getId(), transaction.getFinalPrice(), transaction.getDiscount()));
        } catch (Exception ex) {
            System.out.println("Errors:\n" + ex.getMessage());
        }
    }
}