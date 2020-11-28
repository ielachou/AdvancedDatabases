package Model.Database.ObjectBox;

import Model.*;
import Model.Database.Database;
import Model.Database.DatabaseInfo;
import io.objectbox.Box;
import io.objectbox.BoxStore;

import java.util.ArrayList;

public class ObjectBoxDatabase extends Database {

    private static ObjectBoxDatabase instance = null;
    private final BoxStore store;

    public static ObjectBoxDatabase getInstance() {
        if(instance == null){
            instance = new ObjectBoxDatabase(DatabaseInfo.PATH_OBJDB);
        }
        return instance;
    }

    private ObjectBoxDatabase(String db){
        this.store = MyObjectBox.builder().name(db).build();

    }

    @Override
    public Perso getPerso(String pseudo) {
        Box<Perso> box = store.boxFor(Perso.class);
        Perso perso = box.query().equal(Perso_.pseudo, pseudo).build().findFirst();
        return perso;
    }

    @Override
    public ArrayList<Item> getInventory(Perso perso) {
        Box<Item> box = store.boxFor(Item.class);
        ArrayList<Item> res = new ArrayList( box.query().equal(Item_.ownerName, perso.getPseudo()).build().find()) ;
        return res;
    }

    @Override
    public void requestComplicated(int i) {

    }

    @Override
    public void addItem(Item item) {
        Box<Item> box = store.boxFor(Item.class);
        box.put(item);

    }

    @Override
    public void addPerso(Perso perso) {
        Box<Perso> box = store.boxFor(Perso.class);
        box.put(perso);
    }

    @Override
    public void updatePerso(Perso perso) {

    }

    @Override
    public void updateItem(Item item) {

    }

    @Override
    public void removePerso(Perso perso) {
        Box<Perso> box = store.boxFor(Perso.class);
        this.removeInventory(perso);
        box.remove(perso);
    }

    @Override
    public void removeItem(Item item) {
        Box<Item> box = store.boxFor(Item.class);
        box.remove(item);
    }

    @Override
    public void removePerso(String pseudo) {
        this.removePerso(getPerso(pseudo));
    }

    @Override
    public void removeInventory(Perso perso) {
        removeInventory(perso.getPseudo());
    }

    @Override
    public void removeInventory(String pseudo) {
        Box<Item> box = store.boxFor(Item.class);
        box.remove( box.query().equal(Item_.ownerName, pseudo).build().find() );
    }
}
