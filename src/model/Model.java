package model;

import asset.AssetManager;
import game.gui.ButtonAnimation;
import game.mvp.GamePresenter;
import javafx.animation.AnimationTimer;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;

public class Model
{
    private AssetManager assets;

    private GameModel gameModel;

    private String selectedMap;

    public Model()
    {
        assets = new AssetManager();
        gameModel = new GameModel(this);
    }

    public String getResourcePath(String key)
    {
        return assets.getResourcePath(key);
    }

    public Object getResource(String key)
    {
        return assets.getResourceData(key);
    }

    public Image getImage(String key)
    {
        return assets.getImage(key);
    }

    public Background getAsBackground(String key)
    {
        return new Background(new BackgroundFill(new ImagePattern(getImage(key), 0, 0, 1, 1, true), new CornerRadii(0), new Insets(0)));
    }

    public void bindStageResolution(Stage stage)
    {
        stage.setWidth(0);
    }

    public ButtonAnimation getButtonAnimation(String key)
    {
        return assets.getButtonAnimation(key);
    }

    public void gameStart(GamePresenter presenter)
    {
        gameModel.loadMap(selectedMap);
        AnimationTimer mainLoop = new AnimationTimer()
        {
            private long nextFrameStamp = System.nanoTime();

            @Override
            public void handle(long now)
            {
                if (now > nextFrameStamp)
                {
                    nextFrameStamp += 16000000;
                    presenter.gameMainLoop();
                }
            }

        };
        mainLoop.start();
    }

    public void onGameWorldUserEvent(Event event)
    {
        EventType<? extends Event> type = event.getEventType();

        if (type.getName().equals("MouseEvent") && event instanceof MouseEvent)
        {
            MouseEvent me = (MouseEvent) event;
        }

    }

    public GameModel getGame()
    {
        return gameModel;
    }

    public ReadOnlyDoubleProperty getLoadingPercent()
    {
        return assets.getPercent();
    }

    public void loadResources()
    {
        assets.loadAssets();
    }

    public ReadOnlyObjectProperty<String> getLoadingText()
    {
        return assets.getInfo();
    }

}
