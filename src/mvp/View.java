package mvp;

import javafx.scene.layout.Pane;

public abstract class View<T extends Pane>
{
    protected T root;

    public T getUI()
    {
        return root;
    }

}
