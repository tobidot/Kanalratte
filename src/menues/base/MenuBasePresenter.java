package menues.base;

import model.Model;
import mvp.MainPresenter;
import mvp.Presenter;

public abstract class MenuBasePresenter extends Presenter<MenuBaseView>
{

    public MenuBasePresenter(MainPresenter mainP, Model model)
    {
        super(mainP, model, new MenuBaseView());
    }

    @Override
    public void show()
    {

    }

    @Override
    public void hide()
    {

    }

    public abstract void onOptionPressed(int index);

}
