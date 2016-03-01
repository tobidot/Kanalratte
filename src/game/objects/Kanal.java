package game.objects;

import game.mvp.GamePresenter;
import javafx.scene.paint.Color;
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
public class Kanal extends GameObject
{

    private Rectangle visual;

    public Kanal(double x, double y)
    {
        super.visual = this.visual = new Rectangle();
        visual.setWidth(WallSimple.WALLSIZE);
        visual.setHeight(WallSimple.WALLSIZE);
        visual.setLayoutX(x * WallSimple.WALLSIZE);
        visual.setLayoutY(y * WallSimple.WALLSIZE);
        positionX.set(x * WallSimple.WALLSIZE);
        positionY.set(y * WallSimple.WALLSIZE);
        width.set(WallSimple.WALLSIZE);
        height.set(WallSimple.WALLSIZE);
    }

    @Override
    public void init(GamePresenter p, Model m, GameModel gm, String param)
    {
        super.init(p, m, gm, param);
        // visual.setFill(new ImagePattern(m.getImage("TEST")));
        visual.fillProperty().bind(m.getButtonAnimation("ANIMATION_MAP_KANAL_A").currentImagePattern());
    }

    @Override
    public void calculatePhysics(long n)
    {
        super.calculatePhysics(n);
    }
}
