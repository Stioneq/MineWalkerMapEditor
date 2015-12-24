package org.laptech.minewalker.mapeditor.gui;

import org.laptech.minewalker.mapeditor.gui.tools.EmptyTool;
import org.laptech.minewalker.mapeditor.gui.tools.GameObjectTool;
import org.laptech.minewalker.mapeditor.gui.tools.SelectionTool;
import org.laptech.minewalker.mapeditor.gui.tools.Tool;
import org.laptech.minewalker.mapeditor.gui.utils.PointConverter;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Objects;
import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;

/**
 * Area for display map view
 * @author rlapin
 */
public class EditorArea extends JPanel{
    private static final Logger LOGGER = getLogger(EditorArea.class.getName());
    public static final Color BGCOLOR = new Color(37, 37, 39);
    public static final EmptyTool EMPTY_TOOL = new EmptyTool();
    public static final Cursor SEL_CURSOR = new Cursor(Cursor.CROSSHAIR_CURSOR);
    public static final Color SEL_FGCOLOR = new Color(255, 0, 0, 255);
    public static final Color SEL_BGCOLOR = new Color(100, 200, 100, 50);
    private static final int TEMP_SIZE = 50;
    private boolean isShowGrid = true;
    private boolean isDragged;
    private PointConverter pointConverter;
    private Rectangle mapBounds = new Rectangle(0,0,1024,768);
    private int gridSize = 50;
    private Color gridColor = new Color(205, 255, 107,100);
    private Tool currentTool = EMPTY_TOOL;
    /**
     * Position and size of adding item , just to preview display
     */
    private Rectangle brushObject = new Rectangle(TEMP_SIZE,TEMP_SIZE);
    /**
     * Rectangle is using as selection region
     */
    private Rectangle selRect = new Rectangle();
    /**
     * True if user is selected region in this moment
     */
    private boolean isSelection;

    public EditorArea() {
        pointConverter = new PointConverter();
        initListeners();

    }

    private void initListeners() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if(SwingUtilities.isRightMouseButton(e)){
                    isDragged = false;

                }
                if(SwingUtilities.isLeftMouseButton(e)){
                    isSelection = false;
                    repaint();
                }
                if(isSelectionTool()) {
                    currentTool.apply(selRect.getX(),selRect.getY(),selRect.getWidth(),selRect.getHeight());
                }
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {

            int x;
            int y;
            /**
             * selection start x
             */
            int sX;
            /**
             * selection start y
             */
            int sY;
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                if(isGameObjectTool()){
                    brushObject.setLocation(e.getX()-TEMP_SIZE/2,e.getY()-TEMP_SIZE/2);

                    repaint();
                }

            }

            @Override
            public void mouseDragged(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    if (!isDragged) {
                        isDragged = true;
                    } else {
                        mapBounds.setLocation((int) (mapBounds.getX() - e.getX() + x), (int) (mapBounds.getY() - e.getY() + y));
                        rangeUpdated();
                    }
                    x = e.getX();
                    y = e.getY();
                }
                if (SwingUtilities.isLeftMouseButton(e) && isSelectionTool()) {
                    if (!isSelection ) {
                        isSelection = true;
                        sX = e.getX();
                        sY = e.getY();

                    } else {
                        int dX = e.getX() - sX;
                        int dY = e.getY() - sY;
                        selRect.setLocation(sX, sY);
                        if(dX<0){
                            selRect.setLocation(e.getX(), (int) selRect.getX());
                            dX *= -1;
                        }
                        if(dY<0){
                            selRect.setLocation((int) selRect.getX(),e.getY());
                            dY *= -1;
                        }
                        selRect.setSize(dX, dY);
                        EditorArea.this.repaint();
                    }
                }
            }
        });
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                rangeUpdated();
            }
        });
        addMouseWheelListener(e -> {
            int notches = e.getWheelRotation();
            Point newPoint = pointConverter.convertFromScreen(e.getPoint());
            if (notches < 0) {
                //zoom in
                mapBounds.setSize((int) (mapBounds.getWidth() / 2), (int) (mapBounds.getHeight() / 2));
            } else {
                //zoom out
                mapBounds.setSize((int) (mapBounds.getWidth() * 2), (int) (mapBounds.getHeight() * 2));
            }
            newPoint.translate(-(int) mapBounds.getWidth() / 2, -(int) mapBounds.getHeight() / 2);
            mapBounds.setLocation(newPoint);
            rangeUpdated();
        });
    }

    private boolean isGameObjectTool() {
        return currentTool instanceof GameObjectTool;
    }

    /**
     * Fires when editorarea changed
     */
    private void rangeUpdated() {
        LOGGER.info("MapArea:" + mapBounds);
        pointConverter.updateMultipliers(getWidth(), getHeight(), mapBounds);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(BGCOLOR);
        g.fillRect(0, 0, getWidth(), getHeight());
        drawGrid(g);
        drawBrush(g);

        g.dispose();
    }

    private void drawBrush(Graphics g) {
        if(isSelection) {
            drawSelection(g);
        }else if(isGameObjectTool()){
            drawGameObjectBrush(g);
        }
    }

    private void drawGameObjectBrush(Graphics g) {
        g.setColor(Color.pink);
        g.fillRect((int) brushObject.getX(), (int) brushObject.getY(), (int) brushObject.getWidth(), (int) brushObject.getHeight());
        System.out.println(brushObject);
    }

    private void drawSelection(Graphics g) {
        g.setColor(SEL_FGCOLOR);
        g.drawRect((int) selRect.getX(), (int) selRect.getY(), (int) selRect.getWidth(), (int) selRect.getHeight());
        g.setColor(SEL_BGCOLOR);
        // Add 1 as innerpadding thats why we substract 2 from width and height
        g.fillRect((int) selRect.getX() + 1, (int) selRect.getY() + 1, (int) selRect.getWidth() - 2, (int) selRect.getHeight() - 2);
    }

    /**
     * Draw grid if showgrid is on
     * @param g Graphics context
     */
    private void drawGrid(Graphics g) {
        g.setColor(gridColor);
        // Used for comparing last drawing line and not draw if dif less than pixel
        int last = -1;
        if(isShowGrid){
            //Draw horizontal lines
            double startX = mapBounds.getX();
            double endX = mapBounds.getMaxX();
            while(startX<endX){
                if((int)startX%gridSize==0) {
                    int x = (int) pointConverter.convertXToScreen(startX);
                    if (Math.abs(x - last) > 1) {
                        last = x;

                        if (contains(x, 0)) {
                            g.drawLine(x, 0, x, getHeight());
                        }
                    }
                }
                startX++;

            }
            last = -1;
            double startY = mapBounds.getY();
            double endY = mapBounds.getMaxY();
            while(startY<endY){
                if((int)startY%gridSize==0) {
                    int y = (int) pointConverter.convertYToScreen(startY);
                    if (Math.abs(y - last) > 1) {
                        last = y;
                        if (contains(0, y)) {
                            g.drawLine(0, y, getWidth(), y);
                        }
                    }
                }
                startY++;
            }
        }

    }

    public void setShowGrid(boolean showGrid) {
        this.isShowGrid = showGrid;
        repaint();
    }

    public void setGridSize(int gridSize) {
        this.gridSize = gridSize;
        repaint();
    }

    public void setGridColor(Color gridColor) {
        this.gridColor = gridColor;
        repaint();
    }

    public void setCurrentTool(Tool currentTool) {
        this.currentTool = currentTool;
        if(isSelectionTool()){
            setCursor(SEL_CURSOR);
        }else{
            setCursor(Cursor.getDefaultCursor());
        }
    }

    private boolean isSelectionTool() {
        return Objects.equals(currentTool.getType(), SelectionTool.TYPE);
    }
}
