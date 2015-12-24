package org.laptech.minewalker.mapeditor.gui.utils;

import java.awt.Point;
import java.awt.Rectangle;

/**
 * Convert screen coordinate to physics and back
 * @author rlapin
 */
public class PointConverter {
    /**
     * rX = sX * kX + offsetX
     */
    private double kX;
    /**
     * rY = sY * kY + offsetY;
     */
    private double kY;
    private double offsetX;
    private double offsetY;
    public Point convertFromScreen(Point p){
        Point result = new Point();
        result.setLocation(p.getX()*kX+offsetX,p.getY()*kY+offsetY);
        return result;
    }
    public Point convertToScreen(Point p){
        Point result = new Point();
        result.setLocation((p.getX()-offsetX)/kX,(p.getY()-offsetY)/kY);
        return result;
    }

    public void updateMultipliers(int width, int height, Rectangle mapBounds) {
        kX = (mapBounds.getWidth()-mapBounds.getX())/width;
        kY = (mapBounds.getHeight()-mapBounds.getY())/height;
        offsetX = mapBounds.getX();
        offsetY = mapBounds.getY();
    }

    public double convertXToScreen(double x){
        return (x-offsetX)/kX;
    }

    public double convertXFromScreen(double x){
        return x*kX + offsetX;
    }

    public double convertYToScreen(double y){
        return (y-offsetY)/kY;
    }
    public double convertYFromScreen(double y){
        return y*kY + offsetY;
    }

    public double getKX() {
        return kX;
    }

    public double getKY() {
        return kY;
    }

    public double getOffsetX() {
        return offsetX;
    }

    public double getOffsetY() {
        return offsetY;
    }
}
