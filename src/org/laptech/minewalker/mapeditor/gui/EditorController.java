package org.laptech.minewalker.mapeditor.gui;

import org.laptech.minewalker.mapeditor.data.Map;
import org.laptech.minewalker.mapeditor.data.MapState;
import org.laptech.minewalker.mapeditor.data.io.XMLWriter;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.util.List;

/**
 * Link between Map and GUI
 *
 * @author rlapin
 */
public class EditorController {
    private Map map;
    private MainWindow mainWindow;
    private UndoRedoChanger undoRedoChanger;

    public EditorController(MainWindow window) {
        this.mainWindow = window;
        map = new Map(this);
    }


    public void newMap() {
        map.getObjects().clear();
    }

    public void saveMap(String filePath) {
        new XMLWriter(filePath).write(map);
    }

    public void loadMap(String filePath) {
        new XMLWriter(filePath).write(map);
    }

    public Map getMap() {
        return map;
    }

    public void setShowGrid(boolean selected) {
        mainWindow.getEditorArea().getGrid().setShowGrid(selected);
    }


    public void setGridSize(String s) {
        try {
            mainWindow.getEditorArea().getGrid().setGridSize(Integer.parseInt(s));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Incorrect number format", "Error", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void setGridColor(Color color) {
        mainWindow.getEditorArea().getGrid().setGridColor(color);
    }

    /**
     * Fires when model is changed
     */
    public void mapChanged() {
        mainWindow.getEditorArea().repaint();
    }


    public void mapStateChanged(List<MapState> states, int currentState) {
        undoRedoChanger.update(states, currentState);
    }

    public void initUndoRedoChanger(JMenuItem miUndo, JMenuItem miRedo) {
        undoRedoChanger = new UndoRedoChanger(miUndo, miRedo);
    }

}
