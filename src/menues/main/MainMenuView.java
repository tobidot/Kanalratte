package menues.main;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import mvp.View;

public class MainMenuView extends View<Pane>
{
    private HBox headMenue;

    private VBox optionsContainer;

    public MainMenuView()
    {
        root = new Pane();
        root.setPrefHeight(Integer.MAX_VALUE);
        root.getChildren().add(new Label("MainMenu"));
        root.setBackground(new Background(new BackgroundFill(Color.WHEAT, new CornerRadii(0), new Insets(0))));
        root.maxHeightProperty().bind(getResolutionHeight());
        /// Oberste Struktur
        root.getChildren().add(headMenue = new HBox());
        root.getChildren().add(optionsContainer = new VBox(20));
        headMenue.setPrefHeight(200);
        optionsContainer.layoutXProperty().bind(getResolutionWidth().multiply(0.35));
        optionsContainer.layoutYProperty().bind(getResolutionHeight().multiply(0.2));

        /// Testen
        Button b;
        optionsContainer.getChildren().add(new Button("TestOption"));
        optionsContainer.getChildren().add(b = new Button("TestOption2"));
        b.setPrefWidth(200);
    }

    /**
     * Position im Array - Erscheinen im Menü
     * <ul>
     * <li>0 - Oberste Zeile "Header Bild"
     * <li>1 - hinter den Optionen
     * <li>2 - Bild hinter dem ganzem Menü
     * <ul>
     * 
     * @param images
     *            Liste an Images die zum Menü gehören
     */
    public void setImageSet(ImagePattern... images)
    {
        if (images == null || images.length < 3)
        {
            return;
        }
        else
        {
            // leftImage.setFill(images[1]);
            // rightImage.setFill(images[2]);
            headMenue.setBackground(new Background(new BackgroundFill(images[0], new CornerRadii(0), new Insets(0))));
            root.setBackground(new Background(new BackgroundFill(images[2], new CornerRadii(0), new Insets(0))));
        }
    }

}
