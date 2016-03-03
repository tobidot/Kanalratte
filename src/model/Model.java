package model;

import asset.AssetManager;
import asset.ReadOnlyAssetManager;
import game.mvp.GamePresenter;
import javafx.animation.AnimationTimer;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.ReadOnlyObjectProperty;

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

    /**
     * startet das spiel a.k.a. öffnet das IngameFenster und lädt die Ma neu
     * 
     * @param presenter
     *            Presenter der das Spiel verwaltet
     */
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

    /**
     * 
     * @return Zugriff auf Ingame Mechaniken
     */
    public GameModel getGame()
    {
        return gameModel;
    }

    /**
     * diese Funktion sorgt dafür das Resourcen jetzt geladen werden
     */
    public void loadResources()
    {
        assets.loadAssets();
    }

    /**
     * 
     * @return Ladezeitfortschritt
     */
    public ReadOnlyDoubleProperty getLoadingPercent()
    {
        return assets.getPercent();
    }

    /**
     * 
     * @return Text der als LadeInfo erscheint
     */
    public ReadOnlyObjectProperty<String> getLoadingText()
    {
        return assets.getInfo();
    }

    /**
     * gibt lesenden Zugriff auf den AssetManger
     * 
     * @return AssetManager mit Resourcen
     */
    public ReadOnlyAssetManager getAssets()
    {
        return assets;
    }

}
