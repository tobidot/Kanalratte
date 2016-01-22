package asset;

import java.util.HashMap;

public class AssetManager
{

    public static abstract class ResourceType
    {
        private String path;

        ResourceType(String p)
        {
            path = p;
        }

        public String getPath()
        {
            return path;
        }
    }

    public static class ImageResource extends ResourceType
    {

        ImageResource(String p)
        {
            super(p);
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
}
