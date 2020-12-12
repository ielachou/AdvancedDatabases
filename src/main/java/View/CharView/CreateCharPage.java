package View.CharView;

import Controller.CharControllers.CreateCharController;
import View.DataView;
import View.UtilsNodes.UtilsButtons;
import View.UtilsNodes.UtilsLayout;
import View.UtilsNodes.UtilsTextManager;
import View.ViewInterface;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.logging.LogManager;

public class CreateCharPage implements DataView, UtilsButtons, UtilsLayout, UtilsTextManager, ViewInterface {
    private final BorderPane border;
    private final AnchorPane root;
    private Label compteLabel;
    private Label confirmErrorLabel;
    private Label dataErrorLabel;
    private Label pseudoLabel;
    private Label sexeLabel;
    private TextField pseudoField;
    private ComboBox comboSexe;
    private Button back;
    private Button confirm;
    private VBox topBox;
    private VBox messageBox;
    private VBox vBoxLayout;
    private HBox bottomBox;
    private Listener listener;

    public CreateCharPage() {
        this.root = new AnchorPane();
        this.border = new BorderPane();
        makeRectangle(600,700);
        makeLabels();
        makeText();
        makeCombo();
        makeButtons();
        makeLayouts();
        setButtonActions();
    }

    private void setButtonActions() {
        this.confirm.setOnAction(e -> listener.create());
        this.back.setOnAction(e -> listener.goBack());
    }

    private void makeLayouts() {
        makeVBox();
        makeHBox();
        VBox mainBox = (VBox) createBox("V", Pos.CENTER, 20, true, topBox, vBoxLayout, messageBox, bottomBox);
        root.getChildren().add(border);
        border.setCenter(mainBox);
        centerAnchor(root, border, 10, 20, 10, 10);
    }

    private void makeHBox() {
        bottomBox = (HBox) createBox("H", Pos.CENTER, 20, true, back, confirm);
    }

    private void makeVBox() {
        topBox = (VBox) createBox("V", Pos.CENTER, 0, true, compteLabel);
        messageBox = (VBox) createBox("V", Pos.CENTER, 0, false, confirmErrorLabel, dataErrorLabel);
        vBoxLayout = (VBox) createBox("V", Pos.CENTER, 0, false, pseudoLabel, pseudoField, sexeLabel, comboSexe);
    }

    private void makeButtons() {
        confirm = createButton(" Confirmer ", null, 170, 30,
                15, "#ffffff", "#3ea43a", null, null);
        back = createButton(" Retour ", null, 170, 30,
                15, "#ffffff", "#ff0000", null, null);

    }

    private void makeCombo() {
        String[] s = {"Male", "Female"};
        comboSexe = createComboBox(s, 180, 30, 300, 60, "Male", "white");
    }

    private void makeText() {
        pseudoField = standardText("Pseudo");
    }

    private TextField standardText(String s) {
        TextField field = createTextField(s, 180, 30, 250, 40, 0, "black",
                "#da1333, white, white", "black", null, null,
                null, null, "0 -1 -1 -1, 0 0 0 0, 0 -1 3 -1");
        return field;
    }

    @Override
    public Parent getRoot() {
        return root;
    }

    /**
     * Fonction permettant de créer le rectangle blanc du fond
     */
    private void makeRectangle(int recheight, int recwidth) {
        createRectangle(recheight, recwidth, "-fx-background-color: rgba(255,255,255);", false, root);
    }

    /**
     * Fonction permettant de créer les différents label présent sur la page Account
     */
    private void makeLabels() {
        compteLabel = createLabel(" Compte ", 30, 0, "black", null,
                "bold", 1);

        confirmErrorLabel = createLabel("Veuillez confirmer les données", 20, 0, "red", null,
                "bold", 1);
        confirmErrorLabel.setVisible(false);

        dataErrorLabel = createLabel("Mauvaise conformités des données", 20, 0, "red", null,
                "bold", 1);
        dataErrorLabel.setVisible(false);

        pseudoLabel = standardLabel("Pseudo: ");
        sexeLabel = standardLabel("Gender: ");
        //passwordLabel = standardLabel("Mot de passe: ");
        //repasswordLabel = standardLabel("Réécrire mdp: ");
    }

    public TextField getPseudoField(){
        return pseudoField;
    }

    public ComboBox getComboSexe() {
        return comboSexe;
    }

    /**
     * Fonction créant les labels documentant les champs de la page
     *
     * @param s string du champ
     */
    private Label standardLabel(String s) {

        return createLabel(s, 20, 0, "black", null, "normal", 1);
    }

    public void setListener(Listener createCharListener) {
        this.listener = createCharListener;
    }

    public interface Listener{
        void goBack();
        void create();
    }
}
