package loadscreen;

import asset.AssetManager;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Model;
import mvp.View;

public class LoadView extends View<Pane, LoadingScreenPresenter>
{

    private Rectangle loadImage;

    private Label loadUpdate;

    private ProgressBar loadUpdateBar;

    private Stage loadWindow;

    public LoadView()
    {
        root = new Pane();
        root.getChildren().add(loadImage = new Rectangle());
        loadImage.setFill(Color.DARKGRAY);
        loadImage.setWidth(400);
        loadImage.setHeight(300);
        loadImage.setArcHeight(80);
        loadImage.setArcWidth(200);
        root.getChildren().add(loadUpdate = new Label("loading"));
        loadUpdate.layoutYProperty().bind(loadImage.heightProperty().add(-45));
        loadUpdate.setFont(Font.font(16));
        loadUpdate.setTextFill(Color.DARKBLUE);
        root.getChildren().add(loadUpdateBar = new ProgressBar());
        loadUpdateBar.layoutYProperty().bind(loadImage.heightProperty().add(-20));
        loadUpdateBar.setMaxWidth(Integer.MAX_VALUE);
        loadUpdateBar.prefWidthProperty().bind(loadImage.widthProperty());
    }

    public void show()
    {
        loadWindow = new Stage();
        loadWindow.setWidth(400);
        loadWindow.setHeight(300);
        loadWindow.initStyle(StageStyle.TRANSPARENT);
        Pane pane = this.getUI();
        pane.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, new CornerRadii(0), new Insets(0))));

        Scene scene = new Scene(pane);
        scene.setFill(Color.TRANSPARENT);
        loadWindow.setScene(scene);
        loadWindow.show();
    }

    public void hide()
    {
        loadWindow.close();
    }

    public void setImage(ImagePattern image)
    {
        if (image != null)
        {
            loadImage.setFill(image);
        }
    }

    public void setProgress(double percent, String out)
    {
        loadUpdate.setText(out + " : (" + String.format("%.1f", percent * 100) + "%) ");
        loadUpdateBar.setProgress(percent);
    }

    @Override
    protected void setImages(Model model)
    {

    }
}
