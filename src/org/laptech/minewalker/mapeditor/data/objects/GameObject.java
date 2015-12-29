package org.laptech.minewalker.mapeditor.data.objects;

import org.laptech.minewalker.mapeditor.gui.tools.Drawable;
import org.laptech.minewalker.mapeditor.gui.tools.GameObjectTool;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

/**
 * Entity on gamemap
 *
 * @author rlapin
 */
public abstract class GameObject implements HasIntersection, HasCollision{
    private static final double EPS = 1E-4;
    private Rectangle2D.Double rectangle = new Rectangle2D.Double();
    private Drawable drawable;
    public GameObject(double x, double y, double width, double height) {
        rectangle.setRect(x,y,width,height);
    }

    public double getX() {
        return rectangle.x;
    }

    public void setX(double x) {
        rectangle.x = x;
    }

    public double getY() {
        return rectangle.y;
    }

    public void setY(double y) {
        rectangle.y = y;
    }

    public double getWidth() {
        return rectangle.width;
    }

    public void setWidth(double width) {
        rectangle.width = width;
    }

    public double getHeight() {
        return rectangle.height;
    }

    public void setHeight(double height) {
        rectangle.height = height;
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

    @Override
    public boolean intersect(double x, double y,double width, double height) {
        return rectangle.intersects(x,y,width,height);
    }

    public Rectangle2D createIntersection(double x, double y,double width, double height){
        return rectangle.createIntersection(new Rectangle2D.Double(x,y,width,height));
    }

    @Override
    public boolean collide(double x, double y) {
        return rectangle.contains(x,y);
    }

    @Override
    public boolean collide(double x, double y, double width, double height) {
        return rectangle.contains(x,y,width,height);
    }


}
