package Model.Database.ObjectBox;


import Model.Database.Database;
import Model.Database.DatabaseInfo;
import Model.Game.*;
import Model.Game.Interactions.Fight;
import Model.Game.Interactions.Fight_;
import io.objectbox.Box;
import io.objectbox.BoxStore;
import io.objectbox.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

public class ObjectBoxDatabase extends Database {

    private static ObjectBoxDatabase instance = null;
    private final BoxStore store;

    public static ObjectBoxDatabase getInstance() {
        return getInstance(DatabaseInfo.PATH_OBJDB);
    }

    public static ObjectBoxDatabase getInstance(String pathObjdbtest) {
        if(instance == null){
            instance = new ObjectBoxDatabase(pathObjdbtest);
        }
        return instance;
    }

    private ObjectBoxDatabase(String db){
        this.store = MyObjectBox.builder().name(db).build();
    }



    @Override
    public Perso getPerso(String pseudo) {
        Box<Perso> box = store.boxFor(Perso.class);
        Perso perso = box.query().equal(Perso_.name, pseudo).build().findFirst();
        return perso;
    }

    @Override
    public Perso getPerso(long id) {
        Box<Perso> box = store.boxFor(Perso.class);
        Perso perso = box.query().equal(Perso_.id, id).build().findFirst();
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
        Box<Perso> box = store.boxFor(Perso.class);
        box.put(perso);
    }

    @Override
    public void updateItem(Item item) {
        Box<Item> box = store.boxFor(Item.class);
        box.put(item);
    }

    @Override
    public void removePerso(String pseudo) {
        this.removePerso(getPerso(pseudo));
    }

    @Override
    public void removePerso(long id) {
        Box<Perso> box = store.boxFor(Perso.class);
        box.remove(id);
    }

    @Override
    public void removePerso(Perso perso) {
        Box<Perso> box = store.boxFor(Perso.class);
        this.removeInventory(perso);
        box.remove(perso);
    }

    public void removeAllPersos(){
        Box<Perso> box = store.boxFor(Perso.class);
        box.removeAll();
    }

    public void removeAllItems(){
        Box<Item> box = store.boxFor(Item.class);
        box.removeAll();
    }

    @Override
    public int countPersos() {
        return (int) store.boxFor(Perso.class).count();
    }

    @Override
    public void removeRandomPerso(int number) {
        Box<Perso> box = store.boxFor(Perso.class);
        for(int i = 0; i < number; i++){
            box.remove(box.query().build().findFirst());
        }
    }

    @Override
    public void removeItem(Item item) {
        Box<Item> box = store.boxFor(Item.class);
        box.remove(item);
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

    @Override
    public ArrayList<Perso> getPersos(int number) {
        Box<Perso> box = store.boxFor(Perso.class);
        if(number != -1) {
            return (ArrayList<Perso>) box.query().less(Perso_.__ID_PROPERTY, number).build().find();
        }else{
            return (ArrayList<Perso>) box.getAll();
        }
    }

    @Override
    public void addFight(Fight fight) {
        Box<Fight> box = store.boxFor(Fight.class);
        box.put(fight);
    }

    @Override
    public ArrayList<Fight> getFights(String perso) {
        Box<Fight> box = store.boxFor(Fight.class);

        QueryBuilder<Fight> qb = box.query();
        qb.link(Fight_.PersoList).equal(Perso_.name, perso);
        List<Fight> fightList = qb.build().find();
        return (ArrayList<Fight>) fightList;
    }

    @Override
    public void removeAllFights() {
        Box<Fight> box = store.boxFor(Fight.class);
        box.removeAll();
    }
}
