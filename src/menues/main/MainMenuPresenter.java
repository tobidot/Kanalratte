package menues.main;

import javafx.scene.paint.ImagePattern;
import model.Model;
import mvp.Presenter;

public class MainMenuPresenter extends Presenter<MainMenuView>
{

    public MainMenuPresenter(Model model)
    {
        super(model, new MainMenuView());
        ImagePattern[] menue = new ImagePattern[5];
        menue[0] = null;
        menue[1] = new ImagePattern(model.getImage("MENUE_LEFT"), 0, 0, 1, 1, true);
        menue[2] = null;
        menue[3] = null;
        menue[4] = null;
        view.setImageSet(menue);
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
