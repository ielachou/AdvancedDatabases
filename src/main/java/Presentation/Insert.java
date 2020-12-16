package Presentation;

import Model.Database.SQLDatabase.SQLQueries;
import Model.Game.Perso;
import io.objectbox.Box;
import io.objectbox.BoxStore;


class DbT{

    public void addPerso(Perso perso) {
        this.executeSql(SQLQueries.insertPerso,perso.getPseudo(),
                perso.getSexe(),perso.getDommages(),perso.getAgilite(),
                perso.getIntelligence(),perso.getChance(),perso.getForce(),
                perso.getVitality(),perso.getEnergy(),perso.getY(),perso.getX());

    }

    private void executeSql(SQLQueries insertPerso,Object... data ){

    }

}




////////////////////

class OOdb{
    private BoxStore store;

    public void addPerso(Perso perso) {
        Box<Perso> box = store.boxFor(Perso.class);
        box.put(perso);
    }
}
