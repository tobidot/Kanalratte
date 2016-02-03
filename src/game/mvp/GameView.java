package game.mvp;

import java.util.ArrayList;

import javafx.beans.binding.DoubleBinding;
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
import model.Model;
import mvp.View;

public class GameView extends View<HBox, GamePresenter>
{
    private GridPane statusInfo;

    private Pane gameScreen;

    private GridPane gameMenu;

    private Button moreLeft;

    private Button moreRight;

    private Button menuInfo;

    private Button exitButton;

    private int pageIndex;

    private ArrayList<Button> gameMenuButton;

    private ArrayList<String> gameMenuButtonID;

    public GameView()
    {

        gameMenuButton = new ArrayList<Button>();
        gameMenuButtonID = new ArrayList<String>();

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
        gameMenu.hgapProperty().bind(getResolutionWidth().multiply(0.01));
        /// GridPane
        DoubleBinding perc5 = getResolutionHeight().multiply(0.05);
        DoubleBinding perc10 = getResolutionHeight().multiply(0.1);
        DoubleBinding perc20 = getResolutionHeight().multiply(0.2);
        DoubleBinding perc40 = getResolutionHeight().multiply(0.35);
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
        r.maxHeightProperty().bind(perc5);
        r.prefHeightProperty().bind(perc5);
        r.minHeightProperty().bind(perc5);
        rows.add(r = new RowConstraints());
        r.maxHeightProperty().bind(perc40);
        r.prefHeightProperty().bind(perc40);
        r.minHeightProperty().bind(perc40);

        /// Buttons add
        Button b;
        gameMenu.add(b = exitButton = new Button(), 0, 0, 2, 1);
        exitButton.setPrefHeight(Integer.MAX_VALUE);
        exitButton.setPrefWidth(Integer.MAX_VALUE);
        exitButton.setMaxHeight(Integer.MAX_VALUE);
        exitButton.setOnAction(e -> {
            onExit();
        });
        gameMenu.add(b = moreLeft = new Button(), 0, 5);
        b.setPrefHeight(Integer.MAX_VALUE);
        b.setPrefWidth(Integer.MAX_VALUE);
        b.setMaxHeight(Integer.MAX_VALUE);
        b.setOnAction(e -> {
            gameMenuMoreLeft();
        });
        gameMenu.add(b = moreRight = new Button(), 1, 5);
        b.setPrefHeight(Integer.MAX_VALUE);
        b.setPrefWidth(Integer.MAX_VALUE);
        b.setMaxHeight(Integer.MAX_VALUE);
        b.setOnAction(e -> {
            gameMenuMoreRight();
        });
        gameMenu.add(b = menuInfo = new Button(), 0, 6, 2, 1);
        b.setPrefHeight(Integer.MAX_VALUE);
        b.setPrefWidth(Integer.MAX_VALUE);
        b.setMaxHeight(Integer.MAX_VALUE);

    }

    private void onExit()
    {
        presenter.onExit();

    }

    /**
     * fügt dem Ingame Auswahl-Menü eine weitere Option hinzu kann nur 8
     * optionen gleichzeitig zeigen
     * 
     * @param cap
     *            Beschreibung des Buttons
     */
    public void addGameMenuButton(String nameID, Background back)
    {
        final int index = gameMenuButton.size();
        Button b = new Button();
        GridPane.setFillWidth(b, false);
        GridPane.setFillHeight(b, false);
        b.prefHeightProperty().bind(this.getResolutionHeight().multiply(0.09));
        b.prefWidthProperty().bind(this.getResolutionWidth().multiply(0.12));
        b.setMaxHeight(Integer.MAX_VALUE);
        b.setMaxWidth(Integer.MAX_VALUE);
        b.setOnAction(e -> {
            onGameMenuOption(gameMenuButtonID.get(index));
        });
        if (back != null)
        {
            b.setBackground(back);
        }
        else
        {
            b.setBackground(new Background(new BackgroundFill(Color.ORANGE, new CornerRadii(0), new Insets(0))));
        }
        if (gameMenuButton.size() - pageIndex * 8 < 8)
        { /// TODO wenn < 0 bei remove beachten
            gameMenu.add(b, (gameMenuButton.size() - pageIndex * 8) % 2, (gameMenuButton.size() - pageIndex * 8) / 2 + 1);
        }
        else
        {
            GridPane.setColumnIndex(b, gameMenuButton.size() % 2);
            GridPane.setRowIndex(b, (gameMenuButton.size() / 2) % 4 + 1);
        }
        gameMenuButton.add(b);
        gameMenuButtonID.add(nameID);
        updateGameMenu();
    }

    /**
     * 
     */
    public void removeGameAuswahlButton(String nameID)
    {
        final int index = gameMenuButtonID.indexOf(nameID);
        gameMenuButton.remove(index);
        gameMenuButtonID.remove(index);
        updateGameMenu();
    }

    private void onGameMenuOption(String nameID)
    {
        presenter.onInGameOption(nameID);
    }

    protected void setImages(Model model)
    {
        gameMenu.setBackground(new Background(new BackgroundFill(Color.YELLOW, new CornerRadii(0), new Insets(0))));
        moreLeft.setBackground(model.getAsBackground("MENUE_MORE_LEFT"));
        moreRight.setBackground(model.getAsBackground("MENUE_MORE_RIGHT"));
        exitButton.setBackground(model.getAsBackground("MENUE_EXIT"));
        menuInfo.setBackground(model.getAsBackground("MENUE_INFO_NONE"));

    }

    private void updateGameMenu()
    {
        for (int i = 0; i < 8; i++)
        {
            try
            {
                if (i + pageIndex * 8 < gameMenuButton.size())
                {
                    gameMenu.getChildren().set(i + 4, gameMenuButton.get(i + pageIndex * 8));
                    gameMenu.getChildren().get(i + 4).setVisible(true);
                }
                else
                {
                    gameMenu.getChildren().get(i + 4).setVisible(false);
                }
            }
            catch (IndexOutOfBoundsException e)
            {

            }
        }

        if (gameMenuButton.size() > (pageIndex + 1) * 8)
        {
            moreRight.setDisable(false);
        }
        else
        {
            moreRight.setDisable(true);
        }
        if (pageIndex <= 0)
        {
            moreLeft.setDisable(true);
        }
        else
        {
            moreLeft.setDisable(false);
        }
    }

    private void gameMenuMoreLeft()
    {
        pageIndex--;
        updateGameMenu();
    }

    private void gameMenuMoreRight()
    {
        pageIndex++;
        updateGameMenu();
    }
}
