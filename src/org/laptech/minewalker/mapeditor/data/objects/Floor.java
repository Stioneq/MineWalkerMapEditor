package org.laptech.minewalker.mapeditor.data.objects;

import java.awt.Color;

/**
 * Floor object
 *
 * @author rlapin
 */
public class Floor extends GameObject {

    public Floor(double x, double y, double width, double height) {
        super(x, y, width, height);
    }

    @Override
    public String getType() {
        return "floor";
    }

}
