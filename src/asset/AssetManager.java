package asset;

import java.util.HashMap;
import java.util.NoSuchElementException;

import game.gui.ButtonAnimation;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.ImagePattern;

public class AssetManager
{

    public static abstract class ResourceType
    {
        protected String path;

        protected Object resource;

        public ResourceType(String p)
        {
            path = p;
            resource = null;
        }

        public String getPath()
        {
            return path;
        }

        public Object getResource()
        {
            return resource;
        }
    }

    public static class ImageResource extends ResourceType
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

    private HashMap<String, ResourceType> resources;

    public AssetManager()
    {
        resources = new HashMap<String, ResourceType>();
        resources.put("TEST", new ImageResource("file:bilder/Test.png"));
        resources.put("MENUE_RIGHT", new ImageResource("file:bilder/menue_right.png"));
        resources.put("MENUE_OPTIONS_BACK", new ImageResource("file:bilder/options_background.png"));
        resources.put("MENUE_LEFT", new ImageResource("file:bilder/menue_left.png"));
        resources.put("MENUE_MORE_LEFT", new ImageResource("file:bilder/InGameAuswahl_Bl�ttern_left.png"));
        resources.put("MENUE_MORE_RIGHT", new ImageResource("file:bilder/InGameAuswahl_Bl�ttern_right.png"));
        resources.put("MENUE_INFO_NONE", new ImageResource("file:bilder/Info_NoSelect.png"));
        resources.put("MENUE_EXIT", new ImageResource("file:bilder/Exit_Button.png"));

        resources.put("MAP_BACKGROUND_A", new ImageResource("file:bilder/Map_Background.png"));
        resources.put("MAP_BACKGROUND_B", new ImageResource("file:bilder/Map_Background_B.png"));

        resources.put("MAP_MAUER_A", new ImageResource("file:bilder/Mauer/Mauer_A.png"));

        resources.put("ABILITY_A", new ImageResource("file:bilder/Abbility_Move.png"));
        resources.put("ABILITY_B", new ImageResource("file:bilder/Abbility_Move.png"));
        resources.put("ABILITY_C", new ImageResource("file:bilder/Abbility_Attack.png"));
        resources.put("ABILITY_D", new ImageResource("file:bilder/Abbility_Attack.png"));

        resources.put("ANIMATION_TEST", new ButtonAnimationResource("animationen/button/test.ani"));
        resources.put("ANIMATION_MAP_KANAL_A", new ButtonAnimationResource("animationen/map/Kanal_A.ani"));
    }

    public String getResourcePath(String key)
    {
        ResourceType rt = resources.get(key);
        if (rt == null)
        {
            throw new NoSuchElementException("Resource : '" + key + "' ist nicht vorhanden");
        }
        else
        {
            return rt.getPath();
        }
    }

    public Object getResource(String key)
    {
        ResourceType rt = resources.get(key);
        if (rt == null)
        {
            throw new NoSuchElementException("Resource : '" + key + "' ist nicht vorhanden");
        }
        else
        {
            return rt.getResource();
        }
    }

    public Image getImage(String key)
    {
        ResourceType rt = resources.get(key);
        if (rt == null)
        {
            throw new NoSuchElementException("Resource : '" + key + "' ist nicht vorhanden");
        }
        else if (rt instanceof ImageResource == false)
        {
            throw new NoSuchElementException("Resource : '" + key + "' ist kein Image");
        }
        else
        {
            return ((ImageResource) rt).getImage();
        }
    }

    public ButtonAnimation getButtonAnimation(String key)
    {
        ResourceType rt = resources.get(key);
        if (rt == null)
        {
            throw new NoSuchElementException("Resource : '" + key + "' ist nicht vorhanden");
        }
        else if (rt instanceof ButtonAnimationResource == false)
        {
            throw new NoSuchElementException("Resource : '" + key + "' ist keine Button-Animation");
        }
        else
        {
            return ((ButtonAnimationResource) rt).getButtonAnimation();
        }
    }

}
