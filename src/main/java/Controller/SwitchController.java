package Controller;

import Controller.CharControllers.CreateCharController;
import Controller.CharControllers.SelectCharController;
import Controller.GameControllers.GameController;
import Controller.GameControllers.InventoryController;
import View.CharView.CreateCharPage;
import View.CharView.SelectCharPage;
import View.GameView.GameView;
import View.GameView.InventoryView;

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

    default void goToGame(AbstractController controller){
        GameView gamePage = new GameView();
        GameController gameController = new GameController(gamePage, controller.getStage());
        setControl(controller, gameController);
    }

    default void goToInventory(AbstractController controller){
        InventoryView inventoryView = new InventoryView();
        InventoryController inventoryController = new InventoryController(inventoryView, controller.getStage());
        setControl(controller, inventoryController);
    }
}
