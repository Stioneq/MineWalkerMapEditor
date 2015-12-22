package org.laptech.minewalker.mapeditor.data.objects;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Floor object
 * @author rlapin
 */
public class Floor extends GameObject{
    public static final Color BGCOLOR = new Color(65, 82, 88,255);
    public Floor(int x, int y, int width, int height) {
        super(x, y, width, height);
    }
    @Override
    public void draw(Graphics g) {
        g.setColor(BGCOLOR);
        g.fillRect(getX(),getY(),getWidth(),getHeight());
    }
}
