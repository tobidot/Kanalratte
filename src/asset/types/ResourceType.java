package asset.types;

public abstract class ResourceType
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