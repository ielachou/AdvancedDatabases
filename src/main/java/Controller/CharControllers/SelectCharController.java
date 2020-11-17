package Controller.CharControllers;

import Controller.AbstractController;
import Controller.SwitchController;
import View.CharView.SelectCharPage;
import View.ViewInterface;
import javafx.stage.Stage;

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

    }

    @Override
    public void selectPerso() {

    }
}
