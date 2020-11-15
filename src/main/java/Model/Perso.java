package Model;


//@Entity
public class Perso {


    //@Id
    long id;

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
    String name;
    //ToMany<Item> inventory;

    /*public ToMany<Item> getInventory() {
        return inventory;
    }

    public void setInventory(ToMany<Item> inventory) {
        this.inventory = inventory;
    }*/

    public Perso(String name, int sexe) {
        this.sexe = sexe;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPos() {
        return "(" + x + ", " + y + ")";
    }
}