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

public class GameView implements UtilsLayout, UtilsButtons, UtilsTextManager, UtilsView, ViewInterface, DataView {

    private Listener listener;
    private final AnchorPane root;
    private final BorderPane border;
    private Button fight_btn;
    private Button inventory;

    public GameView() {
        this.border = new BorderPane();
        this.root = new AnchorPane();
        makeButtons();
        initPage();
        setButtonActions();

    }

    private void setButtonActions() {
        inventory.setOnAction(e -> listener.inventory());
        fight_btn.setOnAction(e -> listener.fight());
    }

    private void makeButtons() {
        fight_btn = createButton("Fight", null,
                140, 50, 15, "black",
                "white", null, null);

        inventory = createButton("Inventory", null,
                140, 20, 15, "black",
                "white", null, null);



    }

    private void initPage() {
        setBackGround(root, "Images/Maps/dofus_map_0,0.png");
        makeLayouts();

    }

    private void makeLayouts() {
        root.getChildren().add(border);
        border.setLeft(fight_btn);
        BorderPane.setAlignment(fight_btn, Pos.BOTTOM_CENTER);

        border.setCenter(inventory);
        BorderPane.setAlignment(inventory, Pos.BOTTOM_RIGHT);

        centerAnchor(root, border, 10, 10, 10, 10);
    }

    @Override
    public Parent getRoot() {
        return root;
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public interface Listener{
        void inventory();
        void fight();
    }

}
