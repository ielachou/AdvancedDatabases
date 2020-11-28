package Model;


import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class Perso {


    @Id long id;

    int x;
    int y;
    int energy;
    int vitality = 0;
    int force = 0;
    int chance = 0;
    int intelligence = 0;
    int agilite = 0;
    int dommages = 0;
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

    public int getVitality() {
        return vitality;
    }

    public void setVitality(int vitality) {
        this.vitality = vitality;
    }

    public int getForce() {
        return force;
    }

    public void setForce(int force) {
        this.force = force;
    }

    public int getChance() {
        return chance;
    }

    public void setChance(int chance) {
        this.chance = chance;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getAgilite() {
        return agilite;
    }

    public void setAgilite(int agilite) {
        this.agilite = agilite;
    }

    public int getDommages() {
        return dommages;
    }

    public void setDommages(int dommages) {
        this.dommages = dommages;
    }

    public int getSexe() {
        return sexe;
    }

    public void setSexe(int sexe) {
        this.sexe = sexe;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getEnergy() {
        return energy;
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

    public String getPos() {
        return "(" + x + ", " + y + ")";
    }
}