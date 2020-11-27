package Model.Database.SQLDatabase;

import Model.Database.Database;
import Model.Database.DatabaseInfo;
import Model.Item;
import Model.Perso;

import java.sql.*;
import java.util.ArrayList;

public class SQLDatabase extends Database {

    private static SQLDatabase instance = null;
    private String url;


    public static SQLDatabase getInstance() {
        if(instance == null){
            instance = new SQLDatabase(DatabaseInfo.PATH_SQLDB);
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

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void addPerso(Perso perso) {
        try {
            this.executeSql(SQLQueries.insertPerso,perso.getPseudo(),
                    perso.getSexe(),perso.getDommages(),perso.getAgilite(),
                    perso.getIntelligence(),perso.getChance(),perso.getForce(),
                    perso.getVitality(),perso.getEnergy(),perso.getY(),perso.getX());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void updatePerso(Perso perso) {

    }

    @Override
    public void updateItem(Item item) {

    }

    @Override
    public void removePerso(Perso perso) {
        removePerso(perso.getPseudo());
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
    public void removePerso(String pseudo) {
        removeInventory(pseudo);
        try {
            this.executeSql(SQLQueries.removePerso,pseudo);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
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
}