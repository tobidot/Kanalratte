package game.map;

import game.objects.BackgroundImage;
import game.objects.GameObject;
import game.objects.GameObjectTest;
import game.objects.WallSimple;
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
        loadedMap = new GameObject[8];
        // loadedMap[0] = new BackgroundImage(model, "TEST");
        loadedMap[0] = new WallSimple(0, 0);
        loadedMap[1] = new WallSimple(-10, -10);
        loadedMap[2] = new WallSimple(5, 5);
        loadedMap[3] = new WallSimple(20, 0);
        loadedMap[4] = new WallSimple(0, 20);
        loadedMap[5] = new WallSimple(20, 20);
        loadedMap[6] = new WallSimple(120, 120);
        loadedMap[7] = new WallSimple(130, 130);
    }

    public GameObject[] getNewMap()
    {
        // TODO make this an explicit Copy
        return loadedMap;
    }
}
