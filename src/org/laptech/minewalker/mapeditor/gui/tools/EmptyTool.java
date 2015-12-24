package org.laptech.minewalker.mapeditor.gui.tools;

import java.awt.Image;

/**
 * NULL tool
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
}
