package Controller.CharControllers;

import Controller.AbstractController;
import Controller.SwitchController;
import View.CharView.CreateCharPage;
import View.ViewInterface;
import javafx.stage.Stage;

public class CreateCharController extends AbstractController implements CreateCharPage.Listener, SwitchController {

    private final CreateCharPage createCharPage;

    public CreateCharController(CreateCharPage page, Stage primaryStage) {
        super(primaryStage);
        this.createCharPage = page;
        this.createCharPage.setListener(this);
    }

    @Override
    public ViewInterface getView() {
        return createCharPage;
    }

    @Override
    public void goBack() {
        close();
    }

    @Override
    public void create() {

    }
}
