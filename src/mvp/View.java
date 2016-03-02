package mvp;

import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Model;

@SuppressWarnings("rawtypes")
public abstract class View<T extends Pane, R extends Presenter>
{
    protected T root;

    private SimpleDoubleProperty resolutionHeight;

    private SimpleDoubleProperty resolutionWidth;

    protected R presenter;

    public View()
    {
        resolutionHeight = new SimpleDoubleProperty();
        resolutionWidth = new SimpleDoubleProperty();
    }

    /**
     * 
     * @return gibt daas root-element dieser View zurück
     */
    public T getUI()
    {
        return root;
    }

    /**
     * 
     * @return the width of this Window
     */
    protected ReadOnlyDoubleProperty getResolutionWidth()
    {
        return resolutionWidth;
    }

    /**
     * 
     * @return height of this Window
     */
    protected ReadOnlyDoubleProperty getResolutionHeight()
    {
        return resolutionHeight;
    }

    /**
     * resolution bind to scene if possible otherwise to the stage
     * 
     * @param stage
     *            stage wich holds the scene
     */
    public void setStage(Stage stage)
    {
        resolutionHeight.bind(stage.heightProperty());
        resolutionWidth.bind(stage.widthProperty());
        stage.sceneProperty().addListener((src, o, n) -> {
            if (n != null)
            {
                resolutionWidth.bind(n.widthProperty());
                resolutionHeight.bind(n.heightProperty());
            }
            else
            {
                resolutionHeight.bind(stage.heightProperty());
                resolutionWidth.bind(stage.widthProperty());
            }
        });
    }

    /**
     * setzt den Presenter, der diese View verwaltet
     * 
     * @param p
     *            Presenter der zu dieser View gehört
     */
    public void setPresenter(R p)
    {
        this.presenter = p;
    }

    /**
     * wird aufgerufen, um Bilder nachzuladen, die zur erstellungszeit dieser
     * View noch nicht vorhanden sind
     * 
     * @param model
     *            model das die Bilder enthält
     */
    protected abstract void setImages(Model model);

}
