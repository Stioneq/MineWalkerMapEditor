package org.laptech.minewalker.mapeditor.data.objects;

/**
 * Defines behaviour for object that can collide
 *
 * @author rlapin
 */
public interface HasCollision {
    /**
     * Check point(x,y) for collision
     *
     * @param x x coordinate
     * @param y y coordinate
     * @return true if collide
     */
    boolean collide(double x, double y);

    /**
     * Collide with rect
     *
     * @param x
     * @param y
     * @param width
     * @param height
     * @return true if collide
     */
    boolean collide(double x, double y, double width, double height);
}
