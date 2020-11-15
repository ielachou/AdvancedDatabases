package Controller;


import Controller.HomeControllers.HomeController;
import View.DataView;
import View.HomeViews.HomePage;
import javafx.application.HostServices;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;

/**
 * Controleur du main
 */
public class MainController {
    private HomeController homeControler;
    /**
     * Constructeur
     *
     * @param primaryStage Stage principal du projet
     * @param instance     instance du projet
     */
    public MainController(Stage primaryStage, HostServices instance){
        setStage(primaryStage);
        HomePage loginPage = new HomePage();
        homeControler = new HomeController(loginPage, primaryStage);
        //homeControler.setDb(Database.getInstance(DatabaseInfo.PATHDB));
        homeControler.setInstance(instance);
    }

    private void setStage(Stage primaryStage) {
        setIcon(primaryStage);
        setTitle(primaryStage);
    }

    private void setTitle(Stage primaryStage) {
        primaryStage.setTitle("Advanced Database");
    }

    private void setIcon(Stage primaryStage) {
        String pathIcon = DataView.PATH_ICON;
        ClassLoader classLoader = getClass().getClassLoader();
        try (InputStream icon = classLoader.getResourceAsStream(pathIcon)) {
            primaryStage.getIcons().add(new Image(icon));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Affiche le projet
     */
    public void show() {
        homeControler.show();
    }
}
