package org.laptech.minewalker.mapeditor.gui.utils;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 * @author rlapin
 */
public class ComponentUtils {

    public static final Color BGCOLOR = new Color(30, 30, 30, 255);
    public static final Color FGCOLOR = new Color(120, 200, 145, 255);

    /**
     * Create lane with label containing definite text
     *
     * @param text text of label
     * @return JPanel
     */
    public static JPanel createLabelLane(String text) {
        JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        labelPanel.setOpaque(true);
        labelPanel.setBackground(BGCOLOR);
        JLabel label = new JLabel(text);
        label.setForeground(FGCOLOR);
        labelPanel.add(label);
        return labelPanel;

    }




}
