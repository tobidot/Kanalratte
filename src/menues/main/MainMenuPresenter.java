package menues.main;

import javafx.scene.paint.ImagePattern;
import menues.base.MenuBasePresenter;
import model.Model;
import mvp.MainPresenter;

public class MainMenuPresenter extends MenuBasePresenter
{

    public MainMenuPresenter(MainPresenter mainP, Model model)
    {
        super(mainP, model);
        ImagePattern[] menue = new ImagePattern[3];
        menue[0] = model.getAssets().getImagePattern("MENUE_LEFT");
        menue[1] = model.getAssets().getImagePattern("MENUE_OPTIONS_BACK");
        menue[2] = model.getAssets().getImagePattern("MENUE_RIGHT");
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

    /**
     * wird ausgeführt, wenn ein Menübutton gedrückt wurde
     */
    public void onOptionPressed(int index)
    {
        switch (index)
        {
            case 0:
                mainPresenter.choosePresenter("game");
                break;
            case 1:
                mainPresenter.choosePresenter("menü-einstellungen");
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                System.out.println("Out " + index);
                break;
        }

    }

}
