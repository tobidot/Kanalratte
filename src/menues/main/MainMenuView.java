package menues.main;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
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
        root.getChildren().add(new Label("MainMenu"));
        /// Oberste Struktur
        root.getChildren().add(headMenue = new HBox());
        root.getChildren().add(bodyMenue = new HBox());
        root.getChildren().add(footer = new Pane());
        headMenue.prefHeight(10000);
        bodyMenue.prefHeight(50000);
        footer.prefHeight(5000);
        /// Body
        bodyMenue.getChildren().add(leftImage = new Rectangle());
        bodyMenue.getChildren().add(optionsContainer = new VBox(20));
        bodyMenue.getChildren().add(rightImage = new Rectangle());
        leftImage.prefWidth(10000);
        optionsContainer.prefWidth(8000);
        rightImage.prefWidth(10000);
        ///

        /// Testen
        optionsContainer.getChildren().add(new Button("TestOption"));
        optionsContainer.getChildren().add(new Button("TestOption2"));
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
            leftImage.setFill(images[1]);
    }
}
