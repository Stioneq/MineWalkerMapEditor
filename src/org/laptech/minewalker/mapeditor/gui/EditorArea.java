package org.laptech.minewalker.mapeditor.gui;

import org.laptech.minewalker.mapeditor.data.objects.GameObject;
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
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.util.List;
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

    //Fields used in displaying gameobject tool brush
    private boolean isDrawBrush = false;
    /**
     * Used while displaying game object brush
     */
    private static Cursor blankCursor;
    static {
        blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB), new Point(0, 0), "blank");
    }

    /**
     * Tool width if it is a gameobjectool in screen units
     */
    private int toolWidth;
    /**
     * Tool height if it is a gameobjectool in screen units
     */
    private int toolHeight;
    // END
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
    private Point brushPoint = new Point();
    /**
     * Rectangle is using as selection region
     */
    private Rectangle selRect = new Rectangle();
    /**
     * True if user is selected region in this moment
     */
    private boolean isSelection;
    private MainWindow mainWindow;


    public EditorArea(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
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
                }else if(isGameObjectTool()){
                    currentTool.apply(pointConverter.convertXFromScreen(e.getX()-toolWidth/2),pointConverter.convertYFromScreen(e.getY() - toolHeight / 2));
                }

            }
            @Override
            public void mouseExited(MouseEvent e) {
                if(isGameObjectTool()){
                    isDrawBrush = false;
                    repaint();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                isDrawBrush = true;
                repaint();
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
                    brushPoint.setLocation(e.getX(), e.getY());
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
        if(isGameObjectTool()) {
            GameObjectTool gameObjectTool = (GameObjectTool) currentTool;
            toolWidth = (int) pointConverter.convertXUnitsToScreen(gameObjectTool.getWidth());
            toolHeight = (int) pointConverter.convertYUnitsToScreen(gameObjectTool.getHeight());
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(BGCOLOR);
        g.fillRect(0, 0, getWidth(), getHeight());
        drawMap(g);
        drawGrid(g);
        drawBrush(g);
        g.dispose();
    }

    private void drawMap(Graphics g) {
        for(GameObject gameObject : mainWindow.getController().getMap().getObjects()){
            int x = (int) pointConverter.convertXToScreen(gameObject.getX());
            int y = (int) pointConverter.convertYToScreen(gameObject.getY());
            int width = (int) pointConverter.convertXUnitsToScreen(gameObject.getWidth());
            int height = (int) pointConverter.convertYUnitsToScreen(gameObject.getHeight());
            if(contains(x,y) && contains(x+width,y+width)) {
                gameObject.getDrawable().draw(g, x,y,width,height);
            }
        }
    }

    private void drawBrush(Graphics g) {
        if(isSelection) {
            currentTool.draw(g,(int) selRect.getX(), (int) selRect.getY(), (int) selRect.getWidth(), (int) selRect.getHeight());
        }else if(isDrawBrush && isGameObjectTool()){
            currentTool.draw(g,(int) brushPoint.getX()-toolWidth/2, (int) brushPoint.getY()-toolHeight/2, toolWidth, toolHeight);
        }
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
        }else if(isGameObjectTool()){
            GameObjectTool gameObjectTool = (GameObjectTool) currentTool;
            toolWidth = (int) pointConverter.convertXUnitsToScreen(gameObjectTool.getWidth());
            toolHeight = (int) pointConverter.convertYUnitsToScreen(gameObjectTool.getHeight());
            setCursor(blankCursor);
        }
    }

    private boolean isSelectionTool() {
        return Objects.equals(currentTool.getType(), SelectionTool.TYPE);
    }


}
