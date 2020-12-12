package Model.Game.Interactions;


import Model.Game.Monster;
import Model.Game.Perso;
import io.objectbox.annotation.Backlink;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToMany;
import io.objectbox.relation.ToOne;

import java.util.Collection;
import java.util.Collections;

@Entity
public class Fight {


    @Id
    long id;
    ToMany<Perso> PersoList;
    ToOne<Monster> MobsList;
    boolean finality = false;

    @Override
    public String toString() {
        String s = "Fight{" +
                "PersoList=";
        for (Perso perso : getPersos()) {
            s += perso.getPseudo() + ", ";
        }

        s += "MobsList=" + getMonster().getName() +
                '}';

        return s;
    }

    public Fight() {

    }

    public Fight(long id) {
        this.id = id;
    }

    public Fight(Monster boss, Perso... persos) {
        setMonster(boss);

        addPersos(persos);
    }

    public void setMonster(Monster boss) {
        MobsList.setTarget(boss);
    }

    public void addPersos(Perso... persos) {
        Collections.addAll(PersoList, persos);
    }

    public Iterable<? extends Perso> getPersos() {
        return PersoList;
    }

    public Monster getMonster() {
        return MobsList.getTarget();
    }

    public long getID() {
        return id;
    }

    public void setID(long fightID) {
        this.id = fightID;
    }

    /*
    Perso *persos
    GameEntity *mobs
    Boolean finality

     */
}
