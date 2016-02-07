package asset;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.CharBuffer;
import java.util.ArrayList;
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
        public ImageResource(String p)
        {
            super(p);
            resource = new Image(path);
        }

        public Image getImage()
        {
            return (Image) resource;
        }

        public Background getAsBackGround()
        {
            return new Background(new BackgroundFill(new ImagePattern((Image) resource, 0, 0, 1, 1, true), new CornerRadii(0), new Insets(0)));
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
        resources.put("MENUE_MORE_LEFT", new ImageResource("file:bilder/InGameAuswahl_Blättern_left.png"));
        resources.put("MENUE_MORE_RIGHT", new ImageResource("file:bilder/InGameAuswahl_Blättern_right.png"));
        resources.put("MENUE_INFO_NONE", new ImageResource("file:bilder/Info_NoSelect.png"));
        resources.put("MENUE_EXIT", new ImageResource("file:bilder/Exit_Button.png"));

        resources.put("ABILITY_A", new ImageResource("file:bilder/Abbility_Move.png"));
        resources.put("ABILITY_B", new ImageResource("file:bilder/Abbility_Move.png"));
        resources.put("ABILITY_C", new ImageResource("file:bilder/Abbility_Attack.png"));
        resources.put("ABILITY_D", new ImageResource("file:bilder/Abbility_Attack.png"));

        resources.put("ANIMATION_TEST", new ButtonAnimationResource("animationen/button/test.ani"));
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
