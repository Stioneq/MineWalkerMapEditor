package org.laptech.minewalker.mapeditor.data.objects;

import java.awt.Color;
import java.awt.Graphics;

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

    public Door(int x, int y, int width, int height, boolean isOpened) {
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
    public void draw(Graphics g) {

        g.setColor(BGCOLOR);
        g.fillRect(getX(), getY(), getWidth(), getHeight());
        int innerX = getX() + getWidth() / 4;
        int innerY = getY() + getHeight() / 4;
        int innerWidth = getWidth() / 2;
        int innerHeight = getHeight() / 2;
        g.setColor(INNERCOLOR);
        g.fillRect(innerX, innerY, innerWidth, innerHeight);
    }
}
