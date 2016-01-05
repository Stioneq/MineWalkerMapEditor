package org.laptech.minewalker.mapeditor.gui.editorarea;

import org.laptech.minewalker.mapeditor.data.Map;
import org.laptech.minewalker.mapeditor.data.objects.GameObject;
import org.laptech.minewalker.mapeditor.gui.MainWindow;
import org.laptech.minewalker.mapeditor.gui.tools.EmptyTool;
import org.laptech.minewalker.mapeditor.gui.tools.GameObjectTool;
import org.laptech.minewalker.mapeditor.gui.tools.SelectionTool;
import org.laptech.minewalker.mapeditor.gui.tools.Tool;
import org.laptech.minewalker.mapeditor.gui.utils.PointConverter;


import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;

/**
 * Area for display map view
 *
 * @author rlapin
 */
public class EditorArea extends JPanel {
    public static final Color BGCOLOR = new Color(37, 37, 39);
    public static final EmptyTool EMPTY_TOOL = new EmptyTool();
    public static final Cursor SEL_CURSOR = new Cursor(Cursor.CROSSHAIR_CURSOR);
    private static final Logger LOGGER = getLogger(EditorArea.class.getName());
    /**
     * Multiplicor for distance using in magnetized
     */
    public static final double magneticMul = 0.1;
    /**
     * if true objects is magnetized to grid
     */
    private boolean isMagnets = true;
    private static final Cursor MOVE_CURSOR = new Cursor(Cursor.MOVE_CURSOR);
    /**
     * Used while displaying game object brush
     */
    private static Cursor blankCursor;

    static {
        blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB), new Point(0, 0), "blank");
    }

    //Fields used in displaying gameobject tool brush
    private boolean isDrawBrush = false;
    private EditorGrid grid;
    /**
     * Tool width if it is a gameobjectool in screen units
     */
    private int toolWidth;
    /**
     * Tool height if it is a gameobjectool in screen units
     */
    private int toolHeight;
    // END


    private boolean isDragged;
    private PointConverter pointConverter;
    private Rectangle mapBounds = new Rectangle(0, 0, 1024, 768);

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
    private EditorIntersections interArea;
    /**
     * true if move
     */
    private boolean isMove;

    public EditorArea(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        grid = new EditorGrid(this);
        interArea = new EditorIntersections(this);
        pointConverter = new PointConverter();
        initListeners();

    }

    private void initListeners() {

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    isDragged = false;

                }
                if (SwingUtilities.isLeftMouseButton(e)) {
                    isMove = false;
                    if (isSelectionTool()) {
                        if (e.isControlDown()) {
                            ((SelectionTool) currentTool).setSelectionMode(SelectionTool.SelectionMode.ADDITIONAL_SELECTION);
                        } else {
                            ((SelectionTool) currentTool).setSelectionMode(SelectionTool.SelectionMode.NEW_SELECTION);
                        }
                        if (isSelection) {
                            currentTool.apply(pointConverter.convertXFromScreen((int) selRect.getX()), pointConverter.convertYFromScreen((int) selRect.getY()), pointConverter.convertXUnitsFromScreen((int) selRect.getWidth()), pointConverter.convertYUnitsFromScreen((int) selRect.getHeight()));
                        } else {
                            currentTool.apply(pointConverter.convertXFromScreen(e.getX()), pointConverter.convertYFromScreen(e.getY()));
                        }
                    }
                    isSelection = false;
                    repaint();
                }


            }

            @Override
            public void mousePressed(MouseEvent e) {
                isMove = false;
                if (SwingUtilities.isLeftMouseButton(e)) {
                    if (isGameObjectTool()) {
                        double realX = pointConverter.convertXFromScreen(e.getX());
                        double realY = pointConverter.convertYFromScreen(e.getY());
                        double width = ((GameObjectTool) currentTool).getWidth();
                        double height = ((GameObjectTool) currentTool).getHeight();
                        if(isMagnets){

                            int modX = (int) (realX - width / 2) % grid.getGridSize();
                            int modY = (int) (realY - height / 2) % grid.getGridSize();
                            if(modX <grid.getGridSize()* magneticMul){
                                realX = (int)realX-modX;
                            }else if(modX > grid.getGridSize()*(1-magneticMul)){
                                realX = (int)realX+(grid.getGridSize()-modX);
                            }
                            if(modY <grid.getGridSize()* magneticMul){
                                realY = (int)realY-modY;
                            }else if(modY > grid.getGridSize()*(1-magneticMul)){
                                realY = (int)realY+(grid.getGridSize()-modY);
                            }


                        }
                        if (!isIntersect(pointConverter.convertXToScreen(realX),pointConverter.convertYToScreen(realY))) {
                            currentTool.apply(realX-width/2, realY-height/2);
                        }else{
                            System.out.println(realX+":"+realY);
                        }
                       repaint();
                    }

                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (isGameObjectTool()) {
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
            /**
             * move x
             */
            int mX;
            /**
             * move y
             */
            int mY;
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                if (isGameObjectTool()) {

                    double realX = pointConverter.convertXFromScreen(e.getX());
                    double realY = pointConverter.convertYFromScreen(e.getY());
                    double width = ((GameObjectTool) currentTool).getWidth();
                    double height = ((GameObjectTool) currentTool).getHeight();
                    if(isMagnets){
                        boolean checkMagnet = false;
                        int modX = (int) (realX - width / 2) % grid.getGridSize();
                        int modY = (int) (realY - height / 2) % grid.getGridSize();
                        if(modX <grid.getGridSize()* magneticMul){
                           realX = (int)realX-modX;
                            checkMagnet = true;
                        }else if(modX > grid.getGridSize()*(1-magneticMul)){
                            realX = (int)realX+(grid.getGridSize()-modX);
                            checkMagnet = true;
                        }
                        if(modY <grid.getGridSize()* magneticMul){
                            realY = (int)realY-modY;
                            checkMagnet = true;
                        }else if(modY > grid.getGridSize()*(1-magneticMul)){
                            realY = (int)realY+(grid.getGridSize()-modY);
                            checkMagnet = true;
                        }



                    }
                    interArea.clear();
                    Set<GameObject> objects = mainWindow.getController().getMap().getObjects();
                    for (GameObject gameObject : objects) {
                        if (gameObject.intersect(realX - width / 2, realY - height / 2, width, height)) {
                            Rectangle2D intersection = gameObject.createIntersection(realX - width / 2, realY - height / 2, width, height);
                            interArea.add(new Rectangle(pointConverter.convertXToScreen(intersection.getX()), pointConverter.convertYToScreen(intersection.getY()), pointConverter.convertXUnitsToScreen(intersection.getWidth()), pointConverter.convertYUnitsToScreen(intersection.getHeight())));
                        }
                    }
                    brushPoint.setLocation(pointConverter.convertXToScreen(realX), pointConverter.convertYToScreen(realY));
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
                if (SwingUtilities.isLeftMouseButton(e)) {
                    if (isSelectionTool()) {
                        if (!isSelection) {
                            isSelection = true;
                            sX = e.getX();
                            sY = e.getY();

                        } else {
                            int dX = e.getX() - sX;
                            int dY = e.getY() - sY;
                            selRect.setLocation(sX, sY);
                            if (dX < 0) {
                                selRect.setLocation(e.getX(), (int) selRect.getX());
                                dX *= -1;
                            }
                            if (dY < 0) {
                                selRect.setLocation((int) selRect.getX(), e.getY());
                                dY *= -1;
                            }
                            selRect.setSize(dX, dY);
                            EditorArea.this.repaint();
                        }
                    } else if (isMoveTool()) {
                        if (!isMove) {
                            isMove = true;
                            mX = e.getX();
                            mY = e.getY();
                        } else {
                            int dX = e.getX() - mX;
                            int dY = e.getY() - mY;
                            if(Math.abs(dX)>1 || Math.abs(dY)>1) {
                                currentTool.apply(pointConverter.convertXFromScreen(dX)-pointConverter.convertXFromScreen(0), pointConverter.convertYFromScreen(dY)-pointConverter.convertYFromScreen(0));
                                mX = e.getX();
                                mY = e.getY();
                            }
                        }
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
            Point newPoint = new Point((int) pointConverter.convertXFromScreen((int) e.getPoint().getX()), (int) pointConverter.convertYFromScreen((int) e.getPoint().getY()));
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

    /**
     * Check for insersections
     *
     * @param sX screen x
     * @param sY screen y
     * @return true if brush intersects figures
     */
    private boolean isIntersect(int sX, int sY) {
        double realX = pointConverter.convertXFromScreen(sX+1);
        double realY = pointConverter.convertYFromScreen(sY+1);
        double width = ((GameObjectTool) currentTool).getWidth()-2;
        double height = ((GameObjectTool) currentTool).getHeight()-2;
        Set<GameObject> objects = mainWindow.getController().getMap().getObjects();
        for (GameObject gameObject : objects) {
            if (gameObject.intersect(realX - width / 2, realY - height / 2, width, height)) {
                return true;
            }
        }
        return false;
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
        if (isGameObjectTool()) {
            GameObjectTool gameObjectTool = (GameObjectTool) currentTool;
            toolWidth = pointConverter.convertXUnitsToScreen(gameObjectTool.getWidth());
            toolHeight = pointConverter.convertYUnitsToScreen(gameObjectTool.getHeight());
        }
        interArea.clear();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(BGCOLOR);
        g.fillRect(0, 0, getWidth(), getHeight());
        drawMap(g);
        grid.draw(g);
        drawBrush(g);
        interArea.draw(g);
        g.dispose();
    }


    private void drawMap(Graphics gr) {
        Graphics2D g = (Graphics2D) gr;
        Map map = mainWindow.getController().getMap();
        g.setColor(new Color(50, 200, 200, 255));
        Stroke temp = g.getStroke();
        g.setStroke(new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f));
        for (GameObject gameObject : map.getObjects()) {
            int x = pointConverter.convertXToScreen(gameObject.getX());
            int y = pointConverter.convertYToScreen(gameObject.getY());
            int width = pointConverter.convertXUnitsToScreen(gameObject.getWidth());
            int height = pointConverter.convertYUnitsToScreen(gameObject.getHeight());
            if (contains(x, y) && contains(x + width, y + width)) {
                gameObject.getDrawable().draw(g, x, y, width, height);
                if (map.getSelectedObjects().contains(gameObject)) {
                    g.drawRect(x, y, width, height);
                }
            }
        }
        g.setStroke(temp);
    }

    private void drawBrush(Graphics g) {
        if (isSelection) {
            currentTool.draw(g, (int) selRect.getX(), (int) selRect.getY(), (int) selRect.getWidth(), (int) selRect.getHeight());
        } else if (isDrawBrush && isGameObjectTool()) {
            currentTool.draw(g, (int) brushPoint.getX() - toolWidth / 2, (int) brushPoint.getY() - toolHeight / 2, toolWidth, toolHeight);
        }
    }


    public void setCurrentTool(Tool currentTool) {
        this.currentTool = currentTool;
        if (isSelectionTool()) {
            setCursor(SEL_CURSOR);
        } else if (isMoveTool()) {
            setCursor(MOVE_CURSOR);
        } else if (isGameObjectTool()) {
            GameObjectTool gameObjectTool = (GameObjectTool) currentTool;
            toolWidth = pointConverter.convertXUnitsToScreen(gameObjectTool.getWidth());
            toolHeight = pointConverter.convertYUnitsToScreen(gameObjectTool.getHeight());
            setCursor(blankCursor);
        }
    }

    private boolean isMoveTool() {
        return currentTool.getType().equals("move");
    }

    private boolean isSelectionTool() {
        return Objects.equals(currentTool.getType(), SelectionTool.TYPE);
    }


    public PointConverter getPointConverter() {
        return pointConverter;
    }

    public EditorGrid getGrid() {
        return grid;
    }

    public Rectangle getMapBounds() {
        return mapBounds;
    }

    public void setMagnetized(boolean isMagnets) {
        this.isMagnets = isMagnets;
    }
}
