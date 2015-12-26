package org.laptech.minewalker.mapeditor.gui.tools;

import java.awt.Graphics;
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

    @Override
    public void apply(double x, double y) {

    }

    @Override
    public void draw(Graphics g, int x, int y, int width, int height) {
        // Don't draw
    }
}
