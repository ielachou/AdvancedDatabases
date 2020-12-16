package Presentation;

/*
selectPerso("SELECT ID,pseudo,sexe,dommages,agilite,intelligence, " +
        "chance,force,vitality,energy,y,x  FROM persos WHERE pseudo = ? " ),

        selectItem("SELECT ID,name,ownerName,description,vitality,strength,chance, " +
        "intelligence,agility,damages,equiped  FROM items WHERE ownerName = ?"),

 */

class Read{
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
            //Error management
        }
        return res;
    }






    public Perso getPerso(String pseudo) {
        Box<Perso> box = store.boxFor(Perso.class);
        Perso perso = box.query().equal(Perso_.pseudo, pseudo).build().findFirst();
        return perso;
    }
}




