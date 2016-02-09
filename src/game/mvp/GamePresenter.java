package game.mvp;

import com.sun.javafx.font.directwrite.RECT;

import game.gui.ButtonWrapper;
import game.gui.EventAction;
import game.objects.GameObjectTest;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
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
        for (int i = 0; i < 19; i++)
        {
            ButtonWrapper b = new ButtonWrapper("Name" + i, "Ein Button", model.getAsBackground("TEST"), null);
            view.addGameMenuButton(b);
        }
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

        ButtonWrapper[] abilities = new ButtonWrapper[2];
        abilities[0] = new ButtonWrapper("A", "Eine Option", model.getButtonAnimation("ANIMATION_TEST"), new EventAction()
        {
            @Override
            public void trigger()
            {
                speed += 0.1;
            }
        });
        abilities[1] = new ButtonWrapper("A", "Eine Option", model.getButtonAnimation("ANIMATION_TEST"), new EventAction()
        {
            @Override
            public void trigger()
            {
                speed -= 0.1;
            }
        });
        view.showAbbilties(abilities);

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

    public void addGameWorldObject(GameObjectTest object)
    {
        view.addObjectInGame(object.getVisual());

    }

    private GameObjectTest object = null;

    private double speed = 0.01;

    public void gameMainLoop()
    {
        if (object == null)
        {
            object = new GameObjectTest();
            addGameWorldObject(object);
        }
        object.getVisual().setLayoutX(object.getVisual().getLayoutX() + speed);
    }

}
