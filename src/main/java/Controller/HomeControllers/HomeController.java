package Controller.HomeControllers;


import Controller.AbstractController;
import Model.Database.Database;
import View.HomeViews.HomePage;
import View.ViewInterface;
import javafx.stage.Stage;

/**
 * Controleur de la page de connexion
 */
public class HomeController extends AbstractController implements HomePage.Listener {
    private final HomePage homePage;

    /**
     * Constructeur
     *
     * @param page         Page de connexion
     * @param primaryStage Stage principal de la vue
     */
    public HomeController(HomePage page, Stage primaryStage) {
        super(primaryStage);
        this.homePage = page;
        this.homePage.setListener(this);
    }




    /**
     * Met à jour la base de données
     *
     * @param db base de données du projet
     */
    @Override
    public void setDb(Database db){
        super.setDb(db);
    }



    /**
     * Renvoie la vue de la page
     *
     * @return la vue de la page de connexion
     */
    public ViewInterface getView() {
        return homePage;
    }
}
