package loadscreen;

import model.Model;
import mvp.Presenter;

public class LoadingScreenPresenter extends Presenter<LoadView>
{

    public LoadingScreenPresenter(Model model)
    {
        super(model, new LoadView());
    }

    @Override
    public void show()
    {
        view.show();

    }

    @Override
    public void hide()
    {
        view.hide();
    }

    public void updateProgress(double percent, String out)
    {
        view.setProgress(percent, out);
    }
}
