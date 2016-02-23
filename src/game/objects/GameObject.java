package game.objects;

import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import model.GameModel;
import model.Model;

/**
 * von GameObject leiten alle Objekte die direkt einfluss auf das Spiel haben ab
 * und auch nur diese können im Gamescreen erscheinen
 * 
 * @author Tobi
 *
 */
public abstract class GameObject
{
    protected Node visual;

    protected SimpleDoubleProperty positionX;

    protected SimpleDoubleProperty positionY;

    protected SimpleDoubleProperty width;

    protected SimpleDoubleProperty height;

    private long bufferedNanosecs;

    protected long oldNanosecs;

    public GameObject()
    {
        positionX = new SimpleDoubleProperty();
        positionY = new SimpleDoubleProperty();
        width = new SimpleDoubleProperty();
        height = new SimpleDoubleProperty();
    }

    public ReadOnlyDoubleProperty getX()
    {
        return positionX;
    }

    public ReadOnlyDoubleProperty getY()
    {
        return positionY;
    }

    /**
     * fügt dieses Objekt dem Spiel-Bildschirm hinzu
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
     * Initialisiert dieses Objekt neu diese Methode sollte immer überschrieben
     * werden und mit dem jeweiligen Daten gefüllt
     * 
     * Im normalfall sollte die SUPER-methode aufgerufen werden
     * 
     * @param m
     *            ZUgriff auf das Model, um Resourcen zu laden
     * @param gm
     *            Zugriff auf das SpielModel, um es zu beeinflüssen, mglweise
     *            nicht nötig
     * @param param
     *            extra Parameter für eine besondere Initialisierung
     */
    public void init(Model m, GameModel gm, String param)
    {

        visual.translateXProperty().bind(gm.getCameraX().add(gm.getCameraZoom().multiply(width).multiply(0.5)));
        visual.translateYProperty().bind(gm.getCameraY().add(gm.getCameraZoom().multiply(height).multiply(0.5)));
        visual.scaleXProperty().bind(gm.getCameraZoom());
        visual.scaleYProperty().bind(gm.getCameraZoom());
        visual.layoutXProperty().bind(positionX.multiply(gm.getCameraZoom()));
        visual.layoutYProperty().bind(positionY.multiply(gm.getCameraZoom()));

    }

    /**
     * Verhalten dieses Objekts in einem gewissen Interval<br>
     * (Gravitation , Bewegungsbefehl ...)
     * 
     * Die supermethode muss aufgerufen werden
     */
    public void calculatePhysics(long nanosecs)
    {
        oldNanosecs = bufferedNanosecs;
        bufferedNanosecs = nanosecs;
    }

}
