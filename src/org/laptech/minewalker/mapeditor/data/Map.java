package org.laptech.minewalker.mapeditor.data;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.collections.SetChangeListener;
import org.laptech.minewalker.mapeditor.data.objects.GameObject;
import org.laptech.minewalker.mapeditor.gui.EditorController;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;

/**
 * Game map contains gameobjects
 * @author rlapin
 */
public class Map {
    private static final Logger LOGGER = getLogger(Map.class.getName());
    private ObservableSet<GameObject> objects = FXCollections.observableSet();
    private ObservableSet<GameObject> selectedObjects = FXCollections.observableSet();
    private ClipBoard clipBoard = new ClipBoard();
    /**
     * State of map for undo/redo
     */
    private List<Map> states = new LinkedList<>();
    private int currentState;
    /**
     * Name of the map
     */
    private String name;

    public Map(EditorController editorController) {
        objects.addListener(new SetChangeListener<GameObject>() {
            @Override
            public void onChanged(Change<? extends GameObject> c) {
                //states.addAll(c.getSet());
                editorController.mapChanged();
                LOGGER.info("map changed");
            }
        });
        selectedObjects.addListener(new SetChangeListener<GameObject>() {
            @Override
            public void onChanged(Change<? extends GameObject> c) {
                editorController.mapChanged();
                LOGGER.info("map changed");
            }
        });

    }

    public Set<GameObject> getObjects() {
        return objects;
    }

    public Set<GameObject> getSelectedObjects() {
        return selectedObjects;
    }

    /**
     * Select all objects on map
     */
    public void selectAll() {
       selectedObjects.addAll(objects);
    }

    /**
     * Paste fragments from clipboard
     */
    public void paste() {

    }

    /**
     * Copy selected fragments
     */
    public void copy() {
        clipBoard.getElements().clear();
        clipBoard.getElements().addAll(selectedObjects);
    }

    /**
     * Cut selected fragments
     */
    public void cut() {
        copy();
        removeSelected();
    }

    /**
     * Redo last changes
     */
    public void redo() {

    }

    /**
     * Undo last changes
     */
    public void undo() {
    }

    /**
     * Remove all selected items from the map
     */
    public void removeSelected(){
        for(GameObject obj : selectedObjects){
            objects.remove(obj);
        }
    }

    public String getName() {
        return name;
    }
}
