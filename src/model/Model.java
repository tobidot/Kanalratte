package model;

import asset.AssetManager;
import javafx.scene.image.Image;

public class Model
{
    private AssetManager assets;

    public Model()
    {
        assets = new AssetManager();
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
}
