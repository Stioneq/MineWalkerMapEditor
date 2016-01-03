package org.laptech.minewalker.mapeditor.gui;

import org.laptech.minewalker.mapeditor.data.MapState;

import javax.swing.JMenuItem;
import java.util.List;

/**
 * Class for setting undo and redo menu
 *
 * @author rlapin
 */
public class UndoRedoChanger {


    private final JMenuItem miUndo;
    private final JMenuItem miRedo;

    public UndoRedoChanger(JMenuItem miUndo, JMenuItem miRedo) {

        this.miUndo = miUndo;
        this.miRedo = miRedo;
    }

    public void update(List<MapState> states, int currentState) {
        if (currentState > 0) {
            miUndo.setText("Undo " + states.get(currentState).getChangeTitle());
            miUndo.setEnabled(true);
        } else {
            miUndo.setEnabled(false);
        }
        if (states.size() - 1 > currentState) {
            miRedo.setText("Redo " + states.get(currentState + 1).getChangeTitle());
            miRedo.setEnabled(true);
        } else {
            miRedo.setEnabled(false);
        }
    }
}
