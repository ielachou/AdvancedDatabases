package Model.Game;


import View.CharView.SelectCharPage;
import io.objectbox.annotation.Entity;

@Entity
public class Perso extends GameEntity implements SelectCharPage.PersoInfo {


    int sexe; // 0M 1F
    String pseudo;
    //ToMany<Item> inventory;

    /*public ToMany<Item> getInventory() {
        return inventory;
    }

    public void setInventory(ToMany<Item> inventory) {
        this.inventory = inventory;
    }*/

    public Perso(long id, String pseudo, int x, int y, int energy, int vitality,
                 int force, int chance, int intelligence, int agilite,
                 int dommages, int sexe) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.energy = energy;
        this.vitality = vitality;
        this.force = force;
        this.chance = chance;
        this.intelligence = intelligence;
        this.agilite = agilite;
        this.dommages = dommages;
        this.sexe = sexe;
        this.pseudo = pseudo;
    }

    public Perso(String pseudo, int sexe) {
        this.sexe = sexe;
        this.pseudo = pseudo;
        this.energy = 10000;
        this.x = 0;
        this.y = 0;
    }

    public Perso(){

    }

    public int getSexe() {
        return sexe;
    }

    public void setSexe(int sexe) {
        this.sexe = sexe;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }



    @Override
    public String getPos() {
        return "(" + x + ", " + y + ")";
    }
}