package game.objects;

import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import model.Model;

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
