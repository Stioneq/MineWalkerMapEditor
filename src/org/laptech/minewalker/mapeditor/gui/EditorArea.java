package org.laptech.minewalker.mapeditor.gui;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;

/**
 * Area for display map view
 * @author rlapin
 */
public class EditorArea extends JPanel{

    public static final Color BGCOLOR = new Color(37, 37, 39);

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(BGCOLOR);
    }
}
