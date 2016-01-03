package org.laptech.minewalker.mapeditor.data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import org.laptech.minewalker.mapeditor.data.objects.GameObject;

import java.util.HashSet;
import java.util.Set;

/**
 * Map state is a combination of selected objects and gameobjects
 * @author rlapin
 */
public class MapState {
    private final String changeTitle;
    private Set<GameObject> objects = new HashSet<>();
    private Set<GameObject> selectedObjects = new HashSet<>();

    public MapState(Set<GameObject> objects, Set<GameObject> selectedObjects, String changeTitle) {
        this.changeTitle = changeTitle;
        this.objects.addAll(objects);
        this.selectedObjects.addAll(selectedObjects);
    }

    public Set<GameObject> getObjects() {
        return objects;
    }

    public Set<GameObject> getSelectedObjects() {
        return selectedObjects;
    }

    public String getChangeTitle() {
        return changeTitle;
    }
}
