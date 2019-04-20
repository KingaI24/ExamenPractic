import Domain.Borrow;
import Domain.Car;
import Repository.IRepository;
import Repository.JsonRepository;
import Service.ServiceBorrow;
import Service.ServiceCar;
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
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CarService.fxml"));
            scene.setRoot(fxmlLoader.load());

            IRepository<Car> carRepository = new JsonRepository<>("Cars.json", Car.class);
            IRepository<Borrow> borrowRepository = new JsonRepository<>("Borrows.json", Borrow.class);

            ServiceCar carService = new ServiceCar(carRepository);
            ServiceBorrow borrowService = new ServiceBorrow(borrowRepository,carRepository);

            Controller controller = fxmlLoader.getController();
            controller.setServices(borrowService,carService);

            primaryStage.setTitle("Car Service");
            primaryStage.setScene(scene);
            primaryStage.show();

        }

        public static void Main(String[] args){
            launch(args);
        }
}
