package game.gui;

import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.layout.Background;

public class UsableButton
{
    private SimpleObjectProperty<EventAction> onClickAction;

    private SimpleStringProperty name;

    private SimpleStringProperty description;

    private SimpleObjectProperty<Background> currentImage;

    private SimpleBooleanProperty isActive;

    public UsableButton(String name, String desc, Background back)
    {
        onClickAction = null;
        isActive = new SimpleBooleanProperty(true);
        this.name = new SimpleStringProperty(name);
        this.description = new SimpleStringProperty(desc);
        this.currentImage = new SimpleObjectProperty<Background>(back);
    }

    public UsableButton()
    {
        onClickAction = new SimpleObjectProperty<EventAction>();
        isActive = new SimpleBooleanProperty(true);
        this.name = new SimpleStringProperty("");
        this.description = new SimpleStringProperty("");
        this.currentImage = new SimpleObjectProperty<Background>();
    }

    public void linkTo(ButtonWrapper b)
    {
        onClickAction.bind(b.getTrigger());
        name.bind(b.getName());
        description.bind(b.getDescription());
        currentImage.bind(b.getBackground());
    }

    public void use()
    {
        if (onClickAction != null && onClickAction.get() != null)
        {
            onClickAction.get().trigger();
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

    public ReadOnlyBooleanProperty isActive()
    {
        return isActive;
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
        return currentImage;
    }
}
