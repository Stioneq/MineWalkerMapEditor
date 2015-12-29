package org.laptech.minewalker.mapeditor.gui.utils;

import org.junit.Before;
import org.junit.Test;

import java.awt.Point;
import java.awt.Rectangle;

import static org.junit.Assert.*;

/**
 * Test convertor
 * @author rlapin
 */
public class PointConverterTest {





    @Test
    public void testUpdateMultipliers() throws Exception {
        PointConverter pointConverter = new PointConverter();
        pointConverter.updateMultipliers(50,100,new Rectangle(0,0,10,10));
        assertEquals(pointConverter.getKX(),0.5,1E-3);
        assertEquals(pointConverter.getKY(),0.1,1E-3);
    }

    @Test
    public void testConvertXToScreen() throws Exception {
        PointConverter pointConverter = new PointConverter();
        pointConverter.updateMultipliers(50, 100, new Rectangle(5, 0, 10, 10));
        assertEquals(pointConverter.convertXToScreen(10), 50, 1E-3);
    }

    @Test
    public void testConvertXFromScreen() throws Exception {
        PointConverter pointConverter = new PointConverter();
        pointConverter.updateMultipliers(50,100,new Rectangle(5,5,10,10));
        assertEquals(pointConverter.convertXFromScreen(10),2,1E-3);
    }

    @Test
    public void testConvertYToScreen() throws Exception {
        PointConverter pointConverter = new PointConverter();
        pointConverter.updateMultipliers(50,100,new Rectangle(5,5,10,10));
        assertEquals(pointConverter.convertYToScreen(5),5,1E-3);
    }

    @Test
    public void testConvertYFromScreen() throws Exception {
        PointConverter pointConverter = new PointConverter();
        pointConverter.updateMultipliers(50,100,new Rectangle(0,0,10,10));
        assertEquals(pointConverter.convertYFromScreen(10),1,1E-3);
    }
}