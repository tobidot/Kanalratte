package game.objects;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import model.GameModel;
import model.Model;

/**
 * von GameObject leiten alle Objekte die direkt einfluss auf das Spiel haben ab
 * und auch nur diese k�nnen im Gamescreen erscheinen
 * 
 * @author Tobi
 *
 */
public abstract class GameObject
{
    protected Node visual;

    private long bufferedNanosecs;

    protected long oldNanosecs;

    public GameObject()
    {
    }

    /**
     * f�gt dieses Objekt dem Spiel-Bildschirm hinzu
     * 
     * @param screen
     *            Node in dem das Spiel gezeigt wird
     */
    public void addToScreen(Pane screen)
    {
        oldNanosecs = System.nanoTime();
        bufferedNanosecs = oldNanosecs;
        screen.getChildren().add(visual);
    }

    /**
     * Initialisiert dieses Objekt neu diese Methode sollte immer �berschrieben
     * werden und mit dem jeweiligen Daten gef�llt
     * 
     * Im normalfall sollte die SUPER-methode aufgerufen werden
     * 
     * @param m
     *            ZUgriff auf das Model, um Resourcen zu laden
     * @param gm
     *            Zugriff auf das SpielModel, um es zu beeinfl�ssen, mglweise
     *            nicht n�tig
     * @param param
     *            extra Parameter f�r eine besondere Initialisierung
     */
    public void init(Model m, GameModel gm, String param)
    {
        visual.translateXProperty().bind(gm.getCameraX());
        visual.translateYProperty().bind(gm.getCameraY());

    }

    /**
     * Verhalten dieses Objekts in einem gewissen Interval<br>
     * (Gravitation , Bewwegungsbefehl ...)
     * 
     * Die supermethode muss aufgerufen werden
     */
    public void calculatePhysics(long nanosecs)
    {
        oldNanosecs = bufferedNanosecs;
        bufferedNanosecs = nanosecs;
    }

}
