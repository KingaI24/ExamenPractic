package UI;

import Domain.DrugProduct;
import Domain.Pacient;
import Domain.Transaction;
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
            System.out.println("4. Back");

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

    private void runPacientCrud() {
        while (true) {
            System.out.println("1. Add or update pacient info");
            System.out.println("2. Remove pacient info");
            System.out.println("3. View all pacients");
            System.out.println("4. Back");

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

    private void runProductCrud() {
        while (true) {
            System.out.println("1. Add or update product");
            System.out.println("2. Remove product");
            System.out.println("3. View all products");
            System.out.println("4. Back");

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

            productService.addOrUpdate(id, name, manufacturer, price, needPresciption);

            System.out.println("Product added!");
        } catch (Exception ex) {
            System.out.println("Errors:\n" + ex.getMessage());
        }
    }
}
