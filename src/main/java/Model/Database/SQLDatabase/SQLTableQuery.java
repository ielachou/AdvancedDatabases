package Model.Database.SQLDatabase;

public enum SQLTableQuery  implements  QueryInterface{

    tablePerso("CREATE TABLE IF NOT EXISTS persos(" +
            "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
            "pseudo text NOT NULL," +
            "sexe INTEGER NOT NULL, " +
            "dommages INTEGER NOT NULL, " +
            "agilite INTEGER NOT NULL, " +
            "intelligence INTEGER NOT NULL, " +
            "chance INTEGER NOT NULL, " +
            "force INTEGER NOT NULL, " +
            "vitality INTEGER NOT NULL, " +
            "energy INTEGER NOT NULL, " +
            "y INTEGER NOT NULL, " +
            "x INTEGER NOT NULL " +
            ")"),
    tableItem("CREATE TABLE IF NOT EXISTS items(" +
            "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
            "name text NOT NULL," +
            "ownerName text NOT NULL ," +
            "description text NOT NULL, " +
            "vitality INTEGER NOT NULL, " +
            "strength INTEGER NOT NULL, " +
            "chance INTEGER NOT NULL, " +
            "intelligence INTEGER NOT NULL, " +
            "agility INTEGER NOT NULL, " +
            "damages INTEGER NOT NULL, " +
            "equiped INTEGER NOT NULL " +
            ")"),
    tableMonster("CREATE TABLE IF NOT EXISTS monsters(" +
            "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
            "name text NOT NULL," +
            "rank INTEGER NOT NULL, " +
            "dommages INTEGER NOT NULL, " +
            "agilite INTEGER NOT NULL, " +
            "intelligence INTEGER NOT NULL, " +
            "chance INTEGER NOT NULL, " +
            "force INTEGER NOT NULL, " +
            "vitality INTEGER NOT NULL, " +
            "energy INTEGER NOT NULL, " +
            "y INTEGER NOT NULL, " +
            "x INTEGER NOT NULL " +
            ")"),
    tableFightPerso("CREATE TABLE IF NOT EXISTS fightPerso(" +
            "FIGHT_ID INTEGER NOT NULL, " +
            "PERSO_ID INTEGER NOT NULL ," +
            "PRIMARY KEY (FIGHT_ID, PERSO_ID)" +
            ")"),
    tableFightMonster("CREATE TABLE IF NOT EXISTS fightMonster(" +
                            "FIGHT_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            "MONSTER_ID INTEGER NOT NULL " +
                            ")"),
    dropFightMonster(" DROP TABLE fightMonster"),
    dropPersos("DROP TABLE persos"),
    dropItem("DROP TABLE items"),
    dropFightPerso("DROP TABLE fightPerso;");
    private String query = "";


    SQLTableQuery(String query) {
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
