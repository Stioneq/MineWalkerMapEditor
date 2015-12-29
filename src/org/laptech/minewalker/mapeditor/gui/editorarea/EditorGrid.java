package org.laptech.minewalker.mapeditor.gui.editorarea;

import org.laptech.minewalker.mapeditor.gui.utils.PointConverter;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Editor editorArea grid
 * @author rlapin
 */
public class EditorGrid {
    private boolean isShowGrid = true;
    private int gridSize = 50;
    private Color gridColor = new Color(205, 255, 107, 100);
    private EditorArea editorArea;

    public EditorGrid(EditorArea editorArea) {
        this.editorArea = editorArea;
    }

    /**
     * Draw grid
     *
     * @param g Graphics context
     */
    public void draw(Graphics g) {
        g.setColor(gridColor);

        // Used for comparing last drawing line and not draw if dif less than pixel


        if (isShowGrid) {

            drawVerticalLines(g);
            drawHorizontalLines(g);
        }

    }

    /**
     * draw horizontal lines
     * @param g
     */
    private void drawHorizontalLines(Graphics g) {
        int last = -1;
        PointConverter pointConverter = editorArea.getPointConverter();
        Rectangle mapBounds = editorArea.getMapBounds();
        double startY = mapBounds.getY();
        double endY = mapBounds.getMaxY();
        while (startY < endY) {
            if ((int) startY % gridSize == 0) {
                int y = pointConverter.convertYToScreen(startY);
                if (Math.abs(y - last) > 1) {
                    last = y;
                    if (editorArea.contains(0, y)) {
                        g.drawLine(0, y, editorArea.getWidth(), y);
                    }
                }
            }
            startY++;
        }
    }

    /**
     * Draw vertical lines
     * @param g
     */
    private void drawVerticalLines(Graphics g) {
        int last = -1;
        PointConverter pointConverter = editorArea.getPointConverter();
        Rectangle mapBounds = editorArea.getMapBounds();
        double startX = mapBounds.getX();
        double endX = mapBounds.getMaxX();
        while (startX < endX) {
            if ((int) startX % gridSize == 0) {
                int x = pointConverter.convertXToScreen(startX);
                if (Math.abs(x - last) > 1) {
                    last = x;

                    if (editorArea.contains(x, 0)) {
                        g.drawLine(x, 0, x, editorArea.getHeight());
                    }
                }
            }
            startX++;

        }
    }

    /**
     * Set showgrid
     * @param showGrid
     */
    public void setShowGrid(boolean showGrid) {
        this.isShowGrid = showGrid;
        editorArea.repaint();

    }

    /**
     * Define grid size
     * @param gridSize
     */
    public void setGridSize(int gridSize) {
        this.gridSize = gridSize;
        editorArea.repaint();
    }

    /**
     * Set grid color
     * @param gridColor
     */
    public void setGridColor(Color gridColor) {
        this.gridColor = gridColor;
        editorArea.repaint();
    }

}
