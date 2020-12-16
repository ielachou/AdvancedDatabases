package Presentation;

public class Remove {
    /*
    removePerso("DELETE FROM persos WHERE pseudo = ?"),
    removeInventory("DELETE FROM items WHERE ownerName = ?"),
     */

    public void removePerso(Perso perso) {
        removePerso(perso.getPseudo());
    }

    public void removePerso(String pseudo) {
        removeInventory(pseudo);
        try {
            this.executeSql(SQLQueries.removePerso,pseudo);
        } catch (SQLException throwables) {
            //Error management
        }
    }









    @Override
    public void removePerso(String pseudo) {
        this.removePerso(getPerso(pseudo));
    }

    @Override
    public void removePerso(Perso perso) {
        Box<Perso> box = store.boxFor(Perso.class);
        this.removeInventory(perso);
        box.remove(perso);
    }

}
