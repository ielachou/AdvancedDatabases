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

public class SQLGenerator {

    public static void generatePersos(int number, Database db){


        File name_file =
                new File("src/main/resources/names.txt");

        File monster_file =
                new File("src/main/resources/monster_names.txt");


        try {
            Scanner sc_name, sc_monsters;
            sc_name = new Scanner(name_file);
            sc_monsters = new Scanner(monster_file);
            List<String> monsters = getListFromMonster(sc_monsters);
            if(number >= 18238){
                System.out.println("too many values");
                return;
            }
            int i = 0;
            String perso_name = "";
            Perso toAdd;
            while (i < number) {
                //System.out.println(sc_name.nextLine() + " " + randomNum);
                perso_name = sc_name.nextLine();
                toAdd = new Perso(perso_name, randInt(0,1));
                generateItems(perso_name, monsters, db);
                db.addPerso(toAdd);
                i++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    private static List<String> getListFromMonster(Scanner sc_monsters) {

        List<String> monster_list = new ArrayList<>();
        while(sc_monsters.hasNext()){
            monster_list.add(sc_monsters.nextLine());
        }

        return monster_list;
    }


    public static void generateItems(String pseudo, List<String> monsters, Database db){

        String monsterName;
        String itemType;
        Item toAdd;
        String[] weapon_types = {"Hammer", "Rod", "Sword", "Shield", "Daggers", "Stick", "Axe", "Shovel", "Bow"};
        for(int i = 0; i < 5; i++){
            monsterName = monsters.get(randInt(0,10));
            itemType = weapon_types[randInt(0,8)];
            toAdd = randItem(pseudo, monsterName + "'s " + itemType, true);
            db.addItem(toAdd);
        }

        for (int i = 0; i < 10; i++){
            monsterName = monsters.get(randInt(0,10));
            itemType = weapon_types[randInt(0,8)];
            toAdd = randItem(pseudo, monsterName + "'s " + itemType, false);
            db.addItem(toAdd);
        }

    }

    private static int randInt(int min, int max){
        return ThreadLocalRandom.current().nextInt(min, max +1);
    }

    private static Item randItem(String pseudo, String itemName, boolean equiped){

        return new Item("Lorem Ipsum", pseudo, itemName ,randInt(0,350),
                randInt(0,100), randInt(0,100), randInt(0,100), randInt(0,100), randInt(0,20), equiped);
    }

}
