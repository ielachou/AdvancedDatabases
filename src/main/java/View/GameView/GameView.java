package View.GameView;

import Controller.GameControllers.GameController;
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
    private Button left_map_change;
    private Button right_map_change;
    private Button up_map_change;
    private Button down_map_change;
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
    }

    private void makeButtons() {
        left_map_change = createButton("", PATH_LARROW,
                140, 50, 15, "black",
                null, null, null);
        /*right_map_change = createButton("", PATH_RARROW,
                140, 50, 15, "black",
                null, null, null);
        up_map_change = createButton("", PATH_UARROW,
                140, 50, 15, "black",
                "white", null, null);
        down_map_change = createButton("", PATH_DARROW,
                140, 50, 15, "black",
                null, null, null);*/

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
        //border.setTop(up_map_change);
        //BorderPane.setAlignment(up_map_change, Pos.CENTER);
        border.setLeft(left_map_change);
        BorderPane.setAlignment(left_map_change, Pos.CENTER);
        /*border.setRight(right_map_change);
        BorderPane.setAlignment(right_map_change, Pos.CENTER);
        border.setBottom(down_map_change);
        BorderPane.setAlignment(down_map_change, Pos.CENTER);*/

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
    }

}
