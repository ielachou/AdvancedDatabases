package Controller;

import Controller.CharControllers.CreateCharController;
import Controller.CharControllers.SelectCharController;
import Controller.GameControllers.FightController;
import Controller.GameControllers.GameController;
import Controller.GameControllers.InventoryController;
import Model.Database.ObjectBox.ObjectBoxDatabase;
import Model.Database.SQLDatabase.SQLDatabase;
import View.CharView.CreateCharPage;
import View.CharView.SelectCharPage;
import View.GameView.FightView;
import View.GameView.GameView;
import View.GameView.InventoryView;

public interface SwitchController {

    /**
     * Change de fenêtre vers la page de connexion
     *
     * @param controller Controleur actuel
     */
    default void goToSelectPage(AbstractController controller, boolean mode) {
        SelectCharPage sPage = new SelectCharPage();
        SelectCharController sControler = new SelectCharController(sPage, controller.getStage());
        if(mode){
            sControler.setDb(ObjectBoxDatabase.getInstance());
        }else{
            sControler.setDb(SQLDatabase.getInstance());
        }
        setControl(controller, sControler);
    }

    default void goToCreateChar(AbstractController controller){
        CreateCharPage createPage = new CreateCharPage();
        CreateCharController createController = new CreateCharController(createPage, controller.getStage());

        setControl(controller, createController);
    }

    default void setControl(AbstractController controller, AbstractController abstractController){
        abstractController.setBack(controller);
        abstractController.setInstance(controller.getInstance());
        if(controller.getDb()!=null) {
            abstractController.setDb(controller.getDb());
        }
        abstractController.setPersoInfo(controller.getPersoInfo());
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

    default void goToFight(AbstractController controller){
        FightView fightView = new FightView();
        FightController fightController = new FightController(fightView, controller.getStage());
        setControl(controller, fightController);
    }
}
