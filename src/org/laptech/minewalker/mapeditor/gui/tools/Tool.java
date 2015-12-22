package org.laptech.minewalker.mapeditor.gui.tools;

import java.awt.Image;

/**
 * @author rlapin
 */
public interface Tool {
    Image getToolIcon();
    String getTooltip();
    void apply(int x, int y, int width, int height);
}
