package menues.base;

import javax.swing.GroupLayout.Alignment;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
import javafx.scene.text.Font;
import mvp.Presenter;
import mvp.View;

public class MenuBaseView extends View<Pane, MenuBasePresenter>
{
    private HBox headMenue;

    private VBox optionsContainer;

    private SimpleObjectProperty<Background> optionsBackground;

    public MenuBaseView()
    {
        root = new Pane();
        root.setPrefHeight(Integer.MAX_VALUE);
        root.setBackground(new Background(new BackgroundFill(Color.WHEAT, new CornerRadii(0), new Insets(0))));
        root.maxHeightProperty().bind(getResolutionHeight());
        /// Oberste Struktur
        root.getChildren().add(headMenue = new HBox());
        root.getChildren().add(optionsContainer = new VBox(20));
        headMenue.setPrefHeight(200);
        optionsContainer.layoutXProperty().bind(getResolutionWidth().multiply(0.35));
        optionsContainer.layoutYProperty().bind(getResolutionHeight().multiply(0.1));
        optionsContainer.prefHeightProperty().bind(getResolutionHeight().multiply(0.7));
        optionsContainer.prefWidthProperty().bind(getResolutionWidth().multiply(0.3));
        optionsContainer.setAlignment(Pos.TOP_CENTER);
        optionsContainer.setPadding(new Insets(50, 25, 20, 15));
        optionsContainer.spacingProperty().bind(getResolutionHeight().multiply(0.025));

        /// Buttons
        optionsBackground = new SimpleObjectProperty<Background>();
        optionsBackground.set(new Background(new BackgroundFill(Color.RED, new CornerRadii(0), new Insets(0))));
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
            optionsContainer.setBackground(new Background(new BackgroundFill(images[1], new CornerRadii(0), new Insets(0))));
            root.setBackground(new Background(new BackgroundFill(images[2], new CornerRadii(0), new Insets(0))));
        }
    }

    public void setMenueOptions(String... strings)
    {
        optionsContainer.getChildren().clear();
        if (strings != null)
        {
            for (int i = 0; i < strings.length; i++)
            {
                Button b;
                optionsContainer.getChildren().add(b = new Button());
                b.maxWidthProperty().bind(getResolutionWidth().multiply(0.3));
                b.setBackground(optionsBackground.get());
                b.setTextFill(Color.ALICEBLUE);
                b.setFont(new Font("console", 18));
                b.setVisible(true);
                b.setText(strings[i]);
                final int index = i;
                b.setOnAction(e -> onOptionPressed(index));
            }
        }
    }

    private void onOptionPressed(int index)
    {
        presenter.onOptionPressed(index);
    }

}
