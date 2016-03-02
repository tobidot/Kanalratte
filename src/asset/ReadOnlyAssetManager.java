package asset;

import java.util.HashMap;
import java.util.NoSuchElementException;

import asset.types.ButtonAnimationResource;
import asset.types.ImageResource;
import asset.types.ResourceType;
import game.gui.ButtonAnimation;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.paint.ImagePattern;

/**
 * Diese Klasse erlaubt den lesenden zugriff auf die Resourcen
 * 
 * @author Tobi
 *
 */
public abstract class ReadOnlyAssetManager
{

    /**
     * speichert alle Resourcen die geladen wurden
     */
    protected HashMap<String, ResourceType> resources;

    /**
     * Gíbt die geladene Resource unverändert zurück
     * 
     * @param key
     *            Name der Resource
     * @return geladene Resource
     */
    public ResourceType getResource(String key)
    {
        key = key.toUpperCase();
        ResourceType rt = resources.get(key);
        if (rt == null)
        {
            throw new NoSuchElementException("Resource : '" + key + "' ist nicht vorhanden");
        }
        else
        {
            return rt;
        }
    }

    /**
     * Gíbt die geladene Resource unverändert zurück
     * 
     * @param key
     *            Name der Resource
     * @return geladene Resource
     */
    public <T extends ResourceType> T getResource(String key, Class<T> c)
    {
        ResourceType rt = getResource(key);
        if (c.isInstance(rt))
        {
            return c.cast(rt);
        }
        else
        {
            throw new NoSuchElementException("Resource : '" + key + "' ist keine " + c.getSimpleName());
        }
    }

    /**
     * Gibt den Speicherpfad einer Resource zurück
     * 
     * @param key
     *            Bezeichner der Resource
     * @return String mit URL Pfad
     */
    public String getResourcePath(String key)
    {
        ResourceType rt = getResource(key);
        return rt.getPath();
    }

    /**
     * Gibt das gespeicherte Objekt in der Resource zurück
     * 
     * @param key
     *            Name der Resource
     * @return Die Resource ungekapselt
     */
    public Object getResourceData(String key)
    {
        ResourceType rt = getResource(key);
        return rt.getResource();
    }

    /**
     * Gibt den Inhalt der Resource zurück und checkt ob es sich wirklcih um
     * eine ImageResource handelt
     * 
     * @param key
     *            Name der resource
     * @return Bild aus der Resource
     */
    public ImagePattern getImagePattern(String key)
    {
        ResourceType rt = getResource(key);
        if (rt instanceof ImageResource == false)
        {
            throw new NoSuchElementException("Resource : '" + key + "' ist kein ImageResource");
        }
        else
        {
            return ((ImageResource) rt).getAsImagePattern();
        }
    }

    /**
     * Gibt den Inhalt der Resource als Background zurück und checkt ob es sich
     * wirklcih um eine ImageResource handelt
     * 
     * @param key
     *            Name der resource
     * @return Background aus der Resource
     */
    public Background getBackgroundImage(String key)
    {
        System.out.println(key);
        ResourceType rt = getResource(key);
        if (rt instanceof ImageResource == false)
        {
            throw new NoSuchElementException("Resource : '" + key + "' ist kein ImageResource");
        }
        else
        {
            System.out.println("Data : " + ((ImageResource) rt).getAsBackGround());
            return ((ImageResource) rt).getAsBackGround();
        }
    }

    /**
     * 
     * Gibt den Inhalt der Resource zurück und checkt ob es sich wirklich um
     * eine ButtonAnimation handelt
     * 
     * @param key
     *            Name der resource
     * @return Animation aus der Resource
     */
    public ButtonAnimation getButtonAnimation(String key)
    {
        ResourceType rt = resources.get(key);
        if (rt instanceof ButtonAnimationResource == false)
        {
            throw new NoSuchElementException("Resource : '" + key + "' ist keine ButtonAnimationResource");
        }
        else
        {
            return ((ButtonAnimationResource) rt).getButtonAnimation();
        }
    }

    /**
     * Gibt eine Resource anhand ihred URL-Pfades zurück
     * 
     * @param path
     *            URL-Pfad
     * @return ResourceType kapsel
     */
    public <T extends ResourceType> T getResourceByPath(String path, Class<T> c)
    {
        ResourceType[] rt = resources.values().toArray(rt = new ResourceType[resources.size()]);
        for (ResourceType r : rt)
        {
            if (r.getPath().equals(path))
            {
                if (c.isInstance(r))
                {
                    return c.cast(r);
                }
                else
                {
                    throw new NoSuchElementException("Resource : '" + path + "' ist keine " + c.getSimpleName());
                }
            }
        }
        return null;
    }

}
