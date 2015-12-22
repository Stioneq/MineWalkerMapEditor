package org.laptech.minewalker.mapeditor.gui;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 * Creates menus which are used in menubar
 * @author rlapin
 */
public class MenuFactory {

    public static JMenu createFileMenu(){
        String strMenuFile = "File";
        String strMINew = "New";
        String strMISave = "Save";
        String strMIOpen = "Open";
        String strMIExit = "Exit";
        JMenu menuFile = new JMenu(strMenuFile);
        JMenuItem miNew = new JMenuItem(strMINew);
        JMenuItem miSave = new JMenuItem(strMISave);
        JMenuItem miOpen = new JMenuItem(strMIOpen);
        JMenuItem miExit = new JMenuItem(strMIExit);
        menuFile.add(miNew);
        menuFile.add(miSave);
        menuFile.add(miOpen);
        menuFile.add(miExit);
        return menuFile;
    }


    public static JMenu createEditMenu(){
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

    public static JMenu createHelpMenu(){
        String strMenuHelp = "Help";
        String strMIAbout = "About";
        JMenu menuHelp = new JMenu(strMenuHelp);
        JMenuItem miAbout = new JMenuItem(strMIAbout);
        menuHelp.add(miAbout);
        return menuHelp;
    }
}
