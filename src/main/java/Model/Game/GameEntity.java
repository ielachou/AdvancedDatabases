package Model.Game;

import io.objectbox.annotation.BaseEntity;
import io.objectbox.annotation.Id;

@BaseEntity
public class GameEntity {
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
    String name;


    protected GameEntity(long id, String pseudo, int x, int y, int energy, int vitality, int force, int chance, int intelligence, int agilite, int dommages) {
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
        this.name = pseudo;
    }

    public GameEntity() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public long getID(){
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }
}
