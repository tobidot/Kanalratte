package asset;

import java.util.HashMap;
import java.util.NoSuchElementException;

import javafx.scene.image.Image;

public class AssetManager
{

    public static abstract class ResourceType
    {
        protected String path;

        protected Object resource;

        ResourceType(String p)
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

        ImageResource(String p)
        {
            super(p);
            resource = new Image(path);
        }

        public Image getImage()
        {
            return (Image) resource;
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
}
