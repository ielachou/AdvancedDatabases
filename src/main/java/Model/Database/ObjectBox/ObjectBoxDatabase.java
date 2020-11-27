package Model.Database.ObjectBox;

import Model.Database.Database;
import Model.Database.DatabaseInfo;
import Model.Item;
import Model.Perso;

import java.util.ArrayList;

public class ObjectBoxDatabase extends Database {

    private static ObjectBoxDatabase instance = null;

    public static ObjectBoxDatabase getInstance() {
        if(instance == null){
            instance = new ObjectBoxDatabase(DatabaseInfo.PATH_OBJDB);
        }
        return instance;
    }

    private ObjectBoxDatabase(String db){
        //this.store = MyObjectBox.builder().name(db).build();

    }

    @Override
    public Perso getPerso(String pseudo) {
        return null;
    }

    @Override
    public ArrayList<Item> getInventory(Perso perso) {
        return null;
    }

    @Override
    public void requestComplicated(int i) {

    }

    @Override
    public void addItem(Item item) {

    }

    @Override
    public void addPerso(Perso perso) {

    }

    @Override
    public void updatePerso(Perso perso) {

    }

    @Override
    public void updateItem(Item item) {

    }

    @Override
    public void removePerso(Perso perso) {

    }

    @Override
    public void removeItem(Item item) {

    }

    @Override
    public void removePerso(String pseudo) {

    }

    @Override
    public void removeInventory(Perso perso) {

    }

    @Override
    public void removeInventory(String pseudo) {

    }
}
