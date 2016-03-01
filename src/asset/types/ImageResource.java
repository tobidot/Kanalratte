package asset.types;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.ImagePattern;

public class ImageResource extends ResourceType
{
    private ImagePattern imgPattern;

    public ImageResource(String p)
    {
        super(p);
        resource = new Image(path);
        imgPattern = new ImagePattern((Image) resource, 0, 0, 1, 1, true);
    }

    public Image getImage()
    {
        return (Image) resource;
    }

    public ImagePattern getAsImagePattern()
    {
        return imgPattern;
    }

    public Background getAsBackGround()
    {
        return new Background(new BackgroundFill(imgPattern, new CornerRadii(0), new Insets(0)));
    }
}