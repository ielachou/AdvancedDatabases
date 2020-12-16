package Presentation;

import Model.Database.SQLDatabase.QueryInterface;
import Model.Game.MyObjectBox;
import io.objectbox.BoxStore;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

import java.sql.*;


//SQL lite

enum SQLTableQuery  implements QueryInterface {
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
            ")")
    ;

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


class Db{
    private String url;


    public Connection connect() throws SQLException {
        Connection conn = null;
        conn = DriverManager.getConnection(url);
        return conn;
    }

    private void createNewDatabase() throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(url);
        if (conn != null) {
            conn.getMetaData();
            conn.close();
        }
    }

    public void executeSql(QueryInterface sql, Object... data) throws SQLException {
        Connection conn = this.connect();
        PreparedStatement pstmt = getPreparedStatement(sql, conn, data);
        pstmt.executeUpdate();
        conn.close();
    }

    private void createTable() throws SQLException {
        this.executeSql(SQLTableQuery.tableItem);
        this.executeSql(SQLTableQuery.tablePerso);
    }

    private Db(String pathSqldb) {
        this.url = "jdbc:sqlite:" + pathSqldb;
        try {
            createNewDatabase();
            createTable();
        } catch (SQLException |ClassNotFoundException e) {
            //Error management
        }
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
}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


@Entity
class Perso {

    @Id long id;
    // .. Other attributes

    //Getters and setters

}

class OODb{
    private final BoxStore store; //OODB

    public OODb(String db){
        this.store = MyObjectBox.builder().name(db).build();
    }

}
