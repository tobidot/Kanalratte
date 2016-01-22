package menues.main;

import model.Model;
import mvp.Presenter;

public class MainMenuPresenter extends Presenter<MainMenuView>
{

    public MainMenuPresenter(Model model)
    {
        super(model, new MainMenuView());
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
