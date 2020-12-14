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

    /**
     * Will insert number times a new entry in the db
     * @param number number of inserts
     * @return time
     */
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

    /**
     * Return a list with all monsters in the file
     *
     * @param sc_monsters scanner of monster files
     * @return List with names of monsters
     */
    private List<String> getListFromMonster(Scanner sc_monsters) {

        List<String> monster_list = new ArrayList<>();
        while(sc_monsters.hasNext()){
            monster_list.add(sc_monsters.nextLine());
        }

        return monster_list;
    }


    /**
     * Generate a lot of items for a designated perso
     *
     * @param perso Perso who will be added by items
     * @param monsters List of monster names
     * @return Perso with new items and stats
     */
    public Perso generateItems(Perso perso, List<String> monsters){

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

    /**
     * Update a perso with stats of an item
     *
     * @param perso perso to update
     * @param toAdd item to add at the perso
     * @return updated perso
     */
    private Perso updateStats(Perso perso, Item toAdd) {
        perso.setAgilite(perso.getAgilite() + toAdd.getAgility());
        perso.setChance(perso.getChance() + toAdd.getChance());
        perso.setVitality(perso.getVitality() + toAdd.getVitality());
        perso.setIntelligence(perso.getIntelligence() + toAdd.getIntelligence());
        perso.setForce(perso.getForce() + toAdd.getStrength());
        perso.setDommages(perso.getDommages() + toAdd.getDamages());

        return perso;
    }

    /**
     * Generate a random integer
     *
     * @param min minimum
     * @param max maximum
     * @return random int
     */
    private int randInt(int min, int max){
        return ThreadLocalRandom.current().nextInt(min, max +1);
    }

    /**
     * Generate a new Item with random stats
     *
     * @param pseudo pseudo of the perso
     * @param itemName item name
     * @param equiped equiped item or not
     * @return new Item with random stats
     */
    private Item randItem(String pseudo, String itemName, boolean equiped){

        return new Item("Lorem Ipsum", pseudo, itemName ,randInt(0,350),
                randInt(0,100), randInt(0,100), randInt(0,100), randInt(0,100), randInt(0,20), equiped);
    }

    /**
     * Update a given number of persos into the database
     *
     * @param number number of updates
     */
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

    /**
     * Selects a given number of persos into the database
     *
     * @param number number of selects
     */
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


    /**
     * Removes a given number of persos out of the database
     *
     * @param number number of removes
     * @return time
     */
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

    /**
     * Prints all execution time of operators
     *
     * @param number number of operations
     */
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
