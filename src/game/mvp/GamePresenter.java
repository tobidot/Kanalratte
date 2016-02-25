package game.mvp;

import game.objects.GameObject;
import javafx.event.Event;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
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
     * der Nutzter hat den Verlassen/Settings button gedrückt
     */
    public void onExit()
    {
        mainPresenter.choosePresenter("menü-main");
    }

    /**
     * fügt dem Spiel ein Objekt hinzu
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
                if (mouse.isControlDown() && mouse.isPrimaryButtonDown() || mouse.isMiddleButtonDown())
                {
                    model.getGame().moveCamera(mouse.getSceneX(), mouse.getSceneY());
                }
                else if (!mouse.isControlDown() && mouse.isPrimaryButtonDown())
                {
                    model.getGame().startMoveCamera(mouse.getSceneX(), mouse.getSceneY());
                }
            }
        }
        if (event instanceof ScrollEvent)
        {
            ScrollEvent scroll = (ScrollEvent) event;
            model.getGame().zoomGame(scroll.getDeltaY(), view.getGameScreenWidth() / 2, view.getGameScreenHeight() / 2, scroll.getSceneX(), scroll.getSceneY());
        }
        model.getGame().onGameWorldUserEvent(event);
    }

    /**
     * diese Methode wird immer wieder aufgerufen solange das Spiel läuft
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
