package org.laptech.minewalker.mapeditor.data;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import org.laptech.minewalker.mapeditor.data.objects.GameObject;
import org.laptech.minewalker.mapeditor.gui.EditorController;

import java.util.List;
import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;

/**
 * Game map contains gameobjects
 * @author rlapin
 */
public class Map {
    private static final Logger LOGGER = getLogger(Map.class.getName());
    private ObservableList<GameObject> objects = FXCollections.observableArrayList();

    public Map(EditorController editorController) {
        objects.addListener(new ListChangeListener<GameObject>() {
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
}
