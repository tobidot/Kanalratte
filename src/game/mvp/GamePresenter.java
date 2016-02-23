package game.mvp;

import game.gui.ButtonWrapper;
import game.objects.GameObject;
import javafx.event.Event;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import model.Model;
import mvp.MainPresenter;
import mvp.Presenter;

/**
 * The Gamepresenter handle everything once the game has realy started
 * 
 * @author Tobi
 *
 */
public class GamePresenter extends Presenter<GameView>
{

    public GamePresenter(MainPresenter mainPresenter, Model model)
    {
        super(mainPresenter, model, new GameView());
    }

    @Override
    public void show()
    {
        mainPresenter.activateInGameWindow();
        model.gameStart(this);
    }

    @Override
    public void hide()
    {
        mainPresenter.deactivateInGameWindow();

    }

    public void onInGameOption(String nameID)
    {
        switch (nameID)
        {
            case "Soldier":
                System.out.println("Soldat ausbilden");
                break;
        }
    }

    public void onAbilityUsed(String id)
    {
        switch (id)
        {
            case "build":
                break;
        }

    }

    /**
     * der Nutzter hat den Verlassen/Settings button gedr�ckt
     */
    public void onExit()
    {
        mainPresenter.choosePresenter("men�-main");
    }

    /**
     * f�gt dem Spiel ein Objekt hinzu
     * 
     * @param object
     */
    public void addGameWorldObject(GameObject object, String param)
    {
        view.addObjectInGame(object);
        object.init(model, model.getGame(), param);

    }

    public void onGameWorldUserEvent(Event event)
    {
        if (event instanceof MouseEvent)
        {
            MouseEvent mouse = (MouseEvent) event;
            if (mouse.getEventType() == MouseEvent.MOUSE_PRESSED)
            {
                model.getGame().startMoveCamera(mouse.getSceneX(), mouse.getSceneY());
            }
            if (mouse.getEventType() == MouseEvent.MOUSE_DRAGGED)
            {
                if (mouse.isControlDown())
                {
                    model.getGame().moveCamera(mouse.getSceneX(), mouse.getSceneY());
                }
            }
        }
        model.getGame().onGameWorldUserEvent(event);
    }

    /**
     * diese Methode wird immer wieder aufgerufen solange das Spiel l�uft
     * 
     */
    public void gameMainLoop()
    {
        if (!model.getGame().isInitiated())
        {
            GameObject[] gos = model.getGame().init();
            for (int i = 0; i < gos.length; i++)
            {
                addGameWorldObject(gos[i], "");
            }
            view.showAbbilties(model.getGame().getCurrentAbilities());
        }
        else
        {
            model.getGame().calculatePhysics();
        }
    }

}
