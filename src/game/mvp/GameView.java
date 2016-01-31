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
        root.setFillHeight(true);
        root.setMaxHeight(600);
        left.setPrefWidth(5000);
        /// ingameScreen Layout
        left.getChildren().add(gameScreen = new Pane());
        left.getChildren().add(statusInfo = new GridPane());
        gameScreen.setMinSize(300, 200);
        gameScreen.setPrefSize(800, 600);
        /// GameMenu Layout

        gameMenu.setPrefWidth(4500);
        gameMenu.setPrefHeight(Integer.MAX_VALUE);

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
        }
        rows.add(r = new RowConstraints());
        r.maxHeightProperty().bind(perc40);
        r.prefHeightProperty().bind(perc40);
        r.minHeightProperty().bind(perc40);

        /// Buttons add
        Button b;
        gameMenu.add(b = exitButton = new Button("Exit"), 0, 0, 2, 1);
        exitButton.setPrefHeight(1000);
        exitButton.setPrefWidth(1000);
        // exitButton.setMaxHeight(1000);
        gameMenu.add(b = new Button("INFO"), 0, 5, 2, 1);
        b.setPrefHeight(1000);
        b.setPrefWidth(1000);
        b.setMaxHeight(1000);

        gameMenuButton = new ArrayList<Button>();
        addGameMenuButton("Feed");
        addGameMenuButton("Soldier");
        for (int i = 0; i < 10; i++)
            addGameMenuButton("House" + i);
    }

    private void addGameMenuButton(String cap)
    {
        if (gameMenuButton.size() >= 8)
            return;
        Button b = new Button(cap);
        b.setPrefHeight(1000);
        b.setPrefWidth(1000);
        b.setMaxHeight(1000);
        b.setMaxWidth(1000);
        gameMenu.add(b, (gameMenuButton.size()) % 2, (gameMenuButton.size()) / 2 + 1);
        System.out.println(gameMenuButton.size());
        gameMenuButton.add(b);
    }

    protected void setImages(Model model)
    {
        gameMenu.setBackground(new Background(new BackgroundFill(Color.YELLOW, new CornerRadii(0), new Insets(0))));
        // exitButton.setBackground(new Background(new BackgroundFill(new
        // ImagePattern(model.getImage("MENUE_LEFT"), 0, 0, 1, 1, true), new
        // CornerRadii(0), new Insets(0))));
    }
}
