package asset;

import java.util.HashMap;

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
        resources.put("MENUE_LEFT", new ImageResource("file:bilder/menue_left.png"));
    }

    public String getResourcePath(String key)
    {
        ResourceType rt = resources.get(key);
        if (rt == null)
        {
            return null;
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
            return null;
        }
        else
        {
            return rt.getResource();
        }
    }

    public Image getImage(String key)
    {
        ImageResource rt = (ImageResource) resources.get(key);
        if (rt == null)
        {
            return null;
        }
        else
        {
            return rt.getImage();
        }
    }
}
