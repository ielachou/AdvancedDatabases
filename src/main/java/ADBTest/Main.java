package ADBTest;


import Controller.MainController;
import Model.Database.Database;
import Model.Database.ObjectBox.ObjectBoxDatabase;
import Model.Database.SQLDatabase.SQLDatabase;
import Model.Generator.SQLGenerator;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.stage.Stage;

import static java.lang.System.currentTimeMillis;

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
        // finding the time before the operation is executed
        long start = System.currentTimeMillis();


        SQLGenerator.generatePersos(1000, ObjectBoxDatabase.getInstance());

        //finding the time after the operation is executed
        long end = System.currentTimeMillis();
        //finding the time difference and converting it into seconds
        float sec = (end - start) / 1000F; System.out.println(sec + " seconds for objectbox");

        start = System.currentTimeMillis();
        SQLGenerator.generatePersos(1000, SQLDatabase.getInstance());
        end = System.currentTimeMillis();

        sec = (end - start) / 1000F;
        System.out.println(sec + " seconds for sqlite");

        HostServices instance = getHostServices();
        MainController mainController = new MainController(primaryStage, instance);
        mainController.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

