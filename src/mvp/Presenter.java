package mvp;

import model.Model;

@SuppressWarnings("rawtypes")
public abstract class Presenter<T extends View>
{
    protected Model model;

    protected T view;

    protected MainPresenter mainPresenter;

    public Presenter(MainPresenter mainPresenter, Model model, T view)
    {
        this.model = model;
        this.view = view;
        this.mainPresenter = mainPresenter;
    }

    public abstract void show();

    public abstract void hide();

    public View getView()
    {
        return view;
    }
}
