package menues.main;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import mvp.View;

public class MainMenuView extends View<HBox>
{
    public MainMenuView()
    {
        root = new HBox();
        root.getChildren().add(new Label("MainMenu"));
    }
}
