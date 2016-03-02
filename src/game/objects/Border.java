package game.objects;

import game.mvp.GamePresenter;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
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
public class Border extends GameObject
{
    public static final int WALLSIZE = 20;

    private Rectangle visual;

    public Border(int x, int y)
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
        ImagePattern pattern = m.getAssets().getImagePattern("MAP_WEG_" + (char) ('A' + (int) (Math.random() * 3)));
        visual.setFill(pattern);
        profileImage.set(pattern);
    }

    @Override
    public void calculatePhysics(long n)
    {
        super.calculatePhysics(n);
    }

}
