package org.laptech.minewalker.mapeditor.data;

import org.laptech.minewalker.mapeditor.data.objects.GameObject;
import org.laptech.minewalker.mapeditor.data.undoredo.DefaultUndoRedoAction;
import org.laptech.minewalker.mapeditor.data.undoredo.UndoRedoAction;
import org.laptech.minewalker.mapeditor.data.undoredo.UndoRedoHandler;
import org.laptech.minewalker.mapeditor.gui.EditorController;
import org.laptech.minewalker.mapeditor.gui.tools.SelectionTool;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;

/**
 * Game map contains gameobjects
 *
 * @author rlapin
 */
public class Map {
    private static final Logger LOGGER = getLogger(Map.class.getName());
    private Set<GameObject> objects = new HashSet<>();
    private Set<GameObject> selectedObjects = new HashSet<>();
    private IClipBoard clipBoard = new ClipBoard();

    /**
     * Name of the map
     */
    private String name;
    private UndoRedoAction undoRedoAction;
    private EditorController editorController;


    public Map(EditorController editorController) {
        this.editorController = editorController;
        undoRedoAction = new DefaultUndoRedoAction(this, new UndoRedoHandler() {
            @Override
            public void onUndoRedo(List<MapState> states, int curState) {
                editorController.mapStateChanged(states, curState);
            }
        });
    }

    /**
     * Get unmodifiable set of gameobjects
     *
     * @return
     */
    public Set<GameObject> getObjects() {
        return Collections.unmodifiableSet(objects);
    }

    /**
     * Set objects and signal to editorController
     *
     * @param objects
     */
    public void setObjects(Set<GameObject> objects) {
        this.objects.clear();
        this.objects.addAll(objects);
        editorController.mapChanged();
    }

    /**
     * Get unmodifiable set of selected gameobjects
     *
     * @return
     */
    public Set<GameObject> getSelectedObjects() {
        return Collections.unmodifiableSet(selectedObjects);
    }

    /**
     * Set selected objects and signal to editorController
     *
     * @param selectedObjects
     */
    public void setSelectedObjects(Set<GameObject> selectedObjects) {
        this.selectedObjects.clear();
        this.selectedObjects.addAll(selectedObjects);
        editorController.mapChanged();
    }

    /**
     * Select all objects on map
     */
    public void selectAll() {
        selectedObjects.addAll(objects);
        mapChanged();
    }

    private void mapChanged() {
        editorController.mapChanged();
        undoRedoAction.changed();
    }

    /**
     * Paste fragments from clipboard
     */
    public void paste() {
        clipBoard.paste();
    }

    /**
     * Copy selected fragments
     */
    public void copy() {
        clipBoard.copy();
    }

    /**
     * Cut selected fragments
     */
    public void cut() {
        clipBoard.cut();
    }

    /**
     * Redo last changes
     */
    public void redo() {
        undoRedoAction.redo();
    }

    /**
     * Undo last changes
     */
    public void undo() {
        undoRedoAction.undo();
    }

    /**
     * Remove all selected items from the map
     */
    public void removeSelected() {
        selectedObjects.forEach(objects::remove);
        mapChanged();
    }

    public String getName() {
        return name;
    }

    public void addGameObject(GameObject gameObject) {
        objects.add(gameObject);
        mapChanged();
    }

    /**
     * Select objects in region
     *
     * @param x      top left x
     * @param y      top left y
     * @param width  width of rectangle region
     * @param height height of rectangle region
     */
    public void selectObjects(double x, double y, double width, double height, SelectionTool.SelectionMode selectionMode) {
        // If some of elements selection changed this variable will be true
        boolean isComplete = false;
        if (selectionMode == SelectionTool.SelectionMode.NEW_SELECTION) {
            this.selectedObjects.clear();
            isComplete = true;
        }
        for (GameObject object : this.objects) {
            if (object.intersect(x, y, width, height)) {
                if (selectionMode == SelectionTool.SelectionMode.ADDITIONAL_SELECTION && this.selectedObjects.contains(object)) {
                    this.selectedObjects.remove(object);
                } else {
                    this.selectedObjects.add(object);
                }
                isComplete = true;
            }
        }
        if (isComplete) {
            mapChanged();
        }
    }

    /**
     * Move selected objects
     * @param x x offset
     * @param y y offset
     */
    public void moveSelectedObjects(double x, double y) {
        for(GameObject obj: selectedObjects){
            obj.setX(x+obj.getX());
            obj.setY(y + obj.getY());
        }
        if(!selectedObjects.isEmpty()){
            editorController.mapChanged();
        }
    }
}
