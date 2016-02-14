package game.mvp;

import game.gui.ButtonWrapper;
import game.objects.GameObject;
import javafx.event.Event;
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
    public void addGameWorldObject(GameObject object)
    {
        view.addObjectInGame(object.getVisual());
    }

    public void onGameWorldUserEvent(Event event)
    {
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
                addGameWorldObject(gos[i]);
            }
            view.showAbbilties(model.getGame().getCurrentAbilities());
        }
        else
        {
            model.getGame().calculatePhysics();
        }
    }

}
