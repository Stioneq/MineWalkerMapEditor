package org.laptech.minewalker.mapeditor.gui.tools;

import org.laptech.minewalker.mapeditor.data.objects.Floor;
import org.laptech.minewalker.mapeditor.data.objects.GameObject;
import org.laptech.minewalker.mapeditor.gui.EditorController;

import java.awt.Image;

/**
 * @author rlapin
 */
public class FloorTool extends GameObjectTool {

    public FloorTool(EditorController editorController) {
        super(editorController);
    }

    @Override
    public GameObject getObject(int x, int y, int width, int height) {
        return new Floor(x, y, width, height);
    }

    @Override
    public Image getToolIcon() {
        return null;
    }

    @Override
    public String getTooltip() {
        return null;
    }
}
