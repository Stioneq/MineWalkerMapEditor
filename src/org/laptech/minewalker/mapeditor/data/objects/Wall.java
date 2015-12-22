package org.laptech.minewalker.mapeditor.data.objects;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Wall object
 * @author rlapin
 */
public class Wall extends GameObject{
    public static final Color BGCOLOR = new Color(188, 239, 255,255);
    public Wall(int x, int y, int width, int height) {
        super(x, y, width, height);
    }
    @Override
    public void draw(Graphics g) {
        g.setColor(BGCOLOR);
        g.fillRect(getX(),getY(),getWidth(),getHeight());
    }
}
