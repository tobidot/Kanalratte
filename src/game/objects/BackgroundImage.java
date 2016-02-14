package game.objects;

import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import model.Model;

public class BackgroundImage extends GameObject
{
    public Rectangle visual;

    public BackgroundImage(Model model, String bgImage)
    {
        super.visual = this.visual = new Rectangle();
        visual.setFill(new ImagePattern(model.getImage(bgImage)));
        visual.setHeight(200);
        visual.setWidth(200);
    }

}
