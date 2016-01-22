package menues.main;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
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
}
