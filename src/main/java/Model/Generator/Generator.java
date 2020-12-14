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

    public float generatePersos(int number){
        long start = 0;
        long end = 0;

        File name_file =
                new File("src/main/resources/names.txt");

        File monster_file =
                new File("src/main/resources/monster_names.txt");

        try {
            Scanner sc_name, sc_monsters;
            sc_name = new Scanner(name_file);
            sc_monsters = new Scanner(monster_file);
            List<String> monsters = getListFromMonster(sc_monsters);
            List<String> persos = new ArrayList<>();


            while(sc_name.hasNext()){
                persos.add(sc_name.nextLine());
            }
            String perso_name;
            Perso toAdd;
            int i = 0;

            start = System.currentTimeMillis();
            int max_loops = number / persos.size() +1;
            int j = 0;
            while (j < max_loops) {
                i= 0;
                while (i < persos.size()) {
                    perso_name = persos.get(i);
                    toAdd = new Perso(perso_name, randInt(0, 1));
                    //toAdd = generateItems(toAdd, monsters, db);
                    db.addPerso(toAdd);
                    i++;
                }
                j++;
            }
            end = System.currentTimeMillis();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return (end-start) /1000F;
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

    public float removePersos(int number){

        ArrayList<Perso> liste = db.getPersos(number);

        long start = System.currentTimeMillis();

        if(number > db.countPersos()){
            System.out.println("too many values");
        }else{
            for(int i = 0; i < liste.size(); i++){
                db.removePerso(liste.get(i));
            }
        }
        long end = System.currentTimeMillis();
        return (end - start) / 1000F;
    }

    public void getTimeOperations(int number){
        long start;
        long end;
        float sec;
        sec = generatePersos(number);
        System.out.println(sec + " seconds for insert");

        start = System.currentTimeMillis();
        updatePersos(number);
        end = System.currentTimeMillis();
        sec = (end - start) / 1000F; System.out.println(sec + " seconds for update");

        start = System.currentTimeMillis();
        selectPersos(number);
        end = System.currentTimeMillis();
        sec = (end - start) / 1000F; System.out.println(sec + " seconds for select");


        sec = removePersos(number);
        System.out.println(sec + " seconds for remove");
    }
}
