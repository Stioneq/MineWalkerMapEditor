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


    public void updateMultipliers(int width, int height, Rectangle mapBounds) {
        kX = (mapBounds.getWidth())/width;
        kY = (mapBounds.getHeight())/height;
        offsetX = mapBounds.getX();
        offsetY = mapBounds.getY();
    }

    public int convertXToScreen(double x){
        return (int) ((x-offsetX)/kX);
    }

    public double convertXFromScreen(int x){
        return x*kX + offsetX;
    }

    public int convertYToScreen(double y){
        return (int) ((y-offsetY)/kY);
    }
    public double convertYFromScreen(int y){
        return y*kY + offsetY;
    }

    public double getKX() {
        return kX;
    }

    public double getKY() {
        return kY;
    }



    public int convertXUnitsToScreen(double value) {
        return Math.abs(convertXToScreen(value)-convertXToScreen(0));
    }
    public int convertYUnitsToScreen(double value) {
        return Math.abs(convertYToScreen(value)-convertYToScreen(0));
    }

    public double convertXUnitsFromScreen(int value) {
        return Math.abs(convertXFromScreen(value)-convertXFromScreen(0));
    }
    public double convertYUnitsFromScreen(int value) {
        return Math.abs(convertYFromScreen(value)-convertYFromScreen(0));
    }
}
