package model;

import asset.AssetManager;
import asset.AssetManager.ImageResource;
import game.gui.ButtonAnimation;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;

public class Model
{
    private AssetManager assets;

    private ScreenProperty screen;

    public Model()
    {
        assets = new AssetManager();
        screen = new ScreenProperty(800, 600);
    }

    public String getResourcePath(String key)
    {
        return assets.getResourcePath(key);
    }

    public Object getResource(String key)
    {
        return assets.getResource(key);
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
}
