package loadscreen;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import model.Model;
import mvp.MainPresenter;
import mvp.Presenter;

/**
 * zeigt den ladebildschirm und lädt assets vor
 * 
 * @author Tobi
 *
 */
public class LoadingScreenPresenter extends Presenter<LoadView>
{
    /**
     * zeigt den ladebildschirm und lädt assets vor
     */
    public LoadingScreenPresenter(MainPresenter mainP, Model model)
    {
        super(mainP, model, new LoadView());

        Image image = new Image("file:bilder/Test.png");
        view.setImage(new ImagePattern(image, 0, 0, 1, 1, true));
        view.bindProgress(model.getLoadingText(), model.getLoadingPercent());
        show();
        model.loadResources();
        hide();
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

}
