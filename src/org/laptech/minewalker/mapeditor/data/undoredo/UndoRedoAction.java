package org.laptech.minewalker.mapeditor.data.undoredo;

/**
 * Support of undo/redo
 * @author rlapin
 */
public interface UndoRedoAction {
    /**
     * Undo last changed
     */
    void undo();

    /**
     * Redo last changed
     */
    void redo();

    /**
     * Applies when some changes happends
     */
    void changed();
}
