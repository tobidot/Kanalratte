package game.mvp;

import java.util.ArrayList;
import java.util.Observable;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ObservableDoubleValue;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import model.Model;
import mvp.View;

public class GameView extends View<HBox, GamePresenter>
{
    private GridPane statusInfo;

    private Pane gameScreen;

    private GridPane gameMenu;

    private Button exitButton;

    private ArrayList<Button> gameMenuButton;

    public GameView()
    {
        VBox left;
        root = new HBox();
        /// main Layout
        root.getChildren().add(left = new VBox());
        root.getChildren().add(gameMenu = new GridPane());
        root.maxHeightProperty().bind(getResolutionHeight());
        root.setFillHeight(true);

        /// left / right
        left.prefWidthProperty().bind(this.getResolutionWidth().multiply(0.75));
        gameMenu.prefWidthProperty().bind(this.getResolutionWidth().multiply(0.25));

        /// ingameScreen Layout
        left.getChildren().add(gameScreen = new Pane());
        left.getChildren().add(statusInfo = new GridPane());
        // TODO apply Layout

        /// GameMenu Layout
        /// GridPane
        DoubleBinding perc10 = getResolutionHeight().multiply(0.1);
        DoubleBinding perc20 = getResolutionHeight().multiply(0.2);
        DoubleBinding perc40 = getResolutionHeight().multiply(0.4);
        RowConstraints r;
        ObservableList<RowConstraints> rows = gameMenu.getRowConstraints();
        rows.add(r = new RowConstraints());
        r.maxHeightProperty().bind(perc20);
        r.prefHeightProperty().bind(perc20);
        r.minHeightProperty().bind(perc20);
        for (int i = 0; i < 4; i++)
        {
            rows.add(r = new RowConstraints());
            r.maxHeightProperty().bind(perc10);
            r.prefHeightProperty().bind(perc10);
            r.minHeightProperty().bind(perc10);
            r.setFillHeight(false);
        }
        rows.add(r = new RowConstraints());
        r.maxHeightProperty().bind(perc40);
        r.prefHeightProperty().bind(perc40);
        r.minHeightProperty().bind(perc40);

        /// Buttons add
        Button b;
        gameMenu.add(b = exitButton = new Button("Exit"), 0, 0, 2, 1);
        exitButton.setPrefHeight(Integer.MAX_VALUE);
        exitButton.setPrefWidth(Integer.MAX_VALUE);
        exitButton.setMaxHeight(Integer.MAX_VALUE);
        gameMenu.add(b = new Button("INFO"), 0, 5, 2, 1);
        b.setPrefHeight(Integer.MAX_VALUE);
        b.setPrefWidth(Integer.MAX_VALUE);
        b.setMaxHeight(Integer.MAX_VALUE);

        gameMenuButton = new ArrayList<Button>();
        addGameMenuButton("Feed");
        addGameMenuButton("Soldier");
        for (int i = 0; i < 10; i++)
            addGameMenuButton("House" + i);
    }

    /**
     * fügt dem Ingame Auswahl-Menü eine weitere Option hinzu
     * 
     * @param cap
     *            Beschreibung des Buttons
     */
    private void addGameMenuButton(String cap)
    {
        if (gameMenuButton.size() >= 8)
            return;
        Button b = new Button(cap);
        b.prefHeightProperty().bind(this.getResolutionHeight().multiply(0.09));
        b.setPrefWidth(Integer.MAX_VALUE);
        b.setMaxHeight(Integer.MAX_VALUE);
        b.setMaxWidth(Integer.MAX_VALUE);
        gameMenu.add(b, (gameMenuButton.size()) % 2, (gameMenuButton.size()) / 2 + 1);
        System.out.println(gameMenuButton.size());
        gameMenuButton.add(b);
    }

    protected void setImages(Model model)
    {
        gameMenu.setBackground(new Background(new BackgroundFill(Color.YELLOW, new CornerRadii(0), new Insets(0))));
        exitButton.setBackground(new Background(new BackgroundFill(new ImagePattern(model.getImage("MENUE_LEFT"), 0, 0, 1, 1, true), new CornerRadii(0), new Insets(0))));
    }
}
