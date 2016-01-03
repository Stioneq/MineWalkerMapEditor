package org.laptech.minewalker.mapeditor.data.objects;

import java.awt.Color;

/**
 * Door object
 *
 * @author rlapin
 */
public class Door extends GameObject {
    public static final Color BGCOLOR = new Color(65, 82, 88, 255);
    public static final Color INNERCOLOR = new Color(8, 88, 63, 255);
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
