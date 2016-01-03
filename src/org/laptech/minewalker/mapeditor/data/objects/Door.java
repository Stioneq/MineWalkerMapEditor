package org.laptech.minewalker.mapeditor.data.objects;

import java.awt.Color;

/**
 * Door object
 *
 * @author rlapin
 */
public class Door extends GameObject {
    /**
     * True if door is opened
     */
    private boolean isOpened;

    public Door(double x, double y, double width, double height, boolean isOpened) {
        super(x, y, width, height);
        this.isOpened = isOpened;
    }

    public boolean isOpened() {
        return isOpened;
    }

    public void setIsOpened(boolean isOpened) {
        this.isOpened = isOpened;
    }


    @Override
    public String getType() {
        return "door";
    }
}
