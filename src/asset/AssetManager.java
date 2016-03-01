package asset;

import java.util.HashMap;
import java.util.NoSuchElementException;

import asset.types.ButtonAnimationResource;
import asset.types.ImageResource;
import asset.types.ResourceType;
import game.gui.ButtonAnimation;
import javafx.scene.image.Image;

public class AssetManager
{
    /**
     * speichert alle Resourcen die geladen wurden
     */
    private HashMap<String, ResourceType> resources;

    /**
     * Die Resourcen werden im Konstruktor geladen
     */
    public AssetManager()
    {
        resources = new HashMap<String, ResourceType>();

        /// Test

        resources.put("TEST", new ImageResource("file:bilder/Test.png"));
        resources.put("ANIMATION_TEST", new ButtonAnimationResource("animationen/button/test.ani"));

        /// Men�bilder

        resources.put("MENUE_RIGHT", new ImageResource("file:bilder/menue_right.png"));
        resources.put("MENUE_OPTIONS_BACK", new ImageResource("file:bilder/options_background.png"));
        resources.put("MENUE_LEFT", new ImageResource("file:bilder/menue_left.png"));
        resources.put("MENUE_MORE_LEFT", new ImageResource("file:bilder/InGameAuswahl_Bl�ttern_left.png"));
        resources.put("MENUE_MORE_RIGHT", new ImageResource("file:bilder/InGameAuswahl_Bl�ttern_right.png"));
        resources.put("MENUE_INFO_NONE", new ImageResource("file:bilder/Info_NoSelect.png"));
        resources.put("MENUE_EXIT", new ImageResource("file:bilder/Exit_Button.png"));
        resources.put("ABILITY_A", new ImageResource("file:bilder/Abbility_Move.png"));
        resources.put("ABILITY_B", new ImageResource("file:bilder/Abbility_Move.png"));
        resources.put("ABILITY_C", new ImageResource("file:bilder/Abbility_Attack.png"));
        resources.put("ABILITY_D", new ImageResource("file:bilder/Abbility_Attack.png"));

        /// Map
        resources.put("MAP_BACKGROUND_A", new ImageResource("file:bilder/Map_Background.png"));
        resources.put("MAP_BACKGROUND_B", new ImageResource("file:bilder/Map_Background_B.png"));

        resources.put("MAP_WEG_A", new ImageResource("file:bilder/Weg/Weg_A.png"));
        resources.put("MAP_WEG_B", new ImageResource("file:bilder/Weg/Weg_B.png"));
        resources.put("MAP_WEG_C", new ImageResource("file:bilder/Weg/Weg_C.png"));
        resources.put("MAP_MAUER_A", new ImageResource("file:bilder/Mauer/Mauer_A.png"));

        resources.put("ANIMATION_MAP_KANAL_A", new ButtonAnimationResource("animationen/map/Kanal_A.ani"));

    }

    /**
     * G�bt die geladene Resource unver�ndert zur�ck
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
     * G�bt die geladene Resource unver�ndert zur�ck
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
     * Gibt den Speicherpfad einer Resource zur�ck
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
     * Gibt das gespeicherte Objekt in der Resource zur�ck
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
     * Gibt den Inhalt der Resource zur�ck und checkt ob es sich wirklcih um
     * eine ImageResource handelt
     * 
     * @param key
     *            Name der resource
     * @return Bild aus der Resource
     */
    public Image getImage(String key)
    {
        ResourceType rt = getResource(key);
        if (rt instanceof ImageResource == false)
        {
            throw new NoSuchElementException("Resource : '" + key + "' ist kein ImageResource");
        }
        else
        {
            return ((ImageResource) rt).getImage();
        }
    }

    /**
     * 
     * Gibt den Inhalt der Resource zur�ck und checkt ob es sich wirklich um
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

}
