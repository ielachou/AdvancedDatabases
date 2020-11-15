package View;

import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;

/**
 * Class représentant la scene principal du projet
 */
public class MainScene {
    private Scene scene;
    private double longueur;
    private double largeur;

    /**
     * Construit la scene principal de notre application
     *
     * @param root Un root
     */
    public MainScene(Parent root) {
        initScene(root);
    }

    /**
     * Méthode permettant d'initialiser une scene
     *
     * @param root Un root
     */
    private void initScene(Parent root) {
        setWindowSize();
        this.scene = new Scene(root, longueur, largeur);
    }

    /**
     * Méthode permettant de modifier la taille d'une fenêtre
     */
    private void setWindowSize() {
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        longueur = primScreenBounds.getMaxX() * 0.75;
        largeur = primScreenBounds.getMaxY() * 0.75;
    }

    public Scene getScene() {
        return scene;
    }
}
