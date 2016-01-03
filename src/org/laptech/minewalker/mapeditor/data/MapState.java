package org.laptech.minewalker.mapeditor.data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import org.laptech.minewalker.mapeditor.data.objects.GameObject;

import java.util.Set;

/**
 * Map state is a combination of selected objects and gameobjects
 * @author rlapin
 */
public class MapState {
    private final String changeTitle;
    private ObservableSet<GameObject> objects = FXCollections.observableSet();
    private ObservableSet<GameObject> selectedObjects = FXCollections.observableSet();

    public MapState(Set<GameObject> objects, Set<GameObject> selectedObjects, String changeTitle) {
        this.changeTitle = changeTitle;
        this.objects.addAll(objects);
        this.selectedObjects.addAll(selectedObjects);
    }

    public ObservableSet<GameObject> getObjects() {
        return objects;
    }

    public ObservableSet<GameObject> getSelectedObjects() {
        return selectedObjects;
    }

    public String getChangeTitle() {
        return changeTitle;
    }
}
