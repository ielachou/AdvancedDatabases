package View.GameView;

import View.DataView;
import View.UtilsNodes.UtilsButtons;
import View.UtilsNodes.UtilsLayout;
import View.UtilsNodes.UtilsTextManager;
import View.UtilsNodes.UtilsView;
import View.ViewInterface;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class FightView implements UtilsLayout, UtilsButtons, UtilsTextManager, UtilsView, ViewInterface, DataView {

    private Listener listener;
    private final AnchorPane root;
    private final BorderPane border;
    private Button monster1;
    private Button monster2;
    private Button monster3;
    private Button monster4;
    private Button monster5;
    private Button monster6;
    private Button monster7;
    private Button monster8;
    private Button back;

    public FightView() {
        this.root = new AnchorPane();
        this.border = new BorderPane();
        makeButtons();
        makeLayouts();
        setButtonsActions();
    }

    private void setButtonsActions() {
        back.setOnAction(e->listener.back());
    }

    private void makeLayouts() {
        HBox hbox1 = (HBox) createBox("H", Pos.CENTER, 30, true, monster1, monster2, monster3, monster4);
        HBox hbox2 = (HBox) createBox("H", Pos.CENTER, 30, true, monster5, monster6, monster7, monster8);
        VBox mainVBox = (VBox) createBox("V", Pos.CENTER, 30, true, hbox1, hbox2, back);
        border.setCenter(mainVBox);
        root.getChildren().add(border);
        centerAnchor(root, border, 10, 10, 10, 10);
    }

    private void makeButtons() {
        monster1 = createButton("Boroboro", null,
                140, 50, 15, "black",
                "white", null, null);
        monster2 = createButton("Makichta", null,
                140, 50, 15, "black",
                "white", null, null);
        monster3 = createButton("Anxiolite", null,
                140, 50, 15, "black",
                "white", null, null);
        monster4 = createButton("FootAyoub", null,
                140, 50, 15, "black",
                "white", null, null);
        monster5 = createButton("Volkroz", null,
                140, 50, 15, "black",
                "white", null, null);
        monster6 = createButton("Marwill", null,
                140, 50, 15, "black",
                "white", null, null);
        monster7 = createButton("Ninaike", null,
                140, 50, 15, "black",
                "white", null, null);
        monster8 = createButton("Waytspi", null,
                140, 50, 15, "black",
                "white", null, null);
        back = createButton("Back", null, 100, 20, 15, "white", "red",
                null, null);

    }

    @Override
    public Parent getRoot() {
        return root;
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public interface Listener{

        void back();
    }
}
