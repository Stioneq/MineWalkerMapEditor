package org.laptech.minewalker.mapeditor.data;

import javafx.collections.ObservableSet;
import org.laptech.minewalker.mapeditor.data.objects.GameObject;

import java.util.HashSet;
import java.util.Set;

/**
 * Clipboard that stores gameobjects. Use with copy/cut/paste operations
 * @author rlapin
 */
public class ClipBoard implements IClipBoard{
    /**
     * Elements in clipboard
     */
    private Set<GameObject> elements = new HashSet<>();

    public Set<GameObject> getElements() {
        return elements;
    }

    public void add(ObservableSet<GameObject> objects) {
        for (GameObject object : objects) {
            try {
                elements.add((GameObject) object.clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void cut() {

    }

    @Override
    public void paste() {

    }

    @Override
    public void copy() {

    }
}
