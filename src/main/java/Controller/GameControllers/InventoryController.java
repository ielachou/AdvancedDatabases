package Controller.GameControllers;

import Controller.AbstractController;
import Controller.SwitchController;
import Model.Item;
import Model.Perso;
import View.GameView.GameView;
import View.GameView.InventoryView;
import View.ViewInterface;
import javafx.stage.Stage;

import java.util.ArrayList;

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
        close();
    }

    @Override
    public void updateText(InventoryView.ItemInfo item) {
        page.getTxtArea().setText(item.itemString());
    }

    @Override
    public void begin(){
        ArrayList<Item> inventory = getDb().getInventory(getPersoInfo());
        Item[] itemTab = new Item[0];
        if (inventory != null){
            itemTab = new Item[inventory.size()];
            for(int i = 0; i < inventory.size(); i++){
                itemTab[i] = inventory.get(i);
            }
        }
        this.page.addItems(itemTab);
        page.addItems();
    }
}
