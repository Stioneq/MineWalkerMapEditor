package org.laptech.minewalker.mapeditor.gui.tools;

import java.awt.Graphics;

/**
 * Availability to draw gameobject on the canvas
 *
 * @author rlapin
 */
public interface Drawable {
    /**
     * Draw object
     *
     * @param g      Graphics context
     * @param x
     * @param y
     * @param width
     * @param height
     */
    void draw(Graphics g, int x, int y, int width, int height);


}
