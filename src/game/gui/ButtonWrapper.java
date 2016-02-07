package game.gui;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.layout.Background;

public class ButtonWrapper
{
    private SimpleObjectProperty<EventAction> trigger;

    private SimpleStringProperty description;

    private SimpleStringProperty name;

    private SimpleObjectProperty<Background> background;

    public ReadOnlyObjectProperty<Background> getBackground()
    {
        return background;
    };

    public ReadOnlyStringProperty getName()
    {
        return name;
    };

    public ReadOnlyStringProperty getDescription()
    {
        return description;
    };

    public ReadOnlyObjectProperty<EventAction> getTrigger()
    {
        return trigger;
    };

    public ButtonWrapper(String name, String desc, Background back, EventAction ea)
    {
        this.name = new SimpleStringProperty(name);
        description = new SimpleStringProperty(desc);
        background = new SimpleObjectProperty<Background>(back);
        trigger = new SimpleObjectProperty<EventAction>(ea);
    }

    public ButtonWrapper(String name, String desc, ButtonAnimation anim, EventAction ea)
    {
        this.name = new SimpleStringProperty(name);
        description = new SimpleStringProperty(desc);
        background = new SimpleObjectProperty<Background>();
        background.bind(anim.currentImage());
        trigger = new SimpleObjectProperty<EventAction>(ea);
    }

}
