package org.laptech.minewalker.mapeditor.gui.tools;

import java.awt.Image;

/**
 * @author rlapin
 */
public interface Tool {
    Image getToolIcon();
    String getTooltip();
    void apply(double x, double y, double width, double height);

    /**
     * Type of tool use in editorarea to get information about current tool
     * @return
     */
    String getType();
}
