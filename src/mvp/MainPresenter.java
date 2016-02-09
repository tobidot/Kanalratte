package mvp;

import java.util.HashMap;

import game.mvp.GamePresenter;
import game.objects.GameObjectTest;
import javafx.animation.AnimationTimer;
import javafx.stage.Stage;
import loadscreen.LoadingScreenPresenter;
import model.Model;

@SuppressWarnings("rawtypes")
public class MainPresenter extends Presenter<MainView>
{

    private HashMap<String, Presenter> childPresenter;

    private Presenter currentPresenter;

    private Stage primaryStage;

    public MainPresenter(Model model, Stage stage)
    {
        super(null, model, new MainView());
        childPresenter = new HashMap<String, Presenter>();
        currentPresenter = null;
        primaryStage = stage;
    }

    public void addPresenter(Presenter p, String name)
    {
        name = name.toLowerCase();
        childPresenter.put(name, p);
        p.setStage(primaryStage);
    }

    public void choosePresenter(String name)
    {
        name = name.toLowerCase();
        if (childPresenter.containsKey(name))
        {
            if (currentPresenter != null)
            {
                currentPresenter.hide();
            }
            (currentPresenter = childPresenter.get(name)).show();
            view.embed(currentPresenter.view.getUI());
        }
    }

    public void startLoading()
    {
        LoadingScreenPresenter loadP = new LoadingScreenPresenter(this, model);
        long started = System.nanoTime();
        final long waittime = 1000000000l;
        loadP.show();
        AnimationTimer later = new AnimationTimer()
        {
            boolean done = false;

            @Override
            public void handle(long now)
            {
                loadP.updateProgress((double) (now - started) / waittime, "update");

                if (!done && now > started + waittime)
                {
                    loadP.hide();
                    primaryStage.show();
                    choosePresenter("men�-main");
                    done = true;
                    this.stop();
                }
            }
        };
        later.start();
    }

    @Override
    public void show()
    {

    }

    @Override
    public void hide()
    {

    }

    public void activateInGameWindow()
    {

    }

    public void deactivateInGameWindow()
    {
        // TODO Auto-generated method stub

    }

    public void addGameWorldObject(GameObjectTest object)
    {
        if (currentPresenter instanceof GamePresenter)
        {
            ((GamePresenter) currentPresenter).addGameWorldObject(object);
        }

    }
}
