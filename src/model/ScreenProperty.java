package model;

import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 * Datenspeicherklasse um auf Bildschirmgröße zuzugrefien
 * 
 * @author Tobi
 *
 */
public class ScreenProperty
{
    private SimpleDoubleProperty resolutionWidth;

    private SimpleDoubleProperty resolutionHeight;

    public ScreenProperty(double rH, double rW)
    {
        resolutionHeight = new SimpleDoubleProperty(rH);
        resolutionWidth = new SimpleDoubleProperty(rW);
    }

    public ReadOnlyDoubleProperty resolutionWidthProperty()
    {
        return resolutionWidth;
    }

    public ReadOnlyDoubleProperty resolutionHeightProperty()
    {
        return resolutionHeight;
    }

}
