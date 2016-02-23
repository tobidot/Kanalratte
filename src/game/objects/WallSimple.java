package game.objects;

import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import model.GameModel;
import model.Model;

public class WallSimple extends GameObject
{
    public static final int WALLSIZE = 16;

    private Rectangle visual;

    public WallSimple(int x, int y)
    {
        super.visual = this.visual = new Rectangle();
        visual.setWidth(WALLSIZE);
        visual.setHeight(WALLSIZE);
        visual.setLayoutX(x * WALLSIZE);
        visual.setLayoutY(y * WALLSIZE);
    }

    @Override
    public void init(Model m, GameModel gm, String param)
    {
        super.init(m, gm, param);
        visual.setFill(new ImagePattern(m.getImage("TEST")));
    }

    @Override
    public void calculatePhysics(long n)
    {
        super.calculatePhysics(n);
    }
}
