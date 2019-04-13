package UI;

import Domain.*;
import Service.ServicePacient;
import Service.ServiceProduct;
import Service.ServiceTransaction;
import java.util.Scanner;

public class Console {
    private ServicePacient patientService;
    private ServiceProduct productService;
    private ServiceTransaction transactionService;
    private Scanner scanner;

    public Console(ServicePacient patientService, ServiceProduct productService, ServiceTransaction transactionService) {
        this.patientService = patientService;
        this.productService = productService;
        this.transactionService = transactionService;

        this.scanner = new Scanner(System.in);
    }

    private void showMenu() {
        System.out.println("1. Pacient CRUD");
        System.out.println("2. Drug Product CRUD");
        System.out.println("3. Transaction CRUD");
        System.out.println("x. EXIT");
    }

    public void run() {
        while (true) {
            showMenu();

            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    runPacientCrud();
                    break;
                case "2":
                    runProductCrud();
                    break;
                case "3":
                    runTransactionCrud();
                    break;
                case "x":
                    return;
                default:
                    System.out.println("Invalid option!");
                    break;
            }
        }
    }

    private void runTransactionCrud() {
        while (true) {
            System.out.println("1. Add or update a transaction");
            System.out.println("2. Remove a transaction");
            System.out.println("3. View all transactions");
            System.out.println("4. Restrict view transactions");
            System.out.println("5. Delete transactions in interval");
            System.out.println("6. Show best seller based on transaction");
            System.out.println("7. Show card with most discounts received based on transaction");
            System.out.println("8. Back");

            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    handleAddUpdateTransaction();
                    break;
                case "2":
                    handleRemoveTransaction();
                    break;
                case "3":
                    handleViewTransactions();
                    break;
                case "4":
                    restrictViewTransactions();
                    break;
                case "5":
                    handleDeleteTransactionsFromInterval();
                    break;
                case "6":
                    handleViewBestSellers();
                    break;
                case "7":
                    handleViewMostDiscounts();
                    break;
                case "8":
                    handleViewAboveAveragePrices();
                    break;
                case "9":
                    return;
                default:
                    System.out.println("Invalid option!");
                    break;
            }
        }
    }

    private void handleViewTransactions() {
        for (Transaction transaction : transactionService.showAllTransactions()) {
            System.out.println(transaction);
        }
    }

    private void handleViewBestSellers() {
        for (ProductSoldVM p: transactionService.sortDescSell()){
            System.out.println(p.getName() + "--->" + p.getAmount());
        }
    }

    private void handleViewMostDiscounts() {
        for (PacientDiscountVM d: transactionService.sortDescDiscountsReceived()) {
            System.out.println(d.getIdCard() + "--->" + d.getDiscount());
        }
    }

    private void restrictViewTransactions() {
        try {
            System.out.print("Enter lower level (format: dd.MM.yyyy HH:mm): ");
            String date1 = scanner.nextLine();
            System.out.print("Enter upper level (format: dd.MM.yyyy HH:mm): ");
            String date2 = scanner.nextLine();
            for (Transaction transaction : transactionService.restrictTransactions(date1, date2)) {
                System.out.println(transaction);
            }
        } catch (Exception ex) {
            System.out.println("Errors:\n" + ex.getMessage());
        }
    }

    private void handleRemoveTransaction() {
        try {
            System.out.print("Enter the id to remove:");
            int id = Integer.parseInt(scanner.nextLine());
            transactionService.remove(id);

            System.out.println("Transaction removed!");
        } catch (Exception ex) {
            System.out.println("Errors:\n" + ex.getMessage());
        }
    }

    private void handleAddUpdateTransaction() {
        try {
            System.out.print("Enter id: ");
            int id = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter product id (empty to not change for update): ");
            int idMed = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter client card (empty to not change for update): ");
            int idCard = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter number of items (0 to not change for update): ");
            int quantity = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter date (format: dd.MM.yyyy HH:mm) (empty to not change for update): ");
            String date = scanner.nextLine();

            Transaction transaction = transactionService.addOrUpdate(id, idMed, idCard, quantity, date);
            System.out.println(String.format("Added transaction id=%s, paid price=%f, discount=%f%%", transaction.getId(), transaction.getFinalPrice(), transaction.getDiscount()));
        } catch (Exception ex) {
            System.out.println("Errors:\n" + ex.getMessage());
        }
    }
    private void handleDeleteTransactionsFromInterval() {
        try {
            System.out.print("Enter lower level (format: dd.MM.yyyy HH:mm): ");
            String date1 = scanner.nextLine();
            System.out.print("Enter upper level (format: dd.MM.yyyy HH:mm): ");
            String date2 = scanner.nextLine();
            for (Transaction transaction : transactionService.restrictTransactions(date1, date2)) {
                transactionService.remove(transaction.getId());
            }
        } catch (Exception ex) {
            System.out.println("Errors:\n" + ex.getMessage());
        };
    }

    private void handleViewAboveAveragePrices() {
        for (Transaction transaction : transactionService.aboveAveragePrice()) {
            System.out.println(transaction);
        }
    }

    private void runPacientCrud() {
        while (true) {
            System.out.println("1. Add or update pacient info");
            System.out.println("2. Remove pacient info");
            System.out.println("3. View all pacients");
            System.out.println("4. Search in pacients");
            System.out.println("5. Back");

            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    handleAddUpdatePacient();
                    break;
                case "2":
                    handleRemovePacient();
                    break;
                case "3":
                    handleViewPacient();
                    break;
                case "4":
                    handleSearchPacient();
                    break;
                case "5":
                    return;
                default:
                    System.out.println("Invalid option!");
                    break;
            }
        }
    }

    private void handleViewPacient() {
        for (Pacient pacient : patientService.showPacients()) {
            System.out.println(pacient);
        }
    }

    private void handleRemovePacient() {
        try {
            System.out.print("Enter the id to remove:");
            int id = Integer.parseInt(scanner.nextLine());
            patientService.remove(id);

            System.out.println("Pacient info removed!");
        } catch (Exception ex) {
            System.out.println("Errors:\n" + ex.getMessage());
        }
    }

    private void handleAddUpdatePacient() {
        try {
            System.out.print("Enter id: ");
            int id = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter CNP (empty to not change for update): ");
            String CNP = scanner.nextLine();
            System.out.print("Enter first name (empty to not change for update): ");
            String firstName = scanner.nextLine();
            System.out.print("Enter last name (empty to not change for update): ");
            String lastName = scanner.nextLine();
            System.out.print("Enter date of birth (dd.MM.yyyy) (empty to not change for update): ");
            String dateOfBirth = scanner.nextLine();
            System.out.print("Enter date of registration (dd.MM.yyyy) (empty to not change for update): ");
            String dateOfRegistration = scanner.nextLine();

            patientService.addOrUpdate(id, CNP, firstName, lastName,dateOfBirth, dateOfRegistration);

            System.out.println("Pacient added!");
        } catch (Exception ex) {
            System.out.println("Errors:\n" + ex.getMessage());
        }
    }

    private void handleSearchPacient() {
        try {
            System.out.print("Search text in pacient info: ");
            String text = scanner.nextLine();
            for (Pacient pacient : patientService.fullTextSearch(text)) {
                System.out.println(pacient);
            }
        } catch (Exception ex) {
            System.out.println("Errors:\n" + ex.getMessage());
        }
    }

    private void runProductCrud() {
        while (true) {
            System.out.println("1. Add or update product");
            System.out.println("2. Remove product");
            System.out.println("3. View all products");
            System.out.println("4. Search in products");
            System.out.println("5. Back");

            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    handleAddUpdateProduct();
                    break;
                case "2":
                    handleRemoveProduct();
                    break;
                case "3":
                    handleViewProducts();
                    break;
                case "4":
                    handleSearchProduct();
                    break;
                case "5":
                    return;
                default:
                    System.out.println("Invalid option!");
                    break;
            }
        }
    }

    private void handleViewProducts() {
        for (DrugProduct product : productService.showProducts()) {
            System.out.println(product);
        }
    }

    private void handleRemoveProduct() {
        try {
            System.out.print("Enter the id to remove:");
            int id = Integer.parseInt(scanner.nextLine());
            productService.remove(id);

            System.out.println("Product removed!");
        } catch (Exception ex) {
            System.out.println("Errors:\n" + ex.getMessage());
        }
    }

    private void handleAddUpdateProduct() {

        try {
            System.out.print("Enter id: ");
            int id = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter name (empty to not change for update): ");
            String name = scanner.nextLine();
            System.out.print("Enter manufacturer (empty to not change for update): ");
            String manufacturer = scanner.nextLine();
            System.out.print("Enter price (0 to not change for update): ");
            double price = Double.parseDouble(scanner.nextLine());
            System.out.print("Enter prescription needed (true / false): ");
            boolean needPresciption = Boolean.parseBoolean(scanner.nextLine());
            System.out.print("Enter initial stock: ");
            int stock = Integer.parseInt(scanner.nextLine());

            productService.addOrUpdate(id, name, manufacturer, price, needPresciption);

            System.out.println("Product added!");
        } catch (Exception ex) {
            System.out.println("Errors:\n" + ex.getMessage());
        }
    }

    private void handleSearchProduct() {
        try {
            System.out.print("Search text in product info: ");
            String text = scanner.nextLine();
            for (DrugProduct product : productService.fullTextSearch(text)) {
                System.out.println(product);
            }
        } catch (Exception ex) {
            System.out.println("Errors:\n" + ex.getMessage());
        }
    }
}
