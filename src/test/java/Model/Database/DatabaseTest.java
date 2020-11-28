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
        Database db = ObjectBoxDatabase.getInstance();
        String pseudo = "Eren";
        Perso perso = new Perso(pseudo,1);
        db.addPerso(perso);
        Perso perso2 =  db.getPerso(pseudo);
        System.out.println(perso2.getPseudo());
        Item item = new Item("Marteau du bouftou","Eren");
        db.addItem(item);

        ArrayList<Item> inventory = db.getInventory(perso);
        System.out.println(inventory);

        item = new Item("Kjidsjijiiiii","Eren");
        db.addItem(item);
        inventory = db.getInventory(perso);
        System.out.println(inventory);

        db.removeItem(item); // Remove an item from an inventory
        //db.removeInventory(pseudo);
        inventory = db.getInventory(perso);
        System.out.println(inventory);
        db.removePerso(perso); // Must remove items of perso

        inventory = db.getInventory(perso);
        System.out.println(inventory);

        /*
        db.requestComplicated(2);
        Item item = new Item();
        db.addItem(item,perso);
        db.updatePerso(perso);
        db.updateItem(item);

         */

    }
}