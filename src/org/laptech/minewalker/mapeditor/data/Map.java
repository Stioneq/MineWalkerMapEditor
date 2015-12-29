package org.laptech.minewalker.mapeditor.data;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.collections.SetChangeListener;
import org.laptech.minewalker.mapeditor.data.objects.GameObject;
import org.laptech.minewalker.mapeditor.gui.EditorController;

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
    private ObservableList<GameObject> objects = FXCollections.observableArrayList();
    private ObservableSet<GameObject> selectedObjects = FXCollections.observableSet();
    public Map(EditorController editorController) {
        objects.addListener(new ListChangeListener<GameObject>() {
            @Override
            public void onChanged(Change<? extends GameObject> c) {
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

    public List<GameObject> getObjects() {
        return objects;
    }

    public Set<GameObject> getSelectedObjects() {
        return selectedObjects;
    }
}
