package org.laptech.minewalker.mapeditor.data.undoredo;

import org.laptech.minewalker.mapeditor.data.MapState;

import java.util.List;

/**
 * Event when undo or redo fired
 *
 * @author rlapin
 */
public interface UndoRedoHandler {
    default void onUndoRedo(List<MapState> states, int curState) {
    }

    ;
}
