package Controller.GameControllers;

import Controller.AbstractController;
import Controller.SwitchController;
import View.GameView.FightView;
import View.ViewInterface;
import javafx.stage.Stage;

public class FightController extends AbstractController implements FightView.Listener, SwitchController {

    private final FightView fightPage;

    @Override
    public ViewInterface getView() {
        return fightPage;
    }

    public FightController(FightView page, Stage primaryStage) {
        super(primaryStage);
        this.fightPage = page;
        this.fightPage.setListener(this);
    }

    @Override
    public void back(){
        close();
    }
}
