package Model.Game;

import View.GameView.InventoryView;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class Item implements InventoryView.ItemInfo {
    @Id private long id;

    private String description;
    private String ownerName;
    private String name;
    private int vitality;
    private int strength;
    private int chance;
    private int intelligence;
    private int agility;
    private int damages;
    private boolean equiped;


    public Item(long id, String description, String ownerName, String name, int vitality, int strength, int chance,
                int intelligence, int agility, int damages, boolean equiped) {
        this(description, ownerName, name, vitality, strength, chance, intelligence, agility, damages, equiped);
        this.id = id;
    }

    public Item(String name, String ownerName) {
        this.name = name;
        this.ownerName = ownerName;
        this.description = "description";
        this.vitality = 0;
        this.strength = 0;
        this.chance = 0;
        this.intelligence = 0;
        this.agility = 0;
        this.damages = 0;
        this.equiped = false;
    }

    public Item(String description, String ownerName, String name, int vitality, int strength, int chance,
                int intelligence, int agility, int damages, boolean equiped){
        this.description = description;
        this.ownerName = ownerName;
        this.name = name;
        this.vitality = vitality;
        this.strength = strength;
        this.chance = chance;
        this.intelligence = intelligence;
        this.agility = agility;
        this.damages = damages;
        this.equiped = equiped;
    }

    @Override
    public String toString() {
        return "Item{" +
                "ownerName='" + ownerName + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getName() {
        return name;
    }

    public String itemString(){
        return getName() + "\n \n" + getDescription() +
                "\nVitality : " + getVitality() +
                "\nStrength : " + getStrength() +
                "\nChance : " + getChance()+
                "\nAgility : " + getAgility() +
                "\nIntelligence : " + getIntelligence() +
                "\nDamages : " + getDamages() ;
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

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
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

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public int getDamages() {
        return damages;
    }

    public void setDamages(int damages) {
        this.damages = damages;
    }

    public boolean isEquiped() {
        return equiped;
    }

    public void setEquiped(boolean equiped) {
        this.equiped = equiped;
    }
}
