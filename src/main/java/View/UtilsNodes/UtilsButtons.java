package View.UtilsNodes;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.io.InputStream;

/**
 * Interface implémentant les méthodes utiles des boutons
 */
public interface UtilsButtons {
    /**
     * Rajoute un effet d'ombre au bouton lorsque le curseur est dessus
     *
     * @param but Button
     */
    default void addEffect(Button but) {
        DropShadow shadow = new DropShadow();

        but.addEventHandler(MouseEvent.MOUSE_ENTERED, t -> but.setEffect(shadow));

        but.addEventHandler(MouseEvent.MOUSE_EXITED, e -> but.setEffect(null));
    }

    /**
     * Méthode qui crée un bouton avec une l'image en son centre
     *
     * @param paramName Nom du bouton
     * @param path      chemin vers l'packagebe.ac.ulb.infof307.groupe03.image liée au bouton
     * @return button
     */
    default Button createShapeButtonWithImage(String paramName, String path) {
        Button res = new Button(paramName);
        createSimpleButtonWithImage(path, res);
        return res;
    }

    /**
     * Méthode permettant de créer un bouton avec une image
     *
     * @param path Le chemin de l'image
     * @param res  Le bouton
     */
    default void createSimpleButtonWithImage(String path, Button res) {
        res.setMinSize(50, 20);
        res.setStyle("-fx-background-color: #00cbff;" +
                "-fx-background-radius: 80px;" +
                "-fx-font-size: 15px;" +
                "-fx-text-fill: white;" +
                "-fx-font-family: Helvetica;" +
                "-fx-cursor: hand;");

        ImageView fond = createBanner(path, 15, 15, 1);
        res.setGraphic(fond);
        addEffect(res);
    }

    /**
     * Créer l'Objet ImageView utilisé dans l'application
     *
     * @param url     String path de l'image
     * @param height  int longueur de l'image
     * @param width   int largeur de l'image
     * @param opacity double opacité de l'image
     * @return imageview
     */
    default ImageView createBanner(String url, int height, int width, double opacity) {
        ImageView imview = new ImageView(url);
        imview.setFitHeight(height);
        imview.setFitWidth(width);
        imview.setOpacity(opacity);

        return imview;
    }


    /**
     * Créer l'Objet Button(sans image) utilisé partout dans l'application
     *
     * @param name        String nom du boutton
     * @param image       Image du bouton
     * @param preflength  int longueur
     * @param prefwidth   int largeur
     * @param fontSize    int taille du texte
     * @param textColor   String couleur du texte
     * @param backColor   String couleur de fond
     * @param path        String Chemin du background du boutton
     * @param bordercolor Couleur du bord du bouton
     * @return button
     */
    default Button createButton(String name, String image, int preflength, int prefwidth,
                                int fontSize, String textColor, String backColor, String path, String bordercolor) {
        Button btn;
        if (image != null) {
            ClassLoader classLoader = UtilsView.class.getClassLoader();
            InputStream input = classLoader.getResourceAsStream(image);
            Image img = new Image(input);
            ImageView imview = new ImageView(img);
            imview.setFitHeight(20);
            imview.setFitWidth(20);

            btn = new Button(name, imview);
        } else {
            btn = new Button(name);
        }

        btn.setPrefSize(preflength, prefwidth);
        String str = "-fx-background-radius: 80px;" +
                "-fx-font-size:" + fontSize + "px;" +
                "-fx-text-fill:" + textColor + ";" +
                "-fx-font-family: Helvetica;" +
                "-fx-background-color:" + backColor + ";" +
                "-fx-cursor: hand;" +
                "-fx-border-color:" + bordercolor + ";";
        if (path != null) {
            str += "-fx-background-image: url(" + path + ");" +
                    "-fx-background-repeat: no-repeat;" +
                    "-fx-background-position: center;";
        }
        btn.setStyle(str);
        addEffect(btn);

        return btn;
    }


    /**
     * Crée un espace où se situeront des boutons
     *
     * @param liste liste des boutons à placer
     * @return un espace de boutons
     */
    default GridPane createButtonPanel(Button[] liste) {
        GridPane temp = new GridPane();
        int total = liste.length;
        int x = 2;
        int y = total / x;

        temp.setPadding(new Insets(10, 10, 10, 10));
        temp.setVgap(5);
        temp.setHgap(5);
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                temp.add(liste[i * y + j], i, j);
            }
        }

        return temp;
    }

}
