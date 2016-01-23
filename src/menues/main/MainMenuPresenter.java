package menues.main;

import javafx.scene.paint.ImagePattern;
import model.Model;
import mvp.MainPresenter;
import mvp.Presenter;

public class MainMenuPresenter extends Presenter<MainMenuView>
{

    public MainMenuPresenter(MainPresenter mainP, Model model)
    {
        super(mainP, model, new MainMenuView());
        ImagePattern[] menue = new ImagePattern[6];
        menue[0] = new ImagePattern(model.getImage("TEST"), 0, 0, 1, 1, true);
        menue[1] = new ImagePattern(model.getImage("MENUE_LEFT"), 0, 0, 1, 1, true);
        menue[2] = new ImagePattern(model.getImage("MENUE_RIGHT"), 0, 0, 1, 1, true);
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
