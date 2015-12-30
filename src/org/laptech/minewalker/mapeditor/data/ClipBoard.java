package org.laptech.minewalker.mapeditor.data;

import org.laptech.minewalker.mapeditor.data.objects.GameObject;

import java.util.HashSet;
import java.util.Set;

/**
 * Clipboard that stores gameobjects. Use with copy/cut/paste operations
 * @author rlapin
 */
public class ClipBoard {
    /**
     * Elements in clipboard
     */
    private Set<GameObject> elements = new HashSet<>();

    public Set<GameObject> getElements() {
        return elements;
    }
}
