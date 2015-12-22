package org.laptech.minewalker.mapeditor.data.objects;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Spiked Floor object
 * @author rlapin
 */
public class SpikedFloor extends GameObject{
    public static final Color BGCOLOR = new Color(88, 37, 36,255);
    public SpikedFloor(int x, int y, int width, int height) {
        super(x, y, width, height);
    }
    @Override
    public void draw(Graphics g) {
        g.setColor(BGCOLOR);
        g.fillRect(getX(),getY(),getWidth(),getHeight());
    }
}
