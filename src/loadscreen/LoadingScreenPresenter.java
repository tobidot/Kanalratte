package loadscreen;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import model.Model;
import mvp.MainPresenter;
import mvp.Presenter;

public class LoadingScreenPresenter extends Presenter<LoadView>
{

    public LoadingScreenPresenter(MainPresenter mainP, Model model)
    {
        super(mainP, model, new LoadView());

        Image image = new Image(model.getResourcePath("TEST"));
        view.setImage(new ImagePattern(image, 0, 0, 1, 1, true));
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
