package game.mvp;

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
        view.addGameMenuButton("Feed", model.getAsBackground("TEST"));
        view.addGameMenuButton("Soldier", null);
        for (int i = 0; i < 10; i++)
            view.addGameMenuButton("House" + i, null);
    }

    @Override
    public void show()
    {
        mainPresenter.activateInGameWindow();
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

    public void onExit()
    {
        mainPresenter.choosePresenter("menü-main");

    }

}
