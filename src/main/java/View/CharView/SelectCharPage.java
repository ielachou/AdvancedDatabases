package View.CharView;

import View.DataView;
import View.UtilsNodes.UtilsButtons;
import View.UtilsNodes.UtilsLayout;
import View.UtilsNodes.UtilsTextManager;
import View.UtilsNodes.UtilsView;
import View.ViewInterface;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SelectCharPage implements ViewInterface, DataView, UtilsView, UtilsButtons, UtilsLayout, UtilsTextManager {


    private final BorderPane border;
    private TableView<PersoInfo> tvTableView;
    private final AnchorPane root;
    private Button createNewPerso;
    private Button back;
    private Label titleLabel;
    private Listener listener;

    public SelectCharPage() {
        this.border = new BorderPane();
        this.root = new AnchorPane();
        this.tvTableView = new TableView<>();
        initPage();
        setBackGround(root, PATH_BG);
        setAction();
    }

    private void setAction() {
        this.createNewPerso.setOnAction(e -> listener.createNewPerso());
        //this.back.setOnAction(e -> listener.goBack());
        this.back.setOnAction(e -> listener.selectPerso());
        tvTableView.setRowFactory(tv -> createRow());
    }

    private TableRow<PersoInfo> createRow() {
        TableRow<PersoInfo> row = new TableRow<>();
        row.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2 && e.getButton() == MouseButton.PRIMARY && (!row.isEmpty())){
                listener.selectPerso();
            }
        });
        return row;
    }


    private void initPage() {
        setStyleOfContents();
        setTableview();
        root.getChildren().add(border);
        makeButtons();
        makeLabels();
        makeLayouts();
    }

    /**
     * Boutons interface
     */
    private void makeButtons() {
        createNewPerso = createButton("New Character", null,
                140, 20, 15, "black",
                "white", null, null);
        back = createButton("Back", null, 140, 30,
                15, "white", "black", null, null);
    }

    /**
     * Méthode permettant de modifier le style du contenu
     */
    private void setStyleOfContents() {
        tvTableView.setStyle(
                "-fx-padding: 5px;"
                        + "-fx-cell-size: 500px;"
        );
    }

    /**
     * Méthode permettant de créer un label
     */
    private void makeLabels() {
        titleLabel = createLabel("   Characters   ", 25, 20, "black", "white", "bold", 1);
    }


    /**
     * Méthode permettant de créer des Layouts de la page du projet
     */
    private void makeLayouts() {
        ScrollPane scrollPane = createScrollPane(ScrollPane.ScrollBarPolicy.AS_NEEDED, ScrollPane.ScrollBarPolicy.AS_NEEDED,
                true, true, tvTableView);

        HBox hbButtonsContainerBox = (HBox) createBox("H", Pos.CENTER, 20, true, createNewPerso, back);
        VBox mainVBox = (VBox) createBox("V", Pos.CENTER, 20, true, titleLabel, hbButtonsContainerBox, scrollPane);

        border.setCenter(mainVBox);
        centerAnchor(root, border, 10, 10, 10, 10);
    }

    /**
     * Méthode permettant de modifier la tableView et les informations associées à cette dernière
     */
    public void setTableview() {
        TableColumn<PersoInfo, String> name = new TableColumn<>("Pseudo");
        name.setCellValueFactory(new PropertyValueFactory<>("Pseudo"));
        TableColumn<PersoInfo, String> pos = new TableColumn<>("Position");
        pos.setCellValueFactory(new PropertyValueFactory<>("Pos"));
        TableColumn<PersoInfo, String> energy = new TableColumn<>("Energie");
        energy.setCellValueFactory(new PropertyValueFactory<>("Energy"));
        this.tvTableView.getColumns().addAll(name, pos, energy);
        tvTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    @Override
    public Parent getRoot() {
        return root;
    }

    public void setListener(Listener selectPageListener) {
        this.listener = selectPageListener;
    }


    public interface Listener {

        void createNewPerso();

        void goBack();

        void selectPerso();
    }

    public interface PersoInfo{
        String getPseudo();
        String getEnergy();
        String getPos();
    }
}
