package menues.main;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import mvp.View;

public class MainMenuView extends View<VBox>
{
    private HBox headMenue;

    private HBox bodyMenue;

    private Pane footer;

    private VBox optionsContainer;

    private Rectangle leftImage;

    private Rectangle rightImage;

    public MainMenuView()
    {
        root = new VBox();
        root.setPrefHeight(Integer.MAX_VALUE);
        root.getChildren().add(new Label("MainMenu"));
        root.setBackground(new Background(new BackgroundFill(Color.WHEAT, new CornerRadii(0), new Insets(0))));
        /// Oberste Struktur
        root.getChildren().add(headMenue = new HBox());
        root.getChildren().add(bodyMenue = new HBox());
        root.getChildren().add(footer = new Pane());
        headMenue.setPrefHeight(1000);
        bodyMenue.setPrefHeight(1000);
        footer.setPrefHeight(800);
        /// Body
        bodyMenue.getChildren().add(leftImage = new Rectangle());
        bodyMenue.getChildren().add(optionsContainer = new VBox(20));
        bodyMenue.getChildren().add(rightImage = new Rectangle());

        leftImage.widthProperty().bind(this.getResolutionWidth().multiply(0.35));
        leftImage.heightProperty().bind(optionsContainer.heightProperty());
        rightImage.widthProperty().bind(this.getResolutionWidth().multiply(0.35));
        rightImage.heightProperty().bind(optionsContainer.heightProperty());

        leftImage.setFill(Color.BLACK);
        optionsContainer.setBackground(new Background(new BackgroundFill(Color.WHEAT, new CornerRadii(0), new Insets(0))));
        rightImage.setFill(Color.BLACK);
        ///

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
     * <li>1 - links von der Optionen Wahl
     * <li>2 - rechts von der OPtionen Wahl
     * <li>3 - hinter den Optionen
     * <li>4 - am unteren rand "Footer"
     * <ul>
     * 
     * @param images
     *            Liste an Images die zum Menü gehören
     */
    public void setImageSet(ImagePattern... images)
    {
        if (images == null || images.length < 5)
        {
            leftImage.setFill(images[1]);
            rightImage.setFill(images[2]);
        }
    }

}
