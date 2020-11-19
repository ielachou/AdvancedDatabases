package View.GameView;

import View.DataView;
import View.UtilsNodes.UtilsButtons;
import View.UtilsNodes.UtilsLayout;
import View.UtilsNodes.UtilsTextManager;
import View.UtilsNodes.UtilsView;
import View.ViewInterface;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class InventoryView implements UtilsLayout, UtilsButtons, UtilsTextManager, UtilsView, ViewInterface, DataView {

    BorderPane border;
    AnchorPane root;
    private Listener listener;
    private Button back;
    private TableView<ItemInfo> tvTableView;
    private Label listTitle;
    private TextArea txtArea;


    public InventoryView(){
        this.border = new BorderPane();
        this.root = new AnchorPane();
        this.tvTableView = new TableView<>();
        makeButtons();
        setTableview();
        makeTitle();
        makeTextArea();
        makeLayouts();


    }

    private void makeLayouts() {
        HBox hbox = (HBox) createBox("H", Pos.CENTER, 30, true, tvTableView, txtArea);
        VBox mainVBox = (VBox) createBox("V", Pos.CENTER, 30, true, listTitle, hbox, back);
        border.setCenter(mainVBox);
        root.getChildren().add(border);
        centerAnchor(root, border, 10, 10, 10, 10);
    }

    public void setListener(Listener inventoryListener) {
        this.listener = inventoryListener;
    }


    private void makeTextArea() {
        String s = "qdddqdqds";
        txtArea = createTextArea(s, false, 300, 200, 500, 500);
    }

    private void setTableview() {
        TableColumn<ItemInfo, String> name = new TableColumn<>("Item");
        name.setCellValueFactory(new PropertyValueFactory<>("Item"));
        TableColumn<ItemInfo, String> equiped = new TableColumn<>("Equiped");
        equiped.setCellValueFactory(new PropertyValueFactory<>("Equiped"));
        this.tvTableView.getColumns().addAll(name, equiped);
        tvTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    private void makeButtons() {
        back = createButton("Retour", null, 100, 20, 15, "white", "red",
                null, null);
    }



    /**
     * Fonction permettant de créer les labels de la page
     */
    private void makeTitle() {
        listTitle = createLabel("   Inventory   ", 30, 0, "black",
                "white", "bold", 1);
    }

    @Override
    public AnchorPane getRoot() {
        return root;
    }

    private void setButtonActions() {
        this.back.setOnAction(e -> listener.back());
    }

    public interface Listener{
        void back();
    }

    public interface ItemInfo{
        String getItem();
        String getEquiped();
    }
}
