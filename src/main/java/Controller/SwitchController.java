package Controller;

import Controller.CharControllers.CreateCharController;
import Controller.CharControllers.SelectCharController;
import View.CharView.CreateCharPage;
import View.CharView.SelectCharPage;

public interface SwitchController {

    /**
     * Change de fenêtre vers la page de connexion
     *
     * @param controller Controleur actuel
     */
    default void goToSelectPage(AbstractController controller) {
        SelectCharPage sPage = new SelectCharPage();
        SelectCharController sControler = new SelectCharController(sPage, controller.getStage());
        setControl(controller, sControler);
    }

    default void goToCreateChar(AbstractController controller){
        CreateCharPage createPage = new CreateCharPage();
        CreateCharController createController = new CreateCharController(createPage, controller.getStage());
        createController.setBack(controller);
        createController.show();
    }

    default void setControl(AbstractController controller, AbstractController abstractController){
        abstractController.setBack(controller);
        abstractController.setInstance(controller.getInstance());
        abstractController.show();
    }
}
