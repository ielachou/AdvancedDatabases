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
        for (Perso perso : PersoList) {
            s += perso.getPseudo() + ", ";
        }

        s += "MobsList=" + MobsList.getTarget().getName() +
                '}';

        return s;
    }

    public Fight() {

    }

    public Fight(Monster boss, Perso... persos) {
        MobsList.setTarget(boss);
        
        Collections.addAll(PersoList, persos);
    }

    /*
    Perso *persos
    GameEntity *mobs
    Boolean finality

     */
}
