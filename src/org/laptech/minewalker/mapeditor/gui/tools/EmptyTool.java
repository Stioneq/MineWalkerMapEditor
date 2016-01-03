package org.laptech.minewalker.mapeditor.gui.tools;

import org.laptech.minewalker.mapeditor.gui.tools.properties.ToolProperty;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Collections;
import java.util.Set;

/**
 * NULL tool
 *
 * @author rlapin
 */
public class EmptyTool implements Tool {

    public static final String TYPE = "empty";

    @Override
    public Image getToolIcon() {
        return null;
    }

    @Override
    public String getTooltip() {
        return null;
    }

    @Override
    public void apply(double x, double y, double width, double height) {

    }

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public void apply(double x, double y) {

    }

    @Override
    public Set<ToolProperty> getProperties() {
        return Collections.emptySet();
    }

    @Override
    public void addPropertyChangeListener(org.laptech.minewalker.mapeditor.gui.tools.properties.PropertyChangeListener propertyChangeListener) {

    }


    @Override
    public void draw(Graphics g, int x, int y, int width, int height) {
        // Don't draw
    }
}
