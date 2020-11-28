package ADBTest;


import Controller.MainController;
import Model.Generator.SQLGenerator;
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
        //SQLGenerator.generatePersos(5000,SQLDatabase.getInstance());
        HostServices instance = getHostServices();
        MainController mainController = new MainController(primaryStage, instance);
        mainController.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

