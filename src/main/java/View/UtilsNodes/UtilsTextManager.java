package View.UtilsNodes;

import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * Interface implémentant les méthodes utiles des textes manager
 */
public interface UtilsTextManager {
    /**
     * Crée les Objets Textfields utilisé dans toute l'application
     *
     * @param prompt      String du promptexte
     * @param preflength  int longueur
     * @param prefwidth   int largeur
     * @param maxlength   int longueur maximum
     * @param maxwidth    int largeur maximum
     * @param backradius  int arrondissement du textfield
     * @param textcolor   String couleur du texte
     * @param backcolor   String couleur de fond
     * @param promptcolor String couleur du promptext
     * @param fontType    String taille du texte
     * @param backImage   String image de fond
     * @param backRep     String répition de l'image
     * @param backPos     String position de l'image
     * @param insets      String écarts
     * @return textfield
     */
    default TextField createTextField(String prompt, int preflength, int prefwidth, int maxlength, int maxwidth,
                                      int backradius, String textcolor, String backcolor, String promptcolor,
                                      String fontType, String backImage, String backRep, String backPos,
                                      String insets) {

        TextField text = new TextField();
        text.setPromptText(prompt);
        text.setPrefSize(preflength, prefwidth);
        text.setMaxSize(maxlength, maxwidth);
        text.setStyle("-fx-background-radius:" + backradius + "px;" +
                "-fx-background-color:" + backcolor + ";" +
                "-fx-text-fill:" + textcolor + ";" +
                "-fx-font-family:" + fontType + ";" +
                "-fx-prompt-text-fill:" + promptcolor + ";" +
                "-fx-background-image:" + backImage + ";" +
                "-fx-background-repeat:" + backRep + ";" +
                "-fx-background-position:" + backPos + ";" +
                "-fx-background-insets:" + insets + ";");

        return text;
    }

    /**
     * Crée les Objets PasswordFields utilisé dans toute l'application
     *
     * @param prompt      String du promptexte
     * @param preflength  int longueur
     * @param prefwidth   int largeur
     * @param maxlength   int longueur maximum
     * @param maxwidth    int largeur maximum
     * @param backradius  int arrondissement du passwordfield
     * @param textcolor   String couleur du texte
     * @param backcolor   String couleur de fond
     * @param promptcolor String couleur du promptext
     * @param fontType    String taille du texte
     * @param backImage   String image de fond
     * @param backRep     String répition de l'image
     * @param backPos     String position de l'image
     * @param insets      String écarts
     * @return passwordfield
     */
    default PasswordField createPasswordField(String prompt, int preflength, int prefwidth, int maxlength,
                                              int maxwidth, int backradius, String textcolor, String backcolor, String promptcolor,
                                              String fontType, String backImage, String backRep, String backPos, String insets) {

        PasswordField pass = new PasswordField();
        pass.setPromptText(prompt);
        pass.setPrefSize(preflength, prefwidth);
        pass.setMaxSize(maxlength, maxwidth);
        pass.setStyle("-fx-background-radius:" + backradius + "px;" +
                "-fx-background-color:" + backcolor + ";" +
                "-fx-text-fill:" + textcolor + ";" +
                "-fx-font-family:" + fontType + ";" +
                "-fx-prompt-text-fill:" + promptcolor + ";" +
                "-fx-background-image:" + backImage + ";" +
                "-fx-background-repeat:" + backRep + ";" +
                "-fx-background-position:" + backPos + ";" +
                "-fx-background-insets:" + insets + ";");

        return pass;
    }

    /**
     * Crée les Objets Label utilisés dans l'application
     *
     * @param s          String nom du label
     * @param fontsize   int taille du label
     * @param backradius int arrondissement des coins
     * @param textcolor  String couleur du label
     * @param backcolor  String couleur de fond
     * @param weight     String graisse du label
     * @param opacity    double opacité du label
     * @return label
     */
    default Label createLabel(String s, int fontsize, int backradius, String textcolor, String backcolor,
                              String weight, double opacity) {

        Label label = new Label(s);
        label.setStyle("-fx-font-size:" + fontsize + "px;" +
                "-fx-background-color:" + backcolor + ";" +
                "-fx-text-fill:" + textcolor + ";" +
                "-fx-font-weight:" + weight + ";" +
                "-fx-background-radius:" + backradius + "px;");
        label.setOpacity(opacity);

        return label;
    }


    /**
     * Créer l'Objet TextArea présent dans l'application
     *
     * @param s          String inclus dans le TextArea
     * @param editable   boolean edition ou non
     * @param preflength int longueur
     * @param prefwidth  int largeur
     * @param maxlength  int longueur maximum
     * @param maxwidth   int largeur maximum
     * @return textarea
     */
    default TextArea createTextArea(String s, boolean editable, int preflength, int prefwidth, int maxlength,
                                    int maxwidth) {
        TextArea tArea = new TextArea(s);
        tArea.setEditable(editable);
        tArea.setPrefSize(preflength, prefwidth);
        tArea.setMaxSize(maxlength, maxwidth);

        return tArea;
    }

}
