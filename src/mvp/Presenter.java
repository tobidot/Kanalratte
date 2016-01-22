package mvp;

import model.Model;

@SuppressWarnings("rawtypes")
public abstract class Presenter<T extends View>
{
    protected Model model;

    protected T view;

    public Presenter(Model model, T view)
    {
        this.model = model;
        this.view = view;
    }

    public abstract void show();

    public abstract void hide();

    public View getView()
    {
        return view;
    }
}
