package mvp;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.Model;

public class MainView extends View<VBox, MainPresenter>
{

    private Pane embededPane;

    public MainView()
    {
        root = new VBox();
        root.setMinWidth(400);
        root.setMinHeight(300);
        root.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, new CornerRadii(0), new Insets(0))));

        embededPane = null;
    }

    public void embed(Pane ui)
    {
        if (embededPane != null)
        {
            root.getChildren().remove(embededPane);
        }
        embededPane = ui;
        root.getChildren().add(embededPane);
    }

    @Override
    protected void setImages(Model model)
    {
        // TODO Auto-generated method stub

    }

}
