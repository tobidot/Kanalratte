package mvp;

import javafx.stage.Stage;
import model.Model;

@SuppressWarnings("rawtypes")
public abstract class Presenter<T extends View>
{
    protected Model model;

    protected T view;

    protected MainPresenter mainPresenter;

    @SuppressWarnings("unchecked")
    public Presenter(MainPresenter mainPresenter, Model model, T view)
    {
        this.model = model;
        this.view = view;
        this.view.setPresenter(this);
        this.view.setImages(model);
        this.mainPresenter = mainPresenter;
    }

    /**
     * wird aufgerufen wenn dieser Presenter gezeigt werden soll
     */
    public abstract void show();

    /**
     * wird aufgerufen wenn dieser Presenter aktiv war und nun ein anderer
     * augerfuen wird
     */
    public abstract void hide();

    /**
     * 
     * @return die View die von diesem Presenter verwaltert wird
     */
    public View getView()
    {
        return view;
    }

    protected void setStage(Stage primaryStage)
    {
        view.setStage(primaryStage);
    }
}
