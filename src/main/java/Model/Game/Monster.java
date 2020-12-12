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
