package View;

import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.io.InputStream;

/**
 * Interface implémentant les méthodes utiles pour la vue des images
 */
public interface DataView {
    String PATH_ICON = "Images/Icons/icon.jpg";
    String PATH_BG = "Images/Backgrounds/fbg.jpg";
    String PATH_LARROW = "Images/arrows/fleche.png";
    String PATH_RARROW ="Images/arrows/petite_fleche_droite.png";
    String PATH_UARROW ="Images/arrows/petite_fleche_haut.png";
    String PATH_DARROW ="Images/arrows/petite_fleche_bas.png";

    /**
     * Méthode permettant de mettre une image en arrière plan
     *
     * @param root Le root
     * @param path Le chemin vers l'image
     */
    default void setBackGround(Pane root, String path) {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream input = classLoader.getResourceAsStream(path);
        Image image = new Image(input);
        BackgroundImage backgroundimage = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(0, 0, false, false, false, true));
        Background background = new Background(backgroundimage);
        root.setBackground(background);
    }

}
