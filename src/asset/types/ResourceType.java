package asset.types;

/**
 * Grundklasse für alle ResourcenKlassen
 * 
 * 
 * @author Tobi
 *
 */
public abstract class ResourceType
{
    /**
     * URL-Pfad an dem die Resource sich befindet
     */
    protected String path;

    /**
     * Zeiger auf das geladene Objekt
     */
    protected Object resource;

    /**
     * speichert den Pfad
     * 
     * @param p
     *            URL-Pfad der Resource
     */
    public ResourceType(String p)
    {
        path = p;
        resource = null;
    }

    /**
     * 
     * @return URL-Pfad der Resource
     */
    public String getPath()
    {
        return path;
    }

    /**
     * 
     * @return die geladene Resource
     */
    public Object getResource()
    {
        return resource;
    }
}