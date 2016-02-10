package game.objects;

import javafx.scene.Node;
import javafx.scene.shape.Rectangle;

public abstract class GameObject
{
    protected Node visual;

    public Node getVisual()
    {
        return visual;
    }

    public void calculatePhysics()
    {

    }

}
