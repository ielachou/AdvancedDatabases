package Model.Database;

import Model.Database.ObjectBox.ObjectBoxDatabase;
import Model.Database.SQLDatabase.SQLDatabase;
import Model.Game.Interactions.Fight;
import Model.Game.Item;
import Model.Game.Monster;
import Model.Game.Perso;
import io.objectbox.Box;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class DatabaseTest extends TestCase {

    @Test
    public void TestAll(){
        System.out.println("hdfsuqdhushdushu");
        Database db = SQLDatabase.getInstance(DatabaseInfo.PATH_SQLDBTEST);
        //Database db = ObjectBoxDatabase.getInstance(DatabaseInfo.PATH_OBJDBTEST);
        String pseudo = "Eren";
        Perso perso = new Perso(pseudo,1);
        db.addPerso(perso);
        Monster soufiane = new Monster("Soufiane", 5);
        Fight fight = new Fight(soufiane, perso, new Perso("Mahmoud", 0), new Perso("nidhal", 1));

        db.addFight(fight);


        List<Fight> fights = db.getFights("Eren");
        System.out.println(fights.get(0));
        db.removeAllFights();

        Perso perso2 =  db.getPerso(pseudo);
        System.out.println(perso2.getPseudo());
        Item item = new Item("Marteau du bouftou","Eren");
        db.addItem(item);

        ArrayList<Item> inventory = db.getInventory(perso);
        System.out.println(inventory);

        item.setName("loooul");
        db.updateItem(item);

        inventory = db.getInventory(perso);
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