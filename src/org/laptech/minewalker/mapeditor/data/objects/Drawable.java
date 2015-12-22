package org.laptech.minewalker.mapeditor.data.objects;

import java.awt.Graphics;

/**
 * Availability to draw gameobject on the canvas
 * @author rlapin
 */
public interface Drawable {
    /**
     * Draw object
     * @param g Graphics context
     */
    void draw(Graphics g);
}
