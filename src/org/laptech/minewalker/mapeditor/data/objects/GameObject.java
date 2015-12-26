package org.laptech.minewalker.mapeditor.data.objects;

import org.laptech.minewalker.mapeditor.gui.tools.Drawable;
import org.laptech.minewalker.mapeditor.gui.tools.GameObjectTool;

/**
 * Entity on gamemap
 *
 * @author rlapin
 */
public abstract class GameObject{
    private double x;
    private double y;
    private double width;
    private double height;
    private Drawable drawable;
    public GameObject(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

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

    /**
     * append drawable
     * @param drawable
     * @return
     */
    public GameObject with(Drawable drawable){
        this.drawable = drawable;
        return this;
    }

    public Drawable getDrawable() {
        return drawable;
    }
}
