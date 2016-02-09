package game.objects;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GameObjectTest
{
    private Rectangle visual;

    public Node getVisual()
    {
        return visual;
    }

    public GameObjectTest()
    {
        visual = new Rectangle();
        visual.setWidth(20);
        visual.setHeight(20);
        visual.setFill(Color.BROWN);
    }
}
