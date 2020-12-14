package Model.Generator;

import Model.Database.Database;
import Model.Game.Item;
import Model.Game.Perso;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Generator {

    Database db;

    public Generator(Database db){
        this.db = db;
    }

    public void generatePersos(int number){


        File name_file =
                new File("src/main/resources/names.txt");

        File monster_file =
                new File("src/main/resources/monster_names.txt");

        db.removeAllItems();
        db.removeAllPersos();


        try {
            Scanner sc_name, sc_monsters;
            sc_name = new Scanner(name_file);
            sc_monsters = new Scanner(monster_file);
            List<String> monsters = getListFromMonster(sc_monsters);
            if(number >= 18238){
                System.out.println("too many values");
                return;
            }
            List<String> persos = new ArrayList<>();
            int i = 0;
            while(i < number){
                persos.add(sc_name.nextLine());
                i++;
            }
            i = 0;
            int j = 0;
            String perso_name;
            Perso toAdd;
            while (j < 10) {
                System.out.println(j);
                while (i < number) {
                    //System.out.println(sc_name.nextLine() + " " + randomNum);
                    perso_name = persos.get(i) + j;
                    toAdd = new Perso(perso_name, randInt(0, 1));
                    toAdd = generateItems(toAdd, monsters, db);
                    db.addPerso(toAdd);
                    i++;
                }
                i = 0;
                j++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private List<String> getListFromMonster(Scanner sc_monsters) {

        List<String> monster_list = new ArrayList<>();
        while(sc_monsters.hasNext()){
            monster_list.add(sc_monsters.nextLine());
        }

        return monster_list;
    }


    public Perso generateItems(Perso perso, List<String> monsters, Database db){

        String monsterName;
        String itemType;
        Item toAdd;
        String[] weapon_types = {"Hammer", "Rod", "Sword", "Shield", "Daggers", "Stick", "Axe", "Shovel", "Bow"};
        for(int i = 0; i < 5; i++){
            monsterName = monsters.get(randInt(0,10));
            itemType = weapon_types[randInt(0,8)];
            toAdd = randItem(perso.getName(), monsterName + "'s " + itemType, true);
            perso = updateStats(perso, toAdd);
            db.addItem(toAdd);
        }

        for (int i = 0; i < 10; i++){
            monsterName = monsters.get(randInt(0,10));
            itemType = weapon_types[randInt(0,8)];
            toAdd = randItem(perso.getName(), monsterName + "'s " + itemType, false);
            db.addItem(toAdd);
        }

        return perso;
    }

    private Perso updateStats(Perso perso, Item toAdd) {
        perso.setAgilite(perso.getAgilite() + toAdd.getAgility());
        perso.setChance(perso.getChance() + toAdd.getChance());
        perso.setVitality(perso.getVitality() + toAdd.getVitality());
        perso.setIntelligence(perso.getIntelligence() + toAdd.getIntelligence());
        perso.setForce(perso.getForce() + toAdd.getStrength());
        perso.setDommages(perso.getDommages() + toAdd.getDamages());

        return perso;
    }

    private int randInt(int min, int max){
        return ThreadLocalRandom.current().nextInt(min, max +1);
    }

    private Item randItem(String pseudo, String itemName, boolean equiped){

        return new Item("Lorem Ipsum", pseudo, itemName ,randInt(0,350),
                randInt(0,100), randInt(0,100), randInt(0,100), randInt(0,100), randInt(0,20), equiped);
    }

    public void updatePersos(int number){
        if(number > db.countPersos()){
            System.out.println("too many values");
        }else{
            for(int i = 1; i < number+1; i++){
                Perso toUpdate = new Perso(i,"newpseudo" + i, randInt(0,50), randInt(0,50),randInt(0,50),randInt(0,50),randInt(0,50),randInt(0,50),randInt(0,50),randInt(0,50),randInt(0,50),randInt(0,1));
                db.updatePerso(toUpdate);
            }
        }
    }

    public void selectPersos(int number){
        Perso selected;
        if(number > db.countPersos()){
            System.out.println("too many values");
        }else{
            for(int i = 1; i < number+1; i++){
                selected = db.getPerso(i);
            }
        }
    }

    public void removePersos(int number){
        if(number > db.countPersos()){
            System.out.println("too many values");
        }else{
            for(int i = 1; i < number+1; i++){
                db.removeRandomPerso(1);
            }
        }
        //return time
    }
}
