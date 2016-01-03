package org.laptech.minewalker.mapeditor.data.objects;

import java.awt.Color;

/**
 * Spiked Floor object
 *
 * @author rlapin
 */
public class SpikedFloor extends GameObject {

    public SpikedFloor(double x, double y, double width, double height) {
        super(x, y, width, height);
    }

    @Override
    public String getType() {
        return "spikedfloor";
    }


}
