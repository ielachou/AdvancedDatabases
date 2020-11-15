package Controller;



import View.MainScene;
import View.ViewInterface;
import javafx.stage.Stage;

/**
 * Interface implémentant les méthodes utiles des controleurs
 */
public interface UtilsController {
    /**
     * Affiche la page
     */
    default void show() {
        Stage primaryStage = getStage();
        ViewInterface view = getView();
        if (primaryStage.getScene() != null) {
            primaryStage.getScene().setRoot(view.getRoot());
        } else {
            MainScene mainScene = new MainScene(view.getRoot());
            primaryStage.setScene(mainScene.getScene());
        }
        primaryStage.show();
        begin();
    }

    /**
     * Ferme la page
     */
    default void close() {
        UtilsController back = getBack();
        if (back != null) {
            back.show(); // Retourne en arrière
        } else {
            Stage primaryStage = getStage();
            primaryStage.close(); // Fermeture de la fenêtre s'il n'y a pas de back
        }
    }

    UtilsController getBack();

    void setBack(UtilsController back);

    default void begin() {
    }


    Stage getStage();

    ViewInterface getView();
}
