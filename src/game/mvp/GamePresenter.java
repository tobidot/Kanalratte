package game.mvp;

import model.Model;
import mvp.MainPresenter;
import mvp.Presenter;

/**
 * The Gamepresenter handle everything once the game has realy started
 * 
 * @author Tobi
 *
 */
public class GamePresenter extends Presenter<GameView>
{

    public GamePresenter(MainPresenter mainPresenter, Model model)
    {
        super(mainPresenter, model, new GameView());
        /// Hier im Presenter GameMenuButtons einfügen
        /**
         * ButtonWrapper bw = new ButtonWrapper("<identName>","<description>",model.getAsBackground("<ResourceName>"),<EventAction>);
         * view.addGameMenuButton(bw);
         */
        
        /// Und Ability-Buttons
        /**
         * ButtonWrapper[] bw = new ButtonWrapper[1];
         * bw[0] = new ButtonWrapper("<identName>","<description>",model.getAsBackground("<ResourceName>"),<EventAction>);
         * view.showAbbilties(bw);
         */
       
    }

    @Override
    public void show()
    {
        mainPresenter.activateInGameWindow();
    }

    @Override
    public void hide()
    {
        mainPresenter.deactivateInGameWindow();

    }

//    public void onInGameOption(String nameID)
//    {
//        switch (nameID)
//        {
//        /**
//         * case "":
//         */
//            case "Soldier":
//                System.out.println("Soldat ausbilden");
//                break;
//        }
//    }

    public void onExit()
    {
        mainPresenter.choosePresenter("menü-main");
    }

//    public void onAbilityUsed(String id)
//    {
//        switch (id)
//        {
//            case "build":
//                break;
//        }
//
//    }

}
