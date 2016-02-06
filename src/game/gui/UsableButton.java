package game.gui;

import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class UsableButton
{
    private EventAction onClickAction;

    private SimpleStringProperty name;

    private SimpleStringProperty description;

    private SimpleObjectProperty<ButtonAnimation> animation;

    private SimpleBooleanProperty isActive;

    public UsableButton(String name, String description, ButtonAnimation anim, EventAction ea)
    {
        if (name == null)
        {
            name = "";
        }
        if (description == null)
        {
            description = "";
        }
        if (anim == null)
        {
            anim = new ButtonAnimation(0, null);
        }
        onClickAction = ea;
        isActive = new SimpleBooleanProperty(true);
        this.name = new SimpleStringProperty(name);
        this.description = new SimpleStringProperty(description);
        this.animation = new SimpleObjectProperty<ButtonAnimation>(anim);
    }

    public void bind(UsableButton b)
    {
        onClickAction = b.onClickAction;
        name.bind(b.name);
        description.bind(b.description);
        animation.bind(b.animation);
        // isActive.bind(b.isActive);
    }

    public ReadOnlyStringProperty getName()
    {
        return name;
    }

    public ReadOnlyStringProperty getDescription()
    {
        return description;
    }

    public ReadOnlyObjectProperty<Background> getBackground()
    {
        return animation.get().currentImage();
    }

    public ReadOnlyBooleanProperty getActive()
    {
        return isActive;
    }

    public void use()
    {
        if (onClickAction != null)
        {
            onClickAction.trigger();
        }
    }

    /**
     * sets the visability of this Button to true
     */
    public void activate()
    {
        isActive.set(true);
    }

    /**
     * sets the visability of this Button to false
     */
    public void deactivate()
    {
        isActive.set(false);
    }

}
