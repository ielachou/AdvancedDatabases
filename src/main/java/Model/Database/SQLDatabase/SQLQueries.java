package Model.Database.SQLDatabase;

public enum SQLQueries implements QueryInterface {

    ;
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
