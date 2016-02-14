package game.map;

import java.util.ArrayList;

import game.objects.BackgroundImage;
import game.objects.GameObject;
import model.Model;

public class BaseMap
{
    private Model model;

    private String mapName;

    private GameObject[] loadedMap;

    public BaseMap(Model model, String mapName)
    {
        this.model = model;
        if (mapName == null)
        {
            loadBuildInTestMap();
        }
        else
        {
            // TODO
            // load map from textfile
            this.mapName = mapName;
            loadedMap = new GameObject[0];
        }
    }

    public GameObject[] getMap()
    {
        return loadedMap;
    }

    public String getMapName()
    {
        return mapName;
    }

    private void loadBuildInTestMap()
    {
        mapName = "@TestMap";
        loadedMap = new GameObject[1];
        loadedMap[0] = new BackgroundImage(model, "TEST");
    }

    public GameObject[] getNewMap()
    {
        // TODO make this an explicit Copy
        return loadedMap;
    }
}
