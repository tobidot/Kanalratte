package mvp;

import java.util.HashMap;

import javafx.stage.Stage;
import loadscreen.LoadingScreenPresenter;
import model.Model;

@SuppressWarnings("rawtypes")
public class MainPresenter extends Presenter<MainView>
{

    private HashMap<String, Presenter> childPresenter;

    private Presenter currentPresenter;

    private Stage primaryStage;

    /**
     * 
     * @param model
     *            Das model
     * @param stage
     *            die Stage auf der die View gezeigt werden
     */
    public MainPresenter(Model model, Stage stage)
    {
        super(null, model, new MainView());
        childPresenter = new HashMap<String, Presenter>();
        currentPresenter = null;
        primaryStage = stage;

        new LoadingScreenPresenter(mainPresenter, model);
    }

    /**
     * Add a new Presenter to the MainPresenter
     * 
     * @param p
     *            Presenter to add
     * @param name
     *            Bezeichner für den Presenter
     */
    public void addPresenter(Presenter p, String name)
    {
        name = name.toLowerCase();
        childPresenter.put(name, p);
        p.setStage(primaryStage);
    }

    /**
     * wählt einen Presenter aus der aktiv sein soll
     * 
     * @param name
     *            Bezeichner des gewwählten Presenter
     */
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
        choosePresenter("menü-main");
        primaryStage.show();
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
