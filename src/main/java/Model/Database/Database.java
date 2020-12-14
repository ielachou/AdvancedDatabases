package Model.Database;

import Model.Game.Interactions.Fight;
import Model.Game.Item;
import Model.Game.Perso;

import java.util.ArrayList;

public abstract class Database {

    public abstract Perso getPerso(String pseudo) ;

    public abstract Perso getPerso(long id);

    public abstract ArrayList<Item> getInventory(Perso perso) ;

    public abstract void requestComplicated(int i);

    public abstract void addItem(Item item) ;

    public abstract void addPerso(Perso perso) ;

    public abstract void updatePerso(Perso perso);

    public abstract void updateItem(Item item) ;

    public abstract void removePerso(Perso perso) ;

    public abstract void removePerso(String pseudo) ;

    public abstract void removePerso(long id) ;

    public abstract void removeItem(Item item) ;

    public abstract void removeInventory(Perso perso);

    public abstract void removeInventory(String pseudo);

    public abstract ArrayList<Perso> getPersos(int number);

    public abstract void addFight(Fight fight);

    public abstract ArrayList<Fight> getFights(String perso);

    public abstract void removeAllFights();

    public abstract void removeAllPersos();

    public abstract void removeAllItems();

    public abstract int countPersos();

    public abstract void removeRandomPerso(int number);

}
