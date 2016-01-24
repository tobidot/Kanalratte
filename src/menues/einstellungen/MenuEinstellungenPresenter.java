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
        menue[0] = new ImagePattern(model.getImage("MENUE_LEFT"), 0, 0, 1, 1, true);
        menue[1] = new ImagePattern(model.getImage("MENUE_OPTIONS_BACK"), 0, 0, 1, 1, true);
        menue[2] = new ImagePattern(model.getImage("MENUE_RIGHT"), 0, 0, 1, 1, true);
        menue[3] = new ImagePattern(model.getImage("MENUE_RIGHT"), 0, 0, 1, 1, true);
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
