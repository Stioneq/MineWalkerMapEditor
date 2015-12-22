package org.laptech.minewalker.mapeditor.gui;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;

/**
 * @author rlapin
 */
public class MainWindow {
    public static final String TITLE = "MineWalker MapEditor";
    private JFrame frame;


    public MainWindow() {
        frame = new JFrame(TITLE);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(640, 480);
        frame.setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        frame.setLayout(new BorderLayout());
        frame.add(createMenuBar(), BorderLayout.NORTH);
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(MenuFactory.createFileMenu());
        menuBar.add(MenuFactory.createEditMenu());
        menuBar.add(MenuFactory.createHelpMenu());
        return menuBar;
    }

    public void show(){
        frame.setVisible(true);
    }
}
