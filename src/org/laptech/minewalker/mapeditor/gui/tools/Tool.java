package org.laptech.minewalker.mapeditor.gui.tools;

import org.laptech.minewalker.mapeditor.gui.tools.properties.PropertyChangeListener;
import org.laptech.minewalker.mapeditor.gui.tools.properties.ToolProperty;

import java.awt.Image;
import java.util.Set;

/**
 * @author rlapin
 */
public interface Tool extends Drawable {
    Image getToolIcon();
    String getTooltip();
    void apply(double x, double y, double width, double height);

    /**
     * Type of tool use in editorarea to get information about current tool
     * @return
     */
    String getType();

    /**
     * Apply tool to point with x and y (in real units)
     * @param x value of x in real units
     * @param y value of y in real units
     */
    void apply(double x, double y);

    /**
     * Get properties for tool
     * @return
     */
    Set<ToolProperty> getProperties();
    void addPropertyChangeListener(PropertyChangeListener propertyChangeListener);
}
