package model;

import java.util.ArrayList;

import game.gui.ButtonWrapper;
import game.map.BaseMap;
import game.objects.GameObject;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.scene.input.MouseEvent;

/**
 * GameModel hält alle informationen über das wirkliche Spiel
 * 
 * @author Tobi
 *
 */
public class GameModel
{
    private Model model;

    private BaseMap loadedMap;

    private ArrayList<GameObject> allObjects;

    private ButtonWrapper[] currentAbilities;

    private ButtonWrapper[] currentMenueOptions;

    private boolean isInitiated;

    public GameModel(Model m)
    {
        model = m;
        loadedMap = null;
        currentAbilities = new ButtonWrapper[0];
        currentMenueOptions = new ButtonWrapper[0];
        allObjects = null;
        isInitiated = false;
    }

    /**
     * wird aufgerufen bei userevents wie
     * <ul>
     * <li>MouseEvent
     * <li>TastaturEvent
     * <li>etc
     * <ul>
     * 
     * @param event
     *            Event das aufgetreten ist
     */
    public void onGameWorldUserEvent(Event event)
    {
        EventType<? extends Event> type = event.getEventType();

        if (event instanceof MouseEvent)
        {
            MouseEvent me = (MouseEvent) event;
            GameObject obj = allObjects.get(0);
        }

    }

    /**
     * bevor das Spiel geladen ist wird false zurückgegeben um unnötiges oder
     * falsche Update zu vermeiden bevor alles initiert wurde
     * 
     * @return Ist initiert
     */
    public boolean isInitiated()
    {
        return isInitiated;
    }

    /**
     * if a Map was loaded this sets the GameWorld to the state of the loaded
     * Map
     * 
     * @return a List of all Objects in the Map
     */
    public GameObject[] init()
    {
        GameObject[] go = loadedMap.getNewMap();
        currentMenueOptions = new ButtonWrapper[0];
        currentAbilities = new ButtonWrapper[0];

        allObjects = new ArrayList<GameObject>();
        for (int i = 0; i < go.length; i++)
        {
            allObjects.add(go[i]);
        }
        isInitiated = true;
        return go;
    }

    /**
     * berechnet das verhalten der Welt ohne die eingriffe des Users
     */
    public void calculatePhysics()
    {
        long time = System.nanoTime();
        allObjects.forEach((obj) -> {
            if (obj != null)
            {
                obj.calculatePhysics(time);
            }
        });

    }

    /**
     * gibt zurück welche Fähigkeiten die gewählte einheit hat
     * 
     * @return List der Buttons zu den Fähigkeiten
     */
    public ButtonWrapper[] getCurrentAbilities()
    {
        return currentAbilities;
    }

    /**
     * gibt zurück welche Fähigkeiten der Spieler gengerell hat
     * 
     * @return List der Buttons zu den Fähigkeiten
     */
    public ButtonWrapper[] getCurrentMenueOptions()
    {
        return currentMenueOptions;
    }

    /**
     * lädt die gebene Map
     * 
     * @param selectedMap
     *            name der Map die geladen werden soll bei null wird eine
     *            standard testMap geladen
     */
    public void loadMap(String selectedMap)
    {
        loadedMap = new BaseMap(model, selectedMap);
    }
}
