package Model.Database;

import Model.Item;
import Model.Perso;

import java.util.ArrayList;

public abstract class Database {

    public abstract Perso getPerso(String pseudo) ;

    public abstract ArrayList<Item> getInventory(Perso perso) ;

    public abstract void requestComplicated(int i);

    public abstract void addItem(Item item) ;

    public abstract void addPerso(Perso perso) ;

    public abstract void updatePerso(Perso perso);

    public abstract void updateItem(Item item) ;

    public abstract void removePerso(Perso perso) ;

    public abstract void removeItem(Item item) ;

    public abstract void removePerso(String pseudo);

    public abstract void removeInventory(Perso perso);

    public abstract void removeInventory(String pseudo);

    public abstract ArrayList<Perso> getPersos(int number);
}
