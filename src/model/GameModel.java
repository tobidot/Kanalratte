package model;

import java.util.ArrayList;

import game.gui.ButtonWrapper;
import game.objects.GameObject;
import game.objects.GameObjectTest;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class GameModel
{
    private Model model;

    private ArrayList<GameObject> allObjects = null;

    private ButtonWrapper[] currentAbilities = new ButtonWrapper[0];

    private boolean isInitiated = false;

    private double speed = 0.01;

    public GameModel(Model m)

    {
        model = m;
    }

    public void onGameWorldUserEvent(Event event)
    {
        EventType<? extends Event> type = event.getEventType();

        if (type.getName().equals("MouseEvent") && event instanceof MouseEvent)
        {
            MouseEvent me = (MouseEvent) event;
            GameObject obj = allObjects.get(0);
            obj.getVisual().setLayoutX(me.getX());
            obj.getVisual().setLayoutY(me.getY());
        }

    }

    public boolean isInitiated()
    {
        return isInitiated;
    }

    public GameObject[] init()
    {
        GameObject[] go = new GameObject[1];
        GameObjectTest gtest = new GameObjectTest();
        go[0] = gtest;
        currentAbilities = new ButtonWrapper[2];
        currentAbilities[0] = new ButtonWrapper("Change Color", "ChangeColor to Blue", model.getAsBackground("MENUE_LEFT"), gtest.actionChangeColor(Color.ALICEBLUE));
        currentAbilities[1] = new ButtonWrapper("Change Color", "ChangeColor to Red", model.getAsBackground("MENUE_RIGHT"), gtest.actionChangeColor(Color.RED));

        allObjects = new ArrayList<GameObject>();
        for (int i = 0; i < go.length; i++)
        {
            allObjects.add(go[i]);
        }
        isInitiated = true;
        return go;
    }

    public void calculatePhysics()
    {
        allObjects.forEach((obj) -> {
            if (obj != null)
            {
                obj.calculatePhysics();
            }
        });

    }

    public ButtonWrapper[] getCurrentAbilities()
    {
        return currentAbilities;
    }
}
