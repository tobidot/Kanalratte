package mvp;

import javafx.beans.InvalidationListener;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public abstract class View<T extends Pane>
{
    protected T root;

    private Stage stage;

    private SimpleDoubleProperty resolutionHeight;

    private SimpleDoubleProperty resolutionWidth;

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

    public void setStage(Stage stage)
    {
        this.stage = stage;
        resolutionHeight.bind(stage.heightProperty());
        resolutionWidth.bind(stage.widthProperty());
    }

}
