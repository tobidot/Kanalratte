package model;

import asset.AssetManager;
import javafx.scene.image.Image;
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

    public void bindStageResolution(Stage stage)
    {

        stage.setWidth(0);
    }
}
