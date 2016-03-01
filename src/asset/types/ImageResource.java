package asset.types;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.ImagePattern;

/**
 * ImageResource wird genutzt um einfache Bilder zu verwenden
 * 
 * @author Tobi
 *
 */
public class ImageResource extends ResourceType
{

    private ImagePattern imgPattern;

    private Background imgBack;

    /**
     * lädt das Bild
     * 
     * @param p
     *            URL-PFad des Bildes
     */
    public ImageResource(String p)
    {
        super(p);
        resource = new Image(path);

        imgPattern = null;
        imgBack = null;
    }

    /**
     * 
     * @return gibt das geladene Bild zurück
     */
    public Image getImage()
    {
        return (Image) resource;
    }

    /**
     * erstellt ein Standard-ImagePattern bei der erstaufrufung und gibt dieses
     * dann zurück
     * 
     * @return standard ImagePattern
     */
    public ImagePattern getAsImagePattern()
    {
        if (imgPattern == null)
        {
            imgPattern = new ImagePattern((Image) resource, 0, 0, 1, 1, true);
        }
        return imgPattern;
    }

    /**
     * erstellt ein Background mit diesem Image bei der erstaufrufung und gibt
     * dieses dann zurück
     * 
     * @return den Background mit diesem Image
     */
    public Background getAsBackGround()
    {
        if (imgBack == null)
        {
            imgBack = new Background(new BackgroundFill(imgPattern, new CornerRadii(0), new Insets(0)));
        }
        return imgBack;
    }
}