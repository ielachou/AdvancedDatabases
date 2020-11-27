package ADBTest;



/*
public class ADBTest.Main {

    public static void main(String[] args) {


        Perso perso = new Perso("Achille", 0);
        //box.put(perso);

        System.out.println(box.count() + " notes in ObjectBox database:");
        Perso persoo = box.query().equal(Perso_.name, "Achille").build().findFirst();
        System.out.println(persoo.getName());
        store.close();
    }

}
*/

import Controller.MainController;
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
        HostServices instance = getHostServices();
        MainController mainController = new MainController(primaryStage, instance);
        mainController.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

