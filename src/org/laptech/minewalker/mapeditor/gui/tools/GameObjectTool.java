package org.laptech.minewalker.mapeditor.gui.tools;

import org.laptech.minewalker.mapeditor.data.objects.GameObject;
import org.laptech.minewalker.mapeditor.gui.EditorController;

import java.util.EnumSet;

/**
 * Tool for adding gameobjects to map
 *
 * @author rlapin
 */
public abstract class GameObjectTool implements Tool{
    private EditorController editorController;
    private double width = 50;
    private double height = 50;
    public GameObjectTool(EditorController editorController) {
        this.editorController = editorController;
    }

    @Override
    public void apply(double x, double y, double width, double height) {
        editorController.getMap().addGameObject(getObject(x,y,getWidth(),getHeight()).with(this));
    }

    @Override
    public void apply(double x, double y) {
        apply(x, y, getWidth(), getHeight());
    }

    public abstract GameObject getObject(double x, double y, double width, double height);

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
    @Override
    public EnumSet<ToolProperty> getProperties() {
        return EnumSet.of(ToolProperty.HEIGHT,ToolProperty.WIDTH);
    }

}
