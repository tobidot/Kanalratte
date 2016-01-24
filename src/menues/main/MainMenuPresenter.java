package menues.main;

import javafx.scene.paint.ImagePattern;
import menues.base.MenuBasePresenter;
import model.Model;
import mvp.MainPresenter;
import mvp.Presenter;

public class MainMenuPresenter extends MenuBasePresenter
{

    public MainMenuPresenter(MainPresenter mainP, Model model)
    {
        super(mainP, model);
        ImagePattern[] menue = new ImagePattern[6];
        menue[0] = new ImagePattern(model.getImage("MENUE_LEFT"), 0, 0, 1, 1, true);
        menue[1] = new ImagePattern(model.getImage("MENUE_OPTIONS_BACK"), 0, 0, 1, 1, true);
        menue[2] = new ImagePattern(model.getImage("MENUE_RIGHT"), 0, 0, 1, 1, true);
        view.setImageSet(menue);
        view.setMenueOptions("Spielen", "Einstellungen", "Exit");
    }

    @Override
    public void show()
    {

    }

    @Override
    public void hide()
    {

    }

    public void onOptionPressed(int index)
    {
        switch (index)
        {
            case 0:
                mainPresenter.choosePresenter("men�-einstellungen");
            case 1:
            case 2:
            case 3:
            case 4:
                System.out.println("Out " + index);
                break;
        }

    }

}
