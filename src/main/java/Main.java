import Domain.*;
import Repository.IRepository;
import Repository.JsonRepository;
import Service.ServicePacient;
import Service.ServiceProduct;
import Service.ServiceTransaction;
import UI.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class Main extends Application {

        @Override
        public void start(Stage primaryStage) throws Exception {
            Scene scene = new Scene(new StackPane());
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Pharmacy.fxml"));
            scene.setRoot(fxmlLoader.load());

            IValidator<DrugProduct> productValidator = new DrugProductValidator();
            IValidator<Pacient> pacientValidator = new PacientValidator();
            IValidator<Transaction> transactionValidator = new TransactionValidator();

            //IRepository<DrugProduct> productRepository = new GeneralRepository<>(productValidator);
            //IRepository<Pacient> pacientRepository = new GeneralRepository<>(pacientValidator);
            //IRepository<Transaction> transactionRepository = new GeneralRepository<>(transactionValidator);

            IRepository<DrugProduct> productRepository = new JsonRepository<>(productValidator, "Products.json", DrugProduct.class);
            IRepository<Pacient> pacientRepository = new JsonRepository<>(pacientValidator, "Pacients.json", Pacient.class);
            IRepository<Transaction> transactionRepository = new JsonRepository<>(transactionValidator, "Transactions.json", Transaction.class);

            ServiceProduct productService = new ServiceProduct(productRepository);
            ServicePacient pacientService = new ServicePacient(pacientRepository);
            ServiceTransaction transactionService = new ServiceTransaction(transactionRepository, productRepository, pacientRepository);

            DrugProduct dp1 = new DrugProduct(1, "Faringosept", "Terapia", 12, false);
            productRepository.add(dp1);
            DrugProduct dp2 = new DrugProduct(2, "Echinaceae", "Parafarm", 6, false);
            productRepository.add(dp2);
            DrugProduct dp3 = new DrugProduct(3, "ParacetamolSinus", "Zentiva", 5.75, false);
            productRepository.add(dp3);
            DrugProduct dp4 = new DrugProduct(4, "Augmentin", "GSK", 9.60, true);
            productRepository.add(dp4);
            Pacient p1 = new Pacient(1, "2900912123123", "Marin", "Petra", "12.09.1990", "12.12.2018");
            pacientRepository.add(p1);
            Pacient p2 = new Pacient(2, "2891212123124", "Marin", "Diana", "12.12.1989", "12.12.2018");
            pacientRepository.add(p2);
            Pacient p3 = new Pacient(3, "1940812123125", "Marin", "Vasilica", "12.08.1994", "12.12.2018");
            pacientRepository.add(p3);
            Transaction t1 = new Transaction(1, 1, 1, 1, "12.12.2018 12:12", 12, 2, 10);
            transactionRepository.add(t1);
            Transaction t2 = new Transaction(2, 2, 2, 1, "12.01.2019 13:15", 12, 3, 10);
            transactionRepository.add(t2);
            Transaction t3 = new Transaction(3, 1, 2, 1, "12.04.2019 12:19", 12, 2, 10);
            transactionRepository.add(t3);
            //Console console = new Console(pacientService, productService, transactionService);
            //console.run();
            //ConsoleNL console1 = new ConsoleNL(pacientService, productService, transactionService);
            //console1.run();

            Controller controller = fxmlLoader.getController();
            controller.setServices(pacientService,productService,transactionService);

            primaryStage.setTitle("Pharmacy");
            primaryStage.setScene(scene);
            primaryStage.show();

        }

        public static void Main(String[] args){
            launch(args);
        }
}
