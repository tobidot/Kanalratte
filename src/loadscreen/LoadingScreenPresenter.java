package loadscreen;

import javafx.scene.image.Image;
import model.Model;
import mvp.Presenter;

public class LoadingScreenPresenter extends Presenter<LoadView>
{

    public LoadingScreenPresenter(Model model)
    {
        super(model, new LoadView());

        Image image = new Image(model.getResourcePath("TEST"));
        view.setImage(image);
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
