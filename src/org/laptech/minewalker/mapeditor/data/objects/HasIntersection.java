package org.laptech.minewalker.mapeditor.data.objects;

/**
 * Defines behaviour for object that can intersect
 *
 * @author rlapin
 */
public interface HasIntersection {
    /**
     * Check rectangle for intersection
     *
     * @param x      x coordinate
     * @param y      y coordinate
     * @param width
     * @param height
     * @return true if intersects
     */
    boolean intersect(double x, double y, double width, double height);
}
