package Controller.GameControllers;

import Controller.AbstractController;
import Controller.SwitchController;
import View.CharView.SelectCharPage;
import View.GameView.GameView;
import View.ViewInterface;
import javafx.stage.Stage;

public class GameController extends AbstractController implements GameView.Listener, SwitchController {

    private final GameView gamePage;

    public GameController(GameView page, Stage primaryStage) {
        super(primaryStage);
        this.gamePage = page;
        this.gamePage.setListener(this);
    }

    @Override
    public ViewInterface getView() {
        return gamePage;
    }

    @Override
    public void inventory() {
        goToInventory(this);
    }

    @Override
    public void fight(){
        goToFight(this);
    }
}
