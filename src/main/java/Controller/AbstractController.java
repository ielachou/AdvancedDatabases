package Controller;


import Model.Database.Database;
import Model.Perso;
import javafx.application.HostServices;
import javafx.stage.Stage;

/**
 * Controleur abstrait qui va implémeter tous les controleurs
 */
public abstract class AbstractController implements UtilsController {
    private final Stage primaryStage;
    private HostServices instance;
    private UtilsController back;
    private Database db;
    private Perso persoInfo;

    /**
     * Constructeur d'un controleur
     *
     * @param stage Stage principal de la vue
     */
    public AbstractController(Stage stage) {
        this.primaryStage = stage;
    }


    @Override
    public Stage getStage() {
        return primaryStage;
    }

    /**
     * Fonction permettant de récuperer la base de données
     *
     * @return la base de données
     */
    public Database getDb() {
        return db;
    }

    public void setDb(Database database){
        this.db = database;
    }

    public void setPersoInfo(Perso perso){
        this.persoInfo = perso;
    }


    /**
     * Fonction permettant de retourner a la page précédente
     *
     * @return la page précédente
     */
    @Override
    public UtilsController getBack() {
        return this.back;
    }

    /**
     * Permet de set une page précédente
     *
     * @param backView la page précédente
     */

    @Override
    public void setBack(UtilsController backView) {
        this.back = backView;
    }



    public HostServices getInstance() {
        return instance;
    }

    public void setInstance(HostServices hostInstance) {
        this.instance = hostInstance;
    }

    protected Perso getPersoInfo() {
        return persoInfo;
    }
}
