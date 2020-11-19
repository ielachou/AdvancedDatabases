package Controller.GameControllers;

import Controller.AbstractController;
import Controller.SwitchController;
import View.GameView.GameView;
import View.GameView.InventoryView;
import View.ViewInterface;
import javafx.stage.Stage;

public class InventoryController extends AbstractController implements InventoryView.Listener, SwitchController {

    private final InventoryView page;

    public InventoryController(InventoryView page, Stage primaryStage) {
        super(primaryStage);
        this.page = page;
        this.page.setListener(this);
    }

    @Override
    public InventoryView getView() {
        return page;
    }

    @Override
    public void back() {

    }
}
