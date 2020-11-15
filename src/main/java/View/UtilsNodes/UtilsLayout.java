package View.UtilsNodes;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.*;

/**
 * Interface implémentant les méthodes utiles des layouts
 */
public interface UtilsLayout {
    /**
     * Fonction permettant de créer des box contenant les éléments d'affichage
     *
     * @param type       String qui est le type de box
     * @param alignement Pos alignement de la box
     * @param spacing    int spacing entre les éléments de la box
     * @param growth     boolean aggrandissement automatique des Nodes dans la box
     * @param objects    liste des Nodes a ajouter dans la box
     * @return region
     */
    default Region createBox(String type, Pos alignement, int spacing, boolean growth, Node... objects) {
        Region res = new Region();
        if (type.equals("V")) {
            VBox box;
            box = new VBox(objects);
            res = fillVBox(box, alignement, spacing, growth, objects);
        } else if (type.equals("H")) {
            HBox box;
            box = new HBox(objects);
            res = fillHBox(box, alignement, spacing, growth, objects);
        }
        return res;
    }

    /**
     * Fonction permettant d'ajouter les différents paramètres a la Vbox
     *
     * @param box        Pane representant le type de box
     * @param alignement Pos alignement de la box
     * @param spacing    int spacing entre les éléments de la box
     * @param growth     boolean aggrandissement automatique des Nodes dans la box
     * @param objects    liste des Nodes a ajouter dans la box
     * @return VBox contenant les éléments
     */
    default VBox fillVBox(Pane box, Pos alignement, int spacing, boolean growth, Node... objects) {
        for (int a = 0; a < objects.length; a++) {
            if (growth) {
                VBox.setVgrow(objects[a], Priority.ALWAYS);
            }
        }
        ((VBox) box).setAlignment(alignement);
        ((VBox) box).setSpacing(spacing);
        return (VBox) box;
    }

    /**
     * Fonction permettant d'ajouter les différents paramètres a la Hbox
     *
     * @param box        Pane representant le type de box
     * @param alignement Pos alignement de la box
     * @param spacing    int spacing entre les éléments de la box
     * @param growth     boolean aggrandissement automatique des Nodes dans la box
     * @param objects    liste des Nodes a ajouter dans la box
     * @return HBox contenant les éléments
     */
    default HBox fillHBox(Pane box, Pos alignement, int spacing, boolean growth, Node... objects) {
        for (int a = 0; a < objects.length; a++) {
            if (growth) {
                HBox.setHgrow(objects[a], Priority.ALWAYS);
            }
        }
        ((HBox) box).setAlignment(alignement);
        ((HBox) box).setSpacing(spacing);
        return (HBox) box;
    }

    /**
     * Méthode permettant de centrer l'anchor
     *
     * @param root   Le root
     * @param node   Un noeud
     * @param top    Le haut de la fenêtre
     * @param bottom Le bas de la fenêtre
     * @param left   La gauche de la fenêtre
     * @param right  La droite de la fenêtre
     */
    default void centerAnchor(AnchorPane root, Node node, double top, double bottom, double left, double right) {
        AnchorPane.setTopAnchor(node, top);
        AnchorPane.setRightAnchor(node, right);
        AnchorPane.setLeftAnchor(node, left);
        AnchorPane.setBottomAnchor(node, bottom);
    }

    /**
     * Créer l'Objet ComboBox utilisé dans l'application
     *
     * @param s          String[] liste des éléments
     * @param preflength int longueur
     * @param prefwidth  int largeur
     * @param maxlength  int longueur maximum
     * @param maxwidth   int largeur maximum
     * @param value      String élément par défaut
     * @param backcolor  String couleur de fond
     * @return combobox
     */
    default ComboBox createComboBox(String[] s, int preflength, int prefwidth, int maxlength, int maxwidth,
                                    String value, String backcolor) {

        ComboBox combo = new ComboBox();
        for (int i = 0; i < s.length; i++) {
            combo.getItems().add(s[i]);
        }
        combo.setPrefSize(preflength, prefwidth);
        combo.setMaxSize(maxlength, maxwidth);
        combo.setValue(value);
        combo.setStyle("-fx-background-color:" + backcolor + ";");

        return combo;
    }

    /**
     * Crée une VBox contenant un ToggleGroup
     *
     * @param group   ToggleGroup à placer
     * @param spacing Espace entre les éléments du Toggle
     * @param label   nom du toggle
     * @param a       Varargs pour les éléments du toggle
     * @return une VBox
     */
    default VBox createToggle(ToggleGroup group, int spacing, String label, String... a) {
        Label name = new Label(label);
        HBox box = new HBox();
        for (String s : a) {
            RadioButton elem = new RadioButton(s);
            elem.setToggleGroup(group);
            box.getChildren().add(elem);
            group.selectToggle(elem);
        }
        box.setSpacing(spacing);
        box.setAlignment(Pos.CENTER);
        VBox res = new VBox(name, box);
        res.setSpacing(20);
        res.setAlignment(Pos.CENTER);

        return res;
    }


    /**
     * Créer le rectangle de fond
     *
     * @param h       int hauteur
     * @param w       int largeur
     * @param color   couleur de fond
     * @param opacity boolean pour l'opacité
     * @param root    stackpane
     */
    default void createRectangle(int h, int w, String color, boolean opacity, AnchorPane root) {
        BorderPane bd = new BorderPane();
        Region rect = new Region();
        AnchorPane anchor2 = new AnchorPane();
        rect.setPrefSize(h, w);
        rect.setStyle(color + "-fx-background-radius: 10 10 10 10");
        if (opacity) {
            rect.setOpacity(1);
        }
        bd.setCenter(anchor2);
        anchor2.getChildren().add(rect);
        centerAnchor(anchor2, rect, 2.0, 2.0, 2.0, 2.0);
        centerAnchor(root, bd, 20.0, 20.0, 150.0, 150.0);

        root.getChildren().add(bd);
    }

}
