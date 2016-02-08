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

    @SuppressWarnings("unused")
    private Stage stage;

    private SimpleDoubleProperty resolutionHeight;

    private SimpleDoubleProperty resolutionWidth;

    protected R presenter;

    public View()
    {
        resolutionHeight = new SimpleDoubleProperty();
        resolutionWidth = new SimpleDoubleProperty();
    }

    public T getUI()
    {
        return root;
    }

    protected ReadOnlyDoubleProperty getResolutionWidth()
    {
        return resolutionWidth;
    }

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
        this.stage = stage;
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

    public void setPresenter(R p)
    {
        this.presenter = p;
    }

    protected abstract void setImages(Model model);

}
