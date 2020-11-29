package View.GameView;

import View.CharView.SelectCharPage;
import View.DataView;
import View.UtilsNodes.UtilsButtons;
import View.UtilsNodes.UtilsLayout;
import View.UtilsNodes.UtilsTextManager;
import View.UtilsNodes.UtilsView;
import View.ViewInterface;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
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

    public TextArea getTxtArea(){
        return txtArea;
    }


    public InventoryView(){
        this.border = new BorderPane();
        this.root = new AnchorPane();
        this.tvTableView = new TableView<>();
        makeButtons();
        setTableview();
        makeTitle();
        makeTextArea();
        makeLayouts();
        setButtonActions();


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
        String s = "Click on an item to check its stats";
        txtArea = createTextArea(s, false, 300, 200, 500, 500);
    }

    private void setTableview() {
        TableColumn<ItemInfo, String> name = new TableColumn<>("Name");
        name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        TableColumn<ItemInfo, String> equiped = new TableColumn<>("Equiped");
        equiped.setCellValueFactory(new PropertyValueFactory<>("Equiped"));
        this.tvTableView.getColumns().addAll(name, equiped);
        tvTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    private void makeButtons() {
        back = createButton("Retour", null, 100, 20, 15, "white", "red",
                null, null);
    }

    public void addItems(ItemInfo... items){
        tvTableView.getItems().addAll(items);
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
        tvTableView.setRowFactory(tv -> createRow());
    }

    private TableRow<ItemInfo> createRow() {

        TableRow<ItemInfo> row = new TableRow<>();
        row.setOnMouseClicked(e -> {
            if (e.getClickCount() == 1 && e.getButton() == MouseButton.PRIMARY && (!row.isEmpty())){
                listener.updateText(row.getItem());
            }
        });
        return row;
    }

    public interface Listener{
        void back();
        void updateText(ItemInfo item);
    }

    public interface ItemInfo{
        String getName();
        boolean isEquiped();
        String itemString();
    }
}
