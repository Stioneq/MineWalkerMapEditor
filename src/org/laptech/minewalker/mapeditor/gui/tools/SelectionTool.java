package org.laptech.minewalker.mapeditor.gui.tools;

import org.laptech.minewalker.mapeditor.data.Map;
import org.laptech.minewalker.mapeditor.data.objects.GameObject;
import org.laptech.minewalker.mapeditor.gui.EditorController;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.Set;
import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;

/**
 * @author rlapin
 */
public class SelectionTool implements Tool{
    private static final Logger LOGGER = getLogger(SelectionTool.class.getName());
    public static final String IMAGE_PATH = "images/selectiontool.png";
    public static final String TYPE = "selection";
    public static final Color SEL_FGCOLOR = new Color(255, 0, 0, 255);
    public static final Color SEL_BGCOLOR = new Color(100, 200, 100, 50);
    private Image image;
    private EditorController editorController;
    /**
     * Define behaviour of tool
     * New selection means that selection list is cleared before applying tool
     */
    private SelectionMode selectionMode = SelectionMode.NEW_SELECTION;
    public SelectionTool(EditorController editorController) {
        this.editorController = editorController;
        try {
            image = ImageIO.read(SelectionTool.class.getClassLoader().getResourceAsStream(IMAGE_PATH));
        } catch (IOException e) {
            LOGGER.severe("Cannot load image "+ IMAGE_PATH);
        }
    }

    @Override
    public Image getToolIcon() {
        return image;
    }

    @Override
    public String getTooltip() {
        return "Selection tool";
    }

    @Override
    public void apply(double x, double y, double width, double height) {
        Map map = editorController.getMap();
        Set<GameObject> selectedObjects = map.getSelectedObjects();
        if(selectionMode == SelectionMode.NEW_SELECTION) {
            selectedObjects.clear();
        }
        for(GameObject object : map.getObjects()){
            if(object.intersect(x,y,width,height)){
                if(selectionMode == SelectionMode.ADDITIONAL_SELECTION && selectedObjects.contains(object)){
                    selectedObjects.remove(object);
                }else {
                    selectedObjects.add(object);
                }
            }
        }

    }

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public void apply(double x, double y) {
        apply(x,y,1,1);
    }


    @Override
    public void draw(Graphics g, int x, int y, int width, int height) {
        g.setColor(SEL_FGCOLOR);
        g.drawRect(x, y, width, height);
        g.setColor(SEL_BGCOLOR);
        // Add 1 as innerpadding thats why we substract 2 from width and height
        g.fillRect(x + 1, y + 1, width - 2, height - 2);
    }

    /**
     * Set new selection mode
     * @param selectionMode new selection mode
     */
    public void setSelectionMode(SelectionMode selectionMode) {
        this.selectionMode = selectionMode;
    }

    public enum SelectionMode {
        ADDITIONAL_SELECTION, NEW_SELECTION;
    }
}
