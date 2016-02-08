package starter;

import game.mvp.GamePresenter;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import menues.einstellungen.MenuEinstellungenPresenter;
import menues.main.MainMenuPresenter;
import model.Model;
import mvp.MainPresenter;

public class Starter extends Application
{

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception
    {
        Model model = new Model();
        MainPresenter presenter = new MainPresenter(model, stage);

        presenter.addPresenter(new MainMenuPresenter(presenter, model), "menü-main");
        presenter.addPresenter(new MenuEinstellungenPresenter(presenter, model), "menü-einstellungen");
        presenter.addPresenter(new GamePresenter(presenter, model), "game");

        Pane root = presenter.getView().getUI();
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.setMinWidth(800);
        stage.setHeight(600);

        presenter.startLoading();
    }
}
