package game.objects;

import game.gui.EventAction;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GameObjectTest extends GameObject
{
    private double speed;

    private Rectangle visual;

    public GameObjectTest()
    {
        super.visual = this.visual = new Rectangle();
        visual.setWidth(20);
        visual.setHeight(20);
        visual.setFill(Color.BROWN);
        speed = 0.1;
    }

    @Override
    public void physicsDoing(long n)
    {
        super.calculatePhysics(n);
        visual.setLayoutX(visual.getLayoutX() + speed);
    }

    public EventAction actionChangeColor(Color c)
    {
        return new EventAction()
        {
            @Override
            public void trigger()
            {
                visual.setFill(c);
            }
        };
    };

}
