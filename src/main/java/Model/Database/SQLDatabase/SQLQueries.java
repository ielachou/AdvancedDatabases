package Model.Database.SQLDatabase;

public enum SQLQueries implements QueryInterface {
    selectPerso("SELECT ID,pseudo,sexe,dommages,agilite,intelligence, " +
            "chance,force,vitality,energy,y,x  FROM persos WHERE pseudo = ? " ),

    selectItem("SELECT ID,name,ownerName,description,vitality,strength,chance, " +
            "intelligence,agility,damages,equiped  FROM items WHERE ownerName = ?"),
    selectAnItem("SELECT ID,name,ownerName,description,vitality,strength,chance, " +
            "intelligence,agility,damages,equiped  FROM items WHERE ownerName = ? and name = ?"),

    insertPerso("INSERT INTO persos (pseudo,sexe,dommages,agilite,intelligence," +
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
                 "intelligence = ?,agility = ?,damages = ?,equiped = ? WHERE ID = ?");


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
