package menues.einstellungen;

import javafx.scene.paint.ImagePattern;
import menues.base.MenuBasePresenter;
import model.Model;
import mvp.MainPresenter;

public class MenuEinstellungenPresenter extends MenuBasePresenter
{

    public MenuEinstellungenPresenter(MainPresenter mainPresenter, Model model)
    {
        super(mainPresenter, model);
        ImagePattern[] menue = new ImagePattern[4];
        menue[0] = model.getAssets().getImagePattern("MENUE_LEFT");
        menue[1] = model.getAssets().getImagePattern("MENUE_OPTIONS_BACK");
        menue[2] = model.getAssets().getImagePattern("MENUE_RIGHT");
        menue[3] = model.getAssets().getImagePattern("MENUE_RIGHT");
        view.setImageSet(menue);
        view.setMenueOptions("Audio", "Grafik", "zurück");
    }

    @Override
    public void onOptionPressed(int index)
    {
        switch (index)
        {
            case 0:
                break;
            case 1:
                break;
            case 2:
                mainPresenter.choosePresenter("menü-main");
                break;
        }
    }

}
