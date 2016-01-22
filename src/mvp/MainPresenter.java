package mvp;

import java.util.HashMap;

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
        childPresenter.put(name, p);
    }

    public void choosePresenter(String name)
    {
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
                    choosePresenter("main menü");
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
}
