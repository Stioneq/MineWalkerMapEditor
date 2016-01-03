package org.laptech.minewalker.mapeditor.data.objects;

import java.awt.Color;

/**
 * Wall object
 *
 * @author rlapin
 */
public class Wall extends GameObject {
    public static final Color BGCOLOR = new Color(188, 239, 255, 255);

    public Wall(double x, double y, double width, double height) {
        super(x, y, width, height);
    }

    @Override
    public String getType() {
        return "wall";
    }


}
