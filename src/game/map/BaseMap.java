package game.map;

import java.util.ArrayList;

import game.objects.BackgroundImage;
import game.objects.Border;
import game.objects.GameObject;
import game.objects.Kanal;
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
        ArrayList<GameObject> bufflist = new ArrayList<GameObject>(100);
        bufflist.add(new BackgroundImage(model, "MAP_BACKGROUND_B"));
        for (int i = 0; i < 19; i++)
        {
            bufflist.add(new WallSimple(i, 0));
            bufflist.add(new WallSimple(19, i));
            bufflist.add(new WallSimple(19 - i, 19));
            bufflist.add(new WallSimple(0, 19 - i));

        }
        for (int i = 0; i < 18; i++)
        {
            for (int j = 0; j < 18; j++)
            {
                bufflist.add(new Border(i + 1, j + 1));
            }
        }
        for (int i = 0; i < 10; i++)
            bufflist.add(new Kanal(1 + i, 5));
        for (int i = 0; i < 6; i++)
            bufflist.add(new Kanal(11, 5 + i));
        for (int i = 0; i < 6; i++)
            bufflist.add(new Kanal(11 + i, 5 + i));
        for (int i = 0; i < 6; i++)
            bufflist.add(new Kanal(12 + i, 5 + i));
        for (int i = 0; i < 3; i++)
            bufflist.add(new Kanal(11 - i, 4));
        bufflist.add(new WallSimple(1, 2));

        mapName = "@TestMap";
        loadedMap = new GameObject[bufflist.size()];
        loadedMap = bufflist.toArray(loadedMap);
    }

    public GameObject[] getNewMap()
    {
        // TODO make this an explicit Copy
        return loadedMap;
    }
}
