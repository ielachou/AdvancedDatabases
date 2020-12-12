package Controller.CharControllers;

import Controller.AbstractController;
import Controller.SwitchController;
import Model.Game.Perso;
import View.CharView.SelectCharPage;
import View.ViewInterface;
import javafx.stage.Stage;

import java.util.ArrayList;

public class SelectCharController extends AbstractController implements SelectCharPage.Listener, SwitchController {
    private final SelectCharPage selectCharPage;

    public SelectCharController(SelectCharPage page, Stage primaryStage){
        super(primaryStage);
        this.selectCharPage = page;
        this.selectCharPage.setListener(this);
    }

    @Override
    public ViewInterface getView() {
        return selectCharPage;
    }

    @Override
    public void createNewPerso() {
        goToCreateChar(this);
    }

    @Override
    public void goBack() {
        close();
    }

    @Override
    public void selectPerso(SelectCharPage.PersoInfo persoInfo) {
        setPersoInfo(getDb().getPerso(persoInfo.getPseudo()));
        goToGame(this);
    }

    @Override
    public void begin(){
        
        ArrayList<Perso> listePersos = getDb().getPersos(180000);
        Perso[] persoTab = new Perso[0];
        if (listePersos != null){
            persoTab = new Perso[listePersos.size()];
            for(int i = 0; i < listePersos.size(); i++){
                persoTab[i] = listePersos.get(i);
            }
        }
        this.selectCharPage.addChar(persoTab);
    }
}
