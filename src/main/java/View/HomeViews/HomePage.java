package View.HomeViews;


import View.DataView;
import View.UtilsNodes.UtilsButtons;
import View.UtilsNodes.UtilsLayout;
import View.UtilsNodes.UtilsTextManager;
import View.UtilsNodes.UtilsView;
import View.ViewInterface;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 * Class LoginPage qui regroupe la fenetre de connection de l'application
 */
public class HomePage implements UtilsLayout, UtilsButtons, UtilsTextManager, UtilsView, ViewInterface, DataView {
    private Listener listener;
    private final AnchorPane root;
    private final BorderPane border;
    private Button login;
    private Button register;
    private Label connexLabel;
    private Label accessLabel;
    private Label questionLabel;
    private Label errorLabel;
    private Line line;


    /**
     * Constucteur permettant de créer la fenetre d'acceuil(login) de l'application
     */
    public HomePage() {
        this.border = new BorderPane();
        this.root = new AnchorPane();
        makeRectangle();
        makeTextFields();
        makeButtons();
        makeLabels();
        makeLine();
        makeBanner();
        initPage();
    }

    /**
     * Fonction permettant de créer le rectangle blanc du fond
     */
    private void makeRectangle() {
        createRectangle(500, 500, "-fx-background-color: rgba(255,255,255,0.9);", true, root);
    }

    /**
     * Créer les textfields de la page ainsi que les passwordfields
     */
    private void makeTextFields() {
    }

    /**
     * Créer les bouttons différents bouttons présent sur la page
     */
    private void makeButtons() {
        login = createButton("Se connecter", null, 300, 40, 20,
                "white", "#138c24", null, null);
        login.setDefaultButton(true);

        register = createButton("S'inscrire", null, 300, 40, 20,
                "white", "#00aee5", null, null);
    }


    /**
     * Créer les différents labels de la page
     */
    private void makeLabels() {
        connexLabel = createLabel("Connexion", 30, 0, "black", "white", "bold",
                0.65);
        accessLabel = createLabel("Accéder a TikZOverflow", 15, 0, "black", "white",
                "bold", 0.65);
        questionLabel = createLabel(" Pas encore membre ? ", 15, 20, "black", "white",
                "normal", 1);
        errorLabel = createLabel("Informations incorrectes", 20, 0, "red", null,
                "bold", 1);
        errorLabel.setVisible(false);
    }

    /**
     * Créer les lignes démarcatrice de la page
     */
    private void makeLine() {
        line = createLine(-300.0, 0.0, 400.0, 0.0, 4, Color.rgb(75, 78, 73));
    }

    /**
     * Créer la bannière tikzoverflow supérieure
     */
    private void makeBanner() {
    }

    /**
     * Méthode permettant d'initialiser une page
     */
    private void initPage() {
        makeLayouts();
        setButtonActions();
        setBackGround(this.root, PATH_BG);
    }

    /**
     * Méthode permette de créer les layouts d'une page
     */
    private void makeLayouts() {
        this.root.getChildren().add(this.border);
        //HBox imageBox = (HBox) createBox("H", Pos.CENTER, 0, false);
        VBox fieldBox = (VBox) createBox("V", Pos.CENTER, 10, true, connexLabel, accessLabel, login, errorLabel, line, questionLabel, register);
        VBox mainBox = (VBox) createBox("V", Pos.CENTER, 30, false, fieldBox);

        border.setCenter(mainBox);
        centerAnchor(root, border, 10, 10, 10, 10);
    }

    /**
     * Fonction permettant de récuperer le label d'erreur
     *
     * @return le label d'erreur
     */

    public Label getErrorLabel() {
        return errorLabel;
    }

    public void setListener(Listener loginPageListener) {
        this.listener = loginPageListener;
    }

    public Parent getRoot() {
        return root;
    }

    /**
     * Méthode permettant de réaliser une action selon les options disponible
     */
    private void setButtonActions() {
    }

    public interface Listener {
    }
}
