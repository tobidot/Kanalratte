package game.mvp;

import model.Model;
import mvp.MainPresenter;
import mvp.Presenter;
import mvp.View;

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
    }

    @Override
    public void hide()
    {
        mainPresenter.deactivateInGameWindow();

    }

}
