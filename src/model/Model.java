package model;

import asset.AssetManager;

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
}
