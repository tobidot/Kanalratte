package game.mvp;

import java.util.ArrayList;

import game.gui.UsableButton;
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
import javafx.scene.layout.StackPane;
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

    private ArrayList<UsableButton> menueButton = new ArrayList<UsableButton>();
    //
    // private ArrayList<Button> gameMenuButton;
    //
    // private ArrayList<String> gameMenuButtonID;

    private StackPane selectInfoProfile;

    private StackPane selectInfoLife;

    private StackPane selectInfoMana;

    private final int abilitiesCount = 12;

    // private String inAbilityID[] = new String[abilitiesCount];
    //
    // private Button[] inAbilityButton = new Button[abilitiesCount];

    private UsableButton[] abilityButton = new UsableButton[abilitiesCount];

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
        inGameScreenLayout();

        /// GameMenu Layout
        gameMenuLayout();

    }

    private void gameMenuLayout()
    {
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

    private void inGameScreenLayout()
    {
        gameScreen.prefHeightProperty().bind(getResolutionHeight().multiply(0.75));
        statusInfo.prefHeightProperty().bind(getResolutionHeight().multiply(0.25));

        RowConstraints r;
        statusInfo.getRowConstraints().add(r = new RowConstraints());
        r.setPercentHeight(50);
        statusInfo.getRowConstraints().add(r = new RowConstraints());
        r.setPercentHeight(50);

        statusInfo.add(selectInfoProfile = new StackPane(), 0, 0, 2, 2);
        GridPane.setFillHeight(selectInfoProfile, true);
        statusInfo.add(selectInfoLife = new StackPane(), 2, 0);
        GridPane.setFillHeight(selectInfoLife, true);
        statusInfo.add(selectInfoMana = new StackPane(), 2, 1);
        GridPane.setFillHeight(selectInfoMana, true);
        selectInfoProfile.prefWidthProperty().bind(getResolutionWidth().multiply(0.27));
        selectInfoLife.prefWidthProperty().bind(getResolutionWidth().multiply(0.02));
        selectInfoMana.prefWidthProperty().bind(getResolutionWidth().multiply(0.02));
        /// 16 Felder(8 breit) mit Fähigkeiten
        DoubleBinding w = getResolutionWidth().multiply(0.40 / (abilitiesCount / 2));
        for (int i = 0; i < abilitiesCount; i++)
        {
            final int index = i;
            Button b;
            abilityButton[index] = new UsableButton(null, null, null);
            statusInfo.add(b = new Button(), 3 + i % (abilitiesCount / 2), i / (abilitiesCount / 2));
            b.visibleProperty().bind(abilityButton[index].getActive());
            b.prefWidthProperty().bind(w);
            b.maxWidthProperty().bind(w);
            b.prefHeightProperty().bind(w);
            b.maxHeightProperty().bind(w);
            getResolutionWidth().addListener((src, o, n) -> {
                GridPane.setMargin(b, new Insets(n.doubleValue() * 0.06 / (abilitiesCount / 2)));
            });
            b.setOnAction(e -> {
                abilityUse(abilityButton[index]);
            });
        }
        // TODO
    }

    private void abilityUse(UsableButton usableButton)
    {
        usableButton.use();
    }

    public void showAbbilties(UsableButton... abilities)
    {
        for (int i = 0; i < abilitiesCount; i++)
        {
            if (i < abilities.length)
            {
                abilityButton[i].bind(abilities[i]);
                abilityButton[i].activate();
            }
            else
            {
                abilityButton[i].deactivate();
            }
        }
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
    public void addGameMenuButton(UsableButton option)
    {
        final int index = menueButton.size();
        Button b = new Button();
        GridPane.setFillWidth(b, false);
        GridPane.setFillHeight(b, false);
        b.prefHeightProperty().bind(this.getResolutionHeight().multiply(0.09));
        b.prefWidthProperty().bind(this.getResolutionWidth().multiply(0.12));
        b.setMaxHeight(Integer.MAX_VALUE);
        b.setMaxWidth(Integer.MAX_VALUE);
        b.setOnAction(e -> {
            onGameMenuUse(menueButton.get(index));
        });
        b.backgroundProperty().bind(option.getBackground());
        if (index - pageIndex * 8 < 8)
        { /// TODO wenn < 0 bei remove beachten
            gameMenu.add(b, (index - pageIndex * 8) % 2, (index - pageIndex * 8) / 2 + 1);
        }
        else
        {
            GridPane.setColumnIndex(b, index % 2);
            GridPane.setRowIndex(b, (index / 2) % 4 + 1);
        }
        menueButton.add(option);
        updateGameMenu();
    }

    private void onGameMenuUse(UsableButton usableButton)
    {
        // TODO Auto-generated method stub

    }

    /**
     * entfernt den Menue-Button mit dem Namen
     * 
     */
    public void removeGameAuswahlButton(String nameID)
    {
        for (int i = 0; i < menueButton.size(); i++)
        {
            if (menueButton.get(i).getName().equals(nameID))
            {
                menueButton.remove(i);
            }
        }
        updateGameMenu();
    }

    protected void setImages(Model model)
    {
        gameMenu.setBackground(new Background(new BackgroundFill(Color.YELLOW, new CornerRadii(0), new Insets(0))));
        moreLeft.setBackground(model.getAsBackground("MENUE_MORE_LEFT"));
        moreRight.setBackground(model.getAsBackground("MENUE_MORE_RIGHT"));
        exitButton.setBackground(model.getAsBackground("MENUE_EXIT"));
        menuInfo.setBackground(model.getAsBackground("MENUE_INFO_NONE"));

        gameScreen.setBackground(new Background(new BackgroundFill(Color.BLACK, new CornerRadii(0), new Insets(0))));
        statusInfo.setBackground(new Background(new BackgroundFill(Color.GRAY, new CornerRadii(0), new Insets(0))));

        selectInfoProfile.setBackground(model.getAsBackground("MENUE_INFO_NONE"));
        selectInfoLife.setBackground(new Background(new BackgroundFill(Color.RED, new CornerRadii(0), new Insets(0))));
        selectInfoMana.setBackground(new Background(new BackgroundFill(Color.BLUE, new CornerRadii(0), new Insets(0))));

    }

    private void updateGameMenu()
    {
        if (menueButton.size() > (pageIndex + 1) * 8)
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
