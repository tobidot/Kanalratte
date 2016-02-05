package game.mvp;

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
        view.addGameMenuButton("Feed", model.getAsBackground("TEST"));
        view.addGameMenuButton("Soldier", null);
        for (int i = 0; i < 10; i++)
            view.addGameMenuButton("House" + i, null);
        Background[] bs = new Background[8];
        bs[0] = model.getAsBackground("ABILITY_A");
        bs[1] = model.getAsBackground("TEST");
        bs[2] = model.getAsBackground("TEST");
        bs[3] = model.getAsBackground("TEST");
        bs[4] = model.getAsBackground("ABILITY_A");
        bs[5] = model.getAsBackground("TEST");
        bs[6] = model.getAsBackground("TEST");
        bs[7] = model.getAsBackground("TEST");
        String[] ns = new String[8];
        ns[0] = "Hunt";
        ns[1] = "Move";
        ns[2] = "Attack";
        ns[3] = "Jump";
        ns[4] = "Hunt";
        ns[5] = "Move";
        ns[6] = "Attack";
        ns[7] = "Jump";
        view.showAbbilties(ns, bs);
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

    public void onAbilityUsed(String id)
    {
        switch (id)
        {
            case "build":
                break;
        }

    }

}
