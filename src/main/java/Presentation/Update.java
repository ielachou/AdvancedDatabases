package Presentation;

/*

 updatePerso("UPDATE persos SET pseudo = ?,sexe = ?,dommages = ?,agilite = ?,intelligence = ?," +
         "chance = ? ,force = ?,vitality = ?,energy = ?,y = ?,x = ? WHERE ID = ?"),
         updateItem("UPDATE items SET ID = ?, name = ?,ownerName = ?,description = ?,vitality = ?,strength = ?," +
         "chance = ?," + "intelligence = ?,agility = ?,damages = ?,equiped = ? WHERE ID = ?");

 */



public void updatePerso(Perso perso) {
        try {
        this.executeSql(SQLQueries.updatePerso,perso.getPseudo(),
        perso.getSexe(),perso.getDommages(),perso.getAgilite(),
        perso.getIntelligence(),perso.getChance(),perso.getForce(),
        perso.getVitality(),perso.getEnergy(),perso.getY(),perso.getX(),perso.getID());

        } catch (SQLException throwables) {
        throwables.printStackTrace();
        }
        }

public void updateItem(Item item) {
        try {
        this.executeSql(SQLQueries.updateItem,item.getId(),item.getName(),
        item.getOwnerName(),item.getDescription(),item.getVitality(),
        item.getStrength(),item.getChance(),item.getIntelligence(),
        item.getAgility(),item.getDamages(),item.isEquiped(),item.getId());

        } catch (SQLException throwables) {
        throwables.printStackTrace();
        }
        }




        ////////////////////////


public void updatePerso(Perso perso) {
        Box<Perso> box = store.boxFor(Perso.class);
        box.put(perso);
        }

public void updateItem(Item item) {
        Box<Item> box = store.boxFor(Item.class);
        box.put(item);
        }
        ////////