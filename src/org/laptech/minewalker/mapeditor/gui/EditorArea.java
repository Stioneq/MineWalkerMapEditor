package org.laptech.minewalker.mapeditor.gui;

import org.laptech.minewalker.mapeditor.gui.utils.PointConverter;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;

/**
 * Area for display map view
 * @author rlapin
 */
public class EditorArea extends JPanel{
    private static final Logger LOGGER = getLogger(EditorArea.class.getName());
    public static final Color BGCOLOR = new Color(37, 37, 39);
    public static final Color GRIDCOLOR = new Color(205, 255, 107,100);
    private boolean isShowGrid = true;
    private boolean isDragged;
    private PointConverter pointConverter;
    private Rectangle mapBounds = new Rectangle(0,0,1024,768);
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
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {

            int x;
            int y;
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                if(SwingUtilities.isRightMouseButton(e)){
                    if(!isDragged){
                        isDragged = true;
                    }else{
                        mapBounds.setLocation((int)(mapBounds.getX()-e.getX()+x),(int)(mapBounds.getY()-e.getY()+y));
                        rangeUpdated();
                    }
                    x = e.getX();
                    y = e.getY();
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
            if(notches < 0){
                //zoom in
                mapBounds.setSize((int) (mapBounds.getWidth() / 2), (int) (mapBounds.getHeight() / 2));
            } else {
                //zoom out
                mapBounds.setSize((int) (mapBounds.getWidth() * 2), (int) (mapBounds.getHeight() * 2));
            }
            newPoint.translate(-(int)mapBounds.getWidth()/2,-(int)mapBounds.getHeight()/2);
            mapBounds.setLocation(newPoint);
            rangeUpdated();
        });
    }

    /**
     * Fires when editorarea changed
     */
    private void rangeUpdated() {
        LOGGER.info("MapArea:"+mapBounds);
        pointConverter.updateMultipliers(getWidth(), getHeight(), mapBounds);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(BGCOLOR);
        g.fillRect(0, 0, getWidth(), getHeight());
        drawGrid(g);
    }

    /**
     * Draw grid if showgrid is on
     * @param g Graphics context
     */
    private void drawGrid(Graphics g) {
        g.setColor(GRIDCOLOR);
        // Used for comparing last drawing line and not draw if dif less than pixel
        int last = -1;
        if(isShowGrid){
            //Draw horizontal lines
            double startX = mapBounds.getX();
            double endX = mapBounds.getMaxX();
            while(startX<endX){
                if((int)startX%50==0) {
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
                if((int)startY%50==0) {
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
       // g.dispose();
    }

}
