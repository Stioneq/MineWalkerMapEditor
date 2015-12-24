package org.laptech.minewalker.mapeditor.gui;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;

/**
 * Main window of editor <br>
 * View of MVC, editorController controller and Map is a model
 * @author rlapin
 */
public class MainWindow {
    public static final String TITLE = "MineWalker MapEditor";
    private JFrame frame;
    private EditorArea editorArea;
    private EditorController controller;
    private ToolsPane toolsPane;

    public MainWindow() {
        controller = new EditorController(this);
        frame = new JFrame(TITLE);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(640, 480);
        frame.setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        frame.setLayout(new BorderLayout());
        frame.add(createMenuBar(), BorderLayout.NORTH);
        editorArea = new EditorArea();
        toolsPane = new ToolsPane(controller);
        frame.add(editorArea,BorderLayout.CENTER);
        frame.add(toolsPane,BorderLayout.EAST);

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
