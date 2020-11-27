package Model.Database;

import Model.Database.ObjectBox.ObjectBoxDatabase;
import Model.Database.SQLDatabase.SQLDatabase;
import Model.Item;
import Model.Perso;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class DatabaseTest extends TestCase {

    @Test
    public void TestAll(){
        System.out.println("hdfsuqdhushdushu");
        Database db = SQLDatabase.getInstance();
        String pseudo = "Eren";
        Perso perso =  db.getPerso(pseudo);
        ArrayList<Item> inventory = db.getInventory(perso);
        db.requestComplicated(2);
        Item item = new Item();
        db.addItem(item,perso);
        db.addPerso(perso);
        db.updatePerso(perso);
        db.updateItem(item);
        db.removePerso(perso); // Must remove items of perso
        db.removePerso(pseudo);
        db.removeItem(item); // Remove an item from an inventory

    }
}