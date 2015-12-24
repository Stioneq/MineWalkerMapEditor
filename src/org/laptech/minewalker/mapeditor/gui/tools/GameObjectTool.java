package org.laptech.minewalker.mapeditor.gui.tools;

import org.laptech.minewalker.mapeditor.data.objects.GameObject;
import org.laptech.minewalker.mapeditor.gui.EditorController;

/**
 * Tool for adding gameobjects to map
 *
 * @author rlapin
 */
public abstract class GameObjectTool implements Tool {
    private EditorController editorController;

    public GameObjectTool(EditorController editorController) {
        this.editorController = editorController;
    }

    @Override
    public void apply(double x, double y, double width, double height) {
        editorController.getMap().getObjects().add(getObject(x,y,width,height));
    }

    public abstract GameObject getObject(double x, double y, double width, double height);
}
