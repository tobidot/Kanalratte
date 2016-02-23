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

    private double cameraDragStartY;

    private double cameraDragStartX;

    private SimpleDoubleProperty cameraX;

    private SimpleDoubleProperty cameraY;

    private SimpleDoubleProperty cameraZoom;

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
        cameraZoom = new SimpleDoubleProperty(1);
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

    /**
     * Die Kamera wurde bewegt
     * 
     * @param x
     *            neue X Koordinate auf dem Screen
     * @param y
     *            neue Y Koordinate auf dem Screen
     */
    public void moveCamera(double x, double y)
    {
        cameraX.set(oldCameraX + (x - cameraDragStartX) / cameraZoom.get());
        cameraY.set(oldCameraY + (y - cameraDragStartY) / cameraZoom.get());
    }

    /**
     * Eine mögliche Kamera bewegung wurde initiert
     * 
     * @param x
     *            start X-Koordinate
     * @param y
     *            start Y-Koordinate
     */
    public void startMoveCamera(double x, double y)
    {
        oldCameraY = cameraY.get();
        oldCameraX = cameraX.get();
        cameraDragStartX = x;
        cameraDragStartY = y;
    }

    /**
     * 
     * @return gibt die X-Poisition der Kamera zurück
     */
    public ReadOnlyDoubleProperty getCameraX()
    {
        return cameraX;
    }

    /**
     * 
     * @return gibt die Y-Poisition der Kamera zurück
     */
    public ReadOnlyDoubleProperty getCameraY()
    {
        return cameraY;
    }

    public ReadOnlyDoubleProperty getCameraZoom()
    {
        return cameraZoom;
    }

    public void zoomGame(double deltaY, double centerWidth, double centerHeight, double x, double y)
    {

        double oldZoom, newZoom;
        // oldZoom = 1 / cameraZoom.get();
        // cameraZoom.set(cameraZoom.get() + deltaY / 200 *
        // cameraZoom.get());
        // if (cameraZoom.get() < 0.1)
        // cameraZoom.set(0.1);
        // if (cameraZoom.get() > 5)
        // cameraZoom.set(5);
        // newZoom = oldZoom - cameraZoom.get();
        if (deltaY > 0)
        {
            oldZoom = 1 / cameraZoom.get();
            newZoom = cameraZoom.get() * 2;
        }
        else
        {
            oldZoom = 1 / cameraZoom.get();
            newZoom = cameraZoom.get() / 2;
        }
        cameraZoom.set(newZoom);
        /// zurückzoomen
        double centerX = (centerWidth - cameraX.get()) / newZoom;
        double centerY = (centerHeight - cameraY.get()) / newZoom;

        /// auf neuen Zoom Wert
        cameraX.set(centerWidth + centerX);
        cameraY.set(centerHeight + centerY);
        // cameraX.set(centerWidth);
        // cameraY.set(centerHeight);

        // cameraY.set(cameraY.get() * cameraZoom.get());
        System.out.println("Step : " + centerWidth * 2 + " / " + centerHeight * 2);
        System.out.println("CamX : " + cameraX.get());
        System.out.println("CamY : " + cameraY.get());
        System.out.println("Starring at  : " + centerX + "/" + centerY + "");
        System.out.println("CamZoom : " + cameraZoom.get() + " new " + newZoom + " old " + oldZoom);
        System.out.println("--------------------");
    }
}
