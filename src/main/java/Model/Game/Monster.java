package Model.Game;

import io.objectbox.annotation.Entity;

@Entity
public class Monster extends GameEntity {

    int rank;

    public Monster(String name, int rank) {
        this.name = name;
        this.rank = rank;
    }

    public Monster(){

    }


    public Monster(long id, String name, int x, int y, int energy, int vitality, int force, int chance, int intelligence, int agilite, int dommages, int rank) {

        super(id, name, x, y, energy, vitality, force, chance, intelligence, agilite, dommages);
        this.rank = rank;

    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    /*

    Define monster methods
     */
}
