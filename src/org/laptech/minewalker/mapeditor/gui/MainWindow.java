package org.laptech.minewalker.mapeditor.gui;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * @author rlapin
 */
public class MainWindow {
    public static final String TITLE = "MineWalker MapEditor";
    private JFrame frame;


    public MainWindow() {
        frame = new JFrame(TITLE);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(640,480);
        frame.setLocationRelativeTo(null);
    }

    public void show(){
        frame.setVisible(true);
    }
}
