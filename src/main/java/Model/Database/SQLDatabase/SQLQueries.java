package Model.Database.SQLDatabase;

public enum SQLQueries implements QueryInterface {
    selectPerso("SELECT ID,pseudo,sexe,dommages,agilite,intelligence, " +
            "chance,force,vitality,energy,y,x  FROM persos WHERE pseudo = ? " ),
    selectMonster("SELECT ID,name,rank,dommages,agilite,intelligence, " +
            "chance,force,vitality,energy,y,x  FROM monsters WHERE  rank = ? AND name = ?" ),

    selectItem("SELECT ID,name,ownerName,description,vitality,strength,chance, " +
            "intelligence,agility,damages,equiped  FROM items WHERE ownerName = ?"),
    selectAnItem("SELECT ID,name,ownerName,description,vitality,strength,chance, " +
            "intelligence,agility,damages,equiped  FROM items WHERE ownerName = ? and name = ?"),

    insertPerso("INSERT INTO persos (pseudo,sexe,dommages,agilite,intelligence," +
            "chance,force,vitality,energy,y,x)" +
            "VALUES (?,?,?,?,?,?,?,?,?,?,?)"),
    insertMonster("INSERT INTO monsters (name,rank,dommages,agilite,intelligence," +
            "chance,force,vitality,energy,y,x)" +
            "VALUES (?,?,?,?,?,?,?,?,?,?,?)"),

    insertItem("INSERT INTO items (name,ownerName,description,vitality,strength,chance," +
            "intelligence,agility,damages,equiped)" +
            "VALUES (?,?,?,?,?,?,?,?,?,?)"),

    removePerso("DELETE FROM persos WHERE pseudo = ?"),
    removeInventory("DELETE FROM items WHERE ownerName = ?"),
    removeItem("DELETE FROM items WHERE ownerName = ? and name = ?"),
    selectXPersos("SELECT ID,pseudo,sexe,dommages,agilite,intelligence, " +
                          "chance,force,vitality,energy,y,x  FROM persos WHERE ID < ? "),
    updatePerso("UPDATE persos SET pseudo = ?,sexe = ?,dommages = ?,agilite = ?,intelligence = ?," +
                            "chance = ? ,force = ?,vitality = ?,energy = ?,y = ?,x = ? WHERE ID = ?"),
    updateItem("UPDATE items SET ID = ?, name = ?,ownerName = ?,description = ?,vitality = ?,strength = ?,chance = ?," +
                 "intelligence = ?,agility = ?,damages = ?,equiped = ? WHERE ID = ?"),
    insertPersoRelation("INSERT INTO fightPerso (FIGHT_ID,PERSO_ID) VALUES (?,?)"),
    insertMonsterRelation("INSERT INTO fightMonster (MONSTER_ID) VALUES (?)"),


    selectFight("SELECT  fp.* , " +
            "persos.ID as PID,persos.pseudo as pseudo,persos.sexe as sexe" +
            ",persos.dommages as Pdommages,persos.agilite as Pagilite," +
            "persos.intelligence as Pintelligence,persos.chance as Pchance," +
            "persos.force as Pforce,persos.vitality as Pvitality" +
            ",persos.energy as Penergy,persos.y as Py,persos.x as Px"+
            ",monsters.ID as MID,monsters.name as name,monsters.rank as rank" +
            ",monsters.dommages as Mdommages,monsters.agilite as Magilite," +
            "monsters.intelligence as Mintelligence,monsters.chance as Mchance," +
            "monsters.force as Mforce,monsters.vitality as Mvitality" +
            ",monsters.energy as Menergy,monsters.y as My,monsters.x as Mx  " +
                " FROM " +
            "fightPerso as fp " +
            "INNER JOIN fightMonster as fightMonster ON fightMonster.FIGHT_ID = fp.FIGHT_ID "+
            "INNER JOIN persos as persos ON persos.ID = fp.PERSO_ID " +
            "INNER JOIN monsters as monsters ON fp.FIGHT_ID = monsters.ID " +
            "WHERE EXISTS( " +
                    "SELECT * " +
                        "FROM fightPerso as fp2 " +
                            "WHERE fp2.PERSO_ID = ? AND fp.FIGHT_ID == fp2.FIGHT_ID " +
                    ") " +
            "ORDER BY fp.FIGHT_ID"),


    getFightID("SELECT FIGHT_ID FROM fightMonster WHERE MONSTER_ID = ?");


    private String query = "";


    SQLQueries(String query) {
        this.query = query;
    }

    /**
     * getter
     *
     * @return la query
     */
    @Override
    public String getQuery() {
        return query;
    }

    /**
     * Methode pour afficher
     *
     * @return la query
     */
    public String toString() {
        return query;
    }
}
