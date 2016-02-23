package game.objects;

import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import model.GameModel;
import model.Model;

public class BackgroundImage extends GameObject
{
    public Rectangle visual;

    @Override
    public void addToScreen(Pane screen)
    {
        super.addToScreen(screen);
        this.visual.heightProperty().bind(screen.heightProperty());
        this.visual.widthProperty().bind(screen.widthProperty());
        this.visual.toBack();
    }

    @Override
    public void init(Model m, GameModel gm, String param)
    {

    }

    public BackgroundImage(Model model, String bgImage)
    {
        super.visual = this.visual = new Rectangle();
        visual.setFill(new ImagePattern(model.getImage(bgImage)));
    }

}
