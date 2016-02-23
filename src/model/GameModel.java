package model;

import java.util.ArrayList;

import game.gui.ButtonWrapper;
import game.map.BaseMap;
import game.objects.GameObject;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.scene.input.MouseEvent;
import mvp.Presenter;

/**
 * GameModel h�lt alle informationen �ber das wirkliche Spiel
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

    private double cameraDragStartY;

    private double cameraDragStartX;

    private SimpleDoubleProperty cameraX;

    private SimpleDoubleProperty cameraY;

    private double oldCameraY;

    private double oldCameraX;

    public GameModel(Model m)
    {
        model = m;
        loadedMap = null;
        currentAbilities = new ButtonWrapper[0];
        currentMenueOptions = new ButtonWrapper[0];
        allObjects = null;
        isInitiated = false;
        cameraX = new SimpleDoubleProperty(oldCameraX = 0);
        cameraY = new SimpleDoubleProperty(oldCameraY = 0);
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
     * bevor das Spiel geladen ist wird false zur�ckgegeben um unn�tiges oder
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
     * gibt zur�ck welche F�higkeiten die gew�hlte einheit hat
     * 
     * @return List der Buttons zu den F�higkeiten
     */
    public ButtonWrapper[] getCurrentAbilities()
    {
        return currentAbilities;
    }

    /**
     * gibt zur�ck welche F�higkeiten der Spieler gengerell hat
     * 
     * @return List der Buttons zu den F�higkeiten
     */
    public ButtonWrapper[] getCurrentMenueOptions()
    {
        return currentMenueOptions;
    }

    /**
     * l�dt die gebene Map
     * 
     * @param selectedMap
     *            name der Map die geladen werden soll bei null wird eine
     *            standard testMap geladen
     */
    public void loadMap(String selectedMap)
    {
        loadedMap = new BaseMap(model, selectedMap);
    }

    public void moveCamera(double x, double y)
    {
        cameraX.set(oldCameraX + (x - cameraDragStartX));
        cameraY.set(oldCameraY + (y - cameraDragStartY));
    }

    public void startMoveCamera(double x, double y)
    {
        oldCameraY = cameraY.get();
        oldCameraX = cameraX.get();
        cameraDragStartX = x;
        cameraDragStartY = y;
    }

    public ReadOnlyDoubleProperty getCameraX()
    {
        return cameraX;
    }

    public ReadOnlyDoubleProperty getCameraY()
    {
        return cameraY;
    }
}
