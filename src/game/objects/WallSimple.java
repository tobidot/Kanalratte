package game.objects;

import game.mvp.GamePresenter;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import model.GameModel;
import model.Model;

/**
 * Eine einfache Wand abgeleitete KLassen sind
 * 
 * @see WallRiff
 *
 * 
 * 
 * @author Tobi
 *
 */
public class WallSimple extends GameObject
{
    public static final int WALLSIZE = 20;

    private Rectangle visual;

    public WallSimple(int x, int y)
    {
        super.visual = this.visual = new Rectangle();
        visual.setWidth(WALLSIZE);
        visual.setHeight(WALLSIZE);
        visual.setLayoutX(x * WALLSIZE);
        visual.setLayoutY(y * WALLSIZE);
        positionX.set(x * WALLSIZE);
        positionY.set(y * WALLSIZE);
        width.set(WALLSIZE);
        height.set(WALLSIZE);
    }

    @Override
    public void init(GamePresenter p, Model m, GameModel gm, String param)
    {
        super.init(p, m, gm, param);
        visual.setFill(m.getAssets().getImagePattern("MAP_MAUER_A"));
    }

    @Override
    public void calculatePhysics(long n)
    {
        super.calculatePhysics(n);
    }
}
