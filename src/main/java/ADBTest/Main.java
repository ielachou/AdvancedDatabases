package ADBTest;


import Controller.MainController;
import Model.Database.ObjectBox.ObjectBoxDatabase;
import Model.Database.SQLDatabase.SQLDatabase;
import Model.Generator.Generator;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.stage.Stage;

/**
 * Classe principale du projet, permet de lancer l'application
 */
public class Main extends Application {
    /**
     * Créer la fenetre principale de l'application
     *
     * @param primaryStage stage
     */
    @Override
    public void start(Stage primaryStage) {
        Generator OBGenerator = new Generator(ObjectBoxDatabase.getInstance());
        Generator SQLGenerator = new Generator(SQLDatabase.getInstance());

        System.out.println("OBJECTBOX");
        System.out.println("--------------------");
        OBGenerator.getTimeOperations(50000);
        System.out.println("SQLITE");
        System.out.println("--------------------");
        SQLGenerator.getTimeOperations(100000);


        HostServices instance = getHostServices();
        MainController mainController = new MainController(primaryStage, instance);
        mainController.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

