package Model.Database.SQLDatabase;

import Model.Database.Database;
import Model.Database.DatabaseInfo;
import Model.Game.Interactions.Fight;
import Model.Game.Interactions.SQLFight;
import Model.Game.Item;
import Model.Game.Monster;
import Model.Game.Perso;

import java.sql.*;
import java.util.ArrayList;

public class SQLDatabase extends Database {

    private static SQLDatabase instance = null;
    private String url;


    public static SQLDatabase getInstance() {
        return getInstance(DatabaseInfo.PATH_SQLDB);
    }

    public static SQLDatabase getInstance(String pathSqldbtest) {
        if(instance == null){
            instance = new SQLDatabase(pathSqldbtest);
        }
        return instance;
    }

    private SQLDatabase(String pathSqldb) {
        this.url = "jdbc:sqlite:" + pathSqldb;
        try {
            createNewDatabase();
            createTable();
        } catch (SQLException|ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    private void createTable() throws SQLException {
        this.executeSql(SQLTableQuery.tableItem);
        this.executeSql(SQLTableQuery.tablePerso);
        this.executeSql(SQLTableQuery.tableMonster);
        this.executeSql(SQLTableQuery.tableFightPerso);
        this.executeSql(SQLTableQuery.tableFightMonster);
    }

    private void createNewDatabase() throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(url);
        if (conn != null) {
            conn.getMetaData();
            conn.close();
        }
    }

    /**
     * Retourne la connection dans la base de données
     *
     * @return la connection dans la base de données
     * @throws SQLException Exception liée à une requete SQL
     */
    public Connection connect() throws SQLException {
        Connection conn = null;
        conn = DriverManager.getConnection(url);
        return conn;
    }

    /**
     * Cette méthode exécute des requête SQL
     *
     * @param sql  Requete Sql
     * @param data un ensemble de données
     * @throws SQLException Erreur SQL si la requete est incorrecte
     */
    public void executeSql(QueryInterface sql, Object... data) throws SQLException {
        Connection conn = this.connect();
        PreparedStatement pstmt = getPreparedStatement(sql, conn, data);
        pstmt.executeUpdate();
        conn.close();
    }

    /**
     * Recoit une requete à utiliser
     *
     * @param sql  Interface des requetes
     * @param conn Connexion utilisateur
     * @param data données
     * @return Requete SQL préparée
     * @throws SQLException Erreur de requete sql
     */
    public PreparedStatement getPreparedStatement(QueryInterface sql, Connection conn, Object[] data) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement(sql.getQuery());
        setParams(pstmt, data);
        return pstmt;
    }

    /**
     * Cette méthode permet d'executer une requête sql ayant un retour
     *
     * @param sql  Requete Sql
     * @param conn Connection de la requête
     * @param data un ensemble de données
     * @return ResultSet ensemble de résultats
     * @throws SQLException Exception liée à une requete SQL
     */
    public ResultSet executeSelectQuery(QueryInterface sql, Connection conn, Object... data) throws SQLException {
        PreparedStatement pstmt = getPreparedStatement(sql, conn, data);
        return pstmt.executeQuery();
    }

    /**
     * Place les paramètres
     *
     * @param pstmt requete préparée
     * @param data  données
     * @throws SQLException exception liée à une requete SQL
     */
    private void setParams(PreparedStatement pstmt, Object[] data) throws SQLException {
        for (int i = 0; i < data.length; ++i) {
            pstmt.setObject(i + 1, data[i]);
        }
    }



    @Override
    public Perso getPerso(String pseudo) {
        Perso res = null;
        Connection connection = null;
        try {
            connection = this.connect();

            ResultSet rs = this.executeSelectQuery(SQLQueries.selectPerso, connection, pseudo);
            if (rs.next()) {
                res = new Perso(rs.getLong("ID"), rs.getString("pseudo"), rs.getInt("x"),
                        rs.getInt("y"), rs.getInt("energy"),
                        rs.getInt("vitality"), rs.getInt("force"),
                        rs.getInt("chance"), rs.getInt("intelligence"),
                        rs.getInt("agilite"), rs.getInt("dommages"),
                        rs.getInt("sexe")
                       );
            }
            rs.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return res;
    }

    @Override
    public Perso getPerso(long id) {
        Perso res = null;
        Connection connection = null;
        try {
            connection = this.connect();

            ResultSet rs = this.executeSelectQuery(SQLQueries.selectPersoByID, connection, id);
            if (rs.next()) {
                res = new Perso(rs.getLong("ID"), rs.getString("pseudo"), rs.getInt("x"),
                        rs.getInt("y"), rs.getInt("energy"),
                        rs.getInt("vitality"), rs.getInt("force"),
                        rs.getInt("chance"), rs.getInt("intelligence"),
                        rs.getInt("agilite"), rs.getInt("dommages"),
                        rs.getInt("sexe")
                );
            }
            rs.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return res;
    }

    @Override
    public ArrayList<Item> getInventory(Perso perso) {
        ArrayList<Item> res = new ArrayList<>();
        Connection connection = null;
        try {
            connection = this.connect();
            Item tmp;
            ResultSet rs = this.executeSelectQuery(SQLQueries.selectItem, connection, perso.getPseudo());
            while (rs.next()) {

                tmp = new Item(rs.getLong("ID") , rs.getString ("description"), rs.getString ("ownerName")
                        , rs.getString("name"), rs.getInt("vitality"), rs.getInt("strength"), rs.getInt("chance"),
                rs.getInt("intelligence"), rs.getInt("agility"), rs.getInt("damages"), rs.getBoolean("equiped") );
                res.add(tmp);
            }
            rs.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return res;
    }

    @Override
    public void requestComplicated(int i) {

    }

    @Override
    public void addItem(Item item) {
        try {
            this.executeSql(SQLQueries.insertItem,item.getName(),
                    item.getOwnerName(),item.getDescription(),item.getVitality(),
                    item.getStrength(),item.getChance(),item.getIntelligence(),
                    item.getAgility(),item.getDamages(),item.isEquiped());

            item.setId(getItemId(item));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private long getItemId(Item item) {
        Item temp = getItem(item.getName(),item.getOwnerName());
        return temp != null ? temp.getId() : -1;
    }

    private long getPersoId(Perso perso) {
        return getPersoId(perso.getPseudo());
    }

    private long getPersoId(String pseudo) {
        Perso temp = getPerso(pseudo);
        return  temp != null ? temp.getID() : -1;
    }
    private Item getItem(String name, String ownerName) {
        Item res = null;
        Connection connection = null;
        try {
            connection = this.connect();
            ResultSet rs = this.executeSelectQuery(SQLQueries.selectAnItem, connection, ownerName,name);
            if (rs.next()) {

                res = new Item(rs.getLong("ID") , rs.getString ("description"), rs.getString ("ownerName")
                        , rs.getString("name"), rs.getInt("vitality"), rs.getInt("strength"), rs.getInt("chance"),
                        rs.getInt("intelligence"), rs.getInt("agility"), rs.getInt("damages"), rs.getBoolean("equiped") );

            }
            rs.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return res;
    }

    @Override
    public void addPerso(Perso perso) {
        try {
            this.executeSql(SQLQueries.insertPerso,perso.getPseudo(),
                    perso.getSexe(),perso.getDommages(),perso.getAgilite(),
                    perso.getIntelligence(),perso.getChance(),perso.getForce(),
                    perso.getVitality(),perso.getEnergy(),perso.getY(),perso.getX());
            perso.setId(getPersoId(perso));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }



    @Override
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

    @Override
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

    @Override
    public void removePerso(Perso perso) {
        removePerso(perso.getPseudo());
    }

    @Override
    public void removePerso(String pseudo) {
        removeInventory(pseudo);
        try {
            this.executeSql(SQLQueries.removePerso,pseudo);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void removePerso(long id) {

    }

    @Override
    public void removeInventory(Perso perso) {
        removeInventory(perso.getPseudo());
    }

    @Override
    public void removeInventory(String pseudo) {
        try {
            this.executeSql(SQLQueries.removeInventory,pseudo);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void removeItem(Item item) {
        try {
            this.executeSql(SQLQueries.removeItem,item.getOwnerName(),item.getName());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public ArrayList<Perso> getPersos(int number) {
        ArrayList<Perso> res = new ArrayList<>();
        Connection connection = null;
        try {
            connection = this.connect();
            Perso tmp;
            ResultSet rs = this.executeSelectQuery(SQLQueries.selectXPersos, connection, number);
            while (rs.next()) {

                tmp = new Perso(rs.getLong("ID"), rs.getString("pseudo"), rs.getInt("x"),
                        rs.getInt("y"), rs.getInt("energy"),
                        rs.getInt("vitality"), rs.getInt("force"),
                        rs.getInt("chance"), rs.getInt("intelligence"),
                        rs.getInt("agilite"), rs.getInt("dommages"),
                        rs.getInt("sexe")
                );
                res.add(tmp);
            }
            rs.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return res;
    }

    @Override
    public void addFight(Fight fight) {
        Monster monster = fight.getMonster();
        long monsterId = getMonsterId(monster);
        if(monsterId == -1){
            addMonster(monster);
            monsterId = monster.getID();
        }

        addRelationFightMonster(monsterId);

        fight.setID(getFightID(monsterId));
        for(Perso perso : fight.getPersos()){
            long persoId = getPersoId(perso);
            if(persoId == -1){
                addPerso(perso);
                persoId = perso.getID();
            }
            addRelationFightPersos(fight.getID(),persoId );
        }
    }

    private long getFightID(long id) {
        long res = -1;
        Connection connection = null;
        try {
            connection = this.connect();
            ResultSet rs = this.executeSelectQuery(SQLQueries.getFightID, connection, id);
            if (rs.next()) {
                res = rs.getLong("FIGHT_ID");
            }
            rs.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return res;
    }

    private void addRelationFightPersos(long id, long idPerso) {
        try {
            this.executeSql(SQLQueries.insertPersoRelation,id,idPerso);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    private void addRelationFightMonster( long idMonster) {
        try {
            this.executeSql(SQLQueries.insertMonsterRelation,idMonster);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void addMonster(Monster monster) {
        try {
            this.executeSql(SQLQueries.insertMonster,monster.getName(),
                    monster.getRank(),monster.getDommages(),monster.getAgilite(),
                    monster.getIntelligence(),monster.getChance(),monster.getForce(),
                    monster.getVitality(),monster.getEnergy(),monster.getY(),monster.getX());
            monster.setId(getMonsterId(monster));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private long getMonsterId(Monster monster) {
        Monster temp = getMonster(monster.getRank(),monster.getName());
        return  temp != null ? temp.getID() : -1;
    }

    private Monster getMonster(int rank, String name) {
        Monster res = null;
        Connection connection = null;
        try {
            connection = this.connect();

            ResultSet rs = this.executeSelectQuery(SQLQueries.selectMonster, connection, rank,name);
            if (rs.next()) {
                res = new Monster(rs.getLong("ID"), rs.getString("name"), rs.getInt("x"),
                        rs.getInt("y"), rs.getInt("energy"),
                        rs.getInt("vitality"), rs.getInt("force"),
                        rs.getInt("chance"), rs.getInt("intelligence"),
                        rs.getInt("agilite"), rs.getInt("dommages"),
                        rs.getInt("rank")
                );
            }
            rs.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return res;
    }

    @Override
    public ArrayList<Fight> getFights(String perso) {
        ArrayList<Fight> res = new ArrayList<>();
        Connection connection = null;
        long old_id = -1;
        try {
            Fight temp = null;
            connection = this.connect();
            ResultSet rs = this.executeSelectQuery(SQLQueries.selectFight, connection, getPersoId(perso));
            while (rs.next()) {
                long new_id = rs.getLong("FIGHT_ID");
                if(old_id != new_id){
                    if(temp != null){
                        res.add(temp);
                    }
                    temp = new SQLFight(new_id);
                    temp.setMonster( new Monster(rs.getLong("MID"), rs.getString("name"), rs.getInt("Mx"),
                            rs.getInt("My"), rs.getInt("Menergy"),
                            rs.getInt("Mvitality"), rs.getInt("Mforce"),
                            rs.getInt("Mchance"), rs.getInt("Mintelligence"),
                            rs.getInt("Magilite"), rs.getInt("Mdommages"),
                            rs.getInt("rank")));
                    old_id = new_id;
                }
                temp.addPersos( new Perso(rs.getLong("PID"), rs.getString("pseudo"), rs.getInt("Px"),
                        rs.getInt("Py"), rs.getInt("Penergy"),
                        rs.getInt("Pvitality"), rs.getInt("Pforce"),
                        rs.getInt("Pchance"), rs.getInt("Pintelligence"),
                        rs.getInt("Pagilite"), rs.getInt("Pdommages"),
                        rs.getInt("sexe")
                ));
            }
            if(temp != null){
                res.add(temp);
            }
            rs.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return res;
    }

    @Override
    public void removeAllFights() {
        try {
            this.executeSql(SQLTableQuery.dropFightPerso);
            this.executeSql(SQLTableQuery.dropFightMonster);
            this.executeSql(SQLTableQuery.tableFightPerso);
            this.executeSql(SQLTableQuery.tableFightMonster);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void removeAllPersos() {


        try {
            this.executeSql(SQLTableQuery.dropPersos);
            this.executeSql(SQLTableQuery.tablePerso);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        removeAllItems();
    }

    @Override
    public void removeAllItems() {

        try {
            this.executeSql(SQLTableQuery.dropItem);
            this.executeSql(SQLTableQuery.tableItem);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public int countPersos() {
        Connection connection = null;
        int res = 0;
        try{
            connection = this.connect();
            ResultSet rs = this.executeSelectQuery(SQLQueries.countPersos, connection);
            if(rs.next()) {
                res = rs.getInt(1);
            }
            rs.close();
            connection.close();
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return res;
    }

    @Override
    public void removeRandomPerso(int number) {
        try {
            this.executeSql(SQLQueries.removeRandomPersos,number);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
