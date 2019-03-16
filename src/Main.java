import Domain.DrugProductValidator;
import Domain.PacientValidator;
import Domain.TransactionValidator;
import Repository.RepoPacient;
import Repository.RepoProduct;
import Repository.RepoTransaction;
import Service.ServicePacient;
import Service.ServiceProduct;
import Service.ServiceTransaction;
import UI.Console;
import UI.ConsoleNL;

public class Main {

    public static void main(String[] args) {
        DrugProductValidator productValidator = new DrugProductValidator();
        PacientValidator pacientValidator = new PacientValidator();
        TransactionValidator transactionValidator = new TransactionValidator();

        RepoProduct productRepository = new RepoProduct(productValidator);
        RepoPacient pacientRepository = new RepoPacient(pacientValidator);
        RepoTransaction transactionRepository = new RepoTransaction(transactionValidator);

        ServiceProduct productService = new ServiceProduct(productRepository);
        ServicePacient pacientService = new ServicePacient(pacientRepository);
        ServiceTransaction transactionService = new ServiceTransaction(transactionRepository, productRepository);

        //Console console = new Console(pacientService, productService, transactionService);
        //console.run();
        ConsoleNL console1 = new ConsoleNL(pacientService, productService, transactionService);
        console1.run();
    }
}
