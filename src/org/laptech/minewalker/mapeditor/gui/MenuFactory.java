package org.laptech.minewalker.mapeditor.gui;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import java.awt.Color;
import java.io.File;

/**
 * Creates menus which are used in menubar
 *
 * @author rlapin
 */
public class MenuFactory {
    public static JFileChooser fileChooser;
    static{
        fileChooser = new JFileChooser("");
        fileChooser.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.getName().endsWith(".map");
            }

            @Override
            public String getDescription() {
                return "MineWalker maps(*.map)";
            }
        });
    }
    public static JMenu createFileMenu(EditorController editorController) {
        String strMenuFile = "File";
        String strMINew = "New";
        String strMISave = "Save";
        String strMIOpen = "Open";
        String strMIExit = "Exit";
        JMenu menuFile = new JMenu(strMenuFile);
        JMenuItem miNew = new JMenuItem(strMINew);
        miNew.addActionListener(event -> editorController.newMap());
        JMenuItem miSave = new JMenuItem(strMISave);
        miSave.addActionListener(event -> {

            if(fileChooser.showSaveDialog(null)==JFileChooser.APPROVE_OPTION){
                editorController.saveMap(fileChooser.getSelectedFile().getPath()+".map");
            }

        });
        JMenuItem miOpen = new JMenuItem(strMIOpen);
        miOpen.addActionListener(event -> {

            if(fileChooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
                editorController.loadMap(fileChooser.getSelectedFile().getPath());
            }

        });
        JMenuItem miExit = new JMenuItem(strMIExit);
        miExit.addActionListener(event->System.exit(0));
        menuFile.add(miNew);
        menuFile.add(miSave);
        menuFile.add(miOpen);
        menuFile.add(miExit);
        return menuFile;
    }


    public static JMenu createEditMenu(EditorController editorController) {
        String strMenuEdit = "Edit";
        String strMIUndo = "Undo";
        String strMIRedo = "Redo";
        String strMICut = "Cut";
        String strMICopy = "Copy";
        String strMIPaste = "Paste";
        String strMISelAll = "Select all";
        JMenu menuEdit = new JMenu(strMenuEdit);
        JMenuItem miUndo = new JMenuItem(strMIUndo);
        JMenuItem miRedo = new JMenuItem(strMIRedo);
        JMenuItem miSelAll = new JMenuItem(strMISelAll);
        JMenuItem miCut = new JMenuItem(strMICut);
        JMenuItem miCopy = new JMenuItem(strMICopy);
        JMenuItem miPaste = new JMenuItem(strMIPaste);
        menuEdit.add(miUndo);
        menuEdit.add(miRedo);
        menuEdit.add(miSelAll);
        menuEdit.add(miCut);
        menuEdit.add(miCopy);
        menuEdit.add(miPaste);
        return menuEdit;
    }

    public static JMenu createViewMenu(EditorController editorController) {
        JMenu viewMenu = new JMenu("View");
        viewMenu.add(createGridSubMenu(editorController));

        return viewMenu;
    }

    private static JMenuItem createGridSubMenu(EditorController editorController) {
        JMenu menu = new JMenu("Grid");
        JCheckBoxMenuItem miShowGrid = new JCheckBoxMenuItem("Show grid");
        miShowGrid.setSelected(true);
        menu.add(miShowGrid);
        miShowGrid.addActionListener(event -> editorController.setShowGrid(miShowGrid.isSelected()));
        JMenuItem miGridSize = new JMenuItem("Grid cell size");
        menu.add(miGridSize);
        miGridSize.addActionListener(event -> editorController.setGridSize(JOptionPane.showInputDialog(null, "Input size of grid", 50)));
        JMenuItem miGridColor = new JMenuItem("Grid color");
        miGridColor.setBackground(new Color(205, 255, 107, 100));
        miGridColor.addActionListener(event -> {
            Color color = JColorChooser.showDialog(null, "Choose grid color", Color.CYAN);
            editorController.setGridColor(color);
            miGridColor.setBackground(color);
            miGridColor.setForeground(color.darker());
        });
        menu.add(miGridColor);
        return menu;
    }

    public static JMenu createHelpMenu(EditorController editorController) {
        String strMenuHelp = "Help";
        String strMIAbout = "About";
        JMenu menuHelp = new JMenu(strMenuHelp);
        JMenuItem miAbout = new JMenuItem(strMIAbout);
        menuHelp.add(miAbout);
        return menuHelp;
    }
}
