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
    private EventAction onClickAction;

    private SimpleStringProperty name;

    private SimpleStringProperty description;

    private SimpleObjectProperty<Background> image;

    private SimpleBooleanProperty isActive;

    public UsableButton(String name, String description, EventAction ea)
    {
        if (name == null)
        {
            name = "";
        }
        if (description == null)
        {
            description = "";
        }
        onClickAction = ea;
        isActive = new SimpleBooleanProperty(true);
        this.name = new SimpleStringProperty(name);
        this.description = new SimpleStringProperty(description);
    }

    public void bind(UsableButton b)
    {
        onClickAction = b.onClickAction;
        name.bind(b.name);
        description.bind(b.description);
        image.bind(b.image);
        isActive.bind(b.isActive);
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
        return image;
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

    public void activate()
    {
        isActive.set(true);
    }

    public void deactivate()
    {
        isActive.set(true);
    }

}
