package org.laptech.minewalker.mapeditor.gui.editorarea;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

/**
 * Display intersections area
 *
 * @author rlapin
 */
public class EditorIntersections {
    public static final Color INSERSECT_COLOR = new Color(200, 0, 0, 200);
    /**
     * Store list of intersections region
     */
    private List<Rectangle> intersectionFigures = new ArrayList<>();
    private EditorArea editorArea;

    public EditorIntersections(EditorArea editorArea) {
        this.editorArea = editorArea;
    }

    public void draw(Graphics g) {
        g.setColor(INSERSECT_COLOR);
        for (Rectangle rectangle : intersectionFigures) {
            g.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        }
    }

    /**
     * clear intersections
     */
    public void clear() {
        intersectionFigures.clear();
    }

    /**
     * add new intersection
     *
     * @param rectangle rectangle that intersects
     */
    public void add(Rectangle rectangle) {
        intersectionFigures.add(rectangle);
    }
}
