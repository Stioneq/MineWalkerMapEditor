package org.laptech.minewalker.mapeditor.gui.tools;

import org.laptech.minewalker.mapeditor.gui.EditorController;
import org.laptech.minewalker.mapeditor.gui.tools.properties.ToolProperty;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Collections;
import java.util.Set;
import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;

/**
 * Tool for moving selected objects
 * @author rlapin
 */
public class MoveTool implements Tool{
    private static final Logger LOGGER = getLogger(SelectionTool.class.getName());
    public static final String IMAGE_PATH = "images/movetool.png";
    public static final String TYPE = "move";
    private final EditorController editorController;
    private Image image;
    public MoveTool(EditorController editorController) {
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
        return "Move tool";
    }

    @Override
    public void apply(double x, double y, double width, double height) {

    }

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public void apply(double x, double y) {

    }

    @Override
    public Set<ToolProperty> getProperties() {
        return Collections.emptySet();
    }

    @Override
    public void addPropertyChangeListener(org.laptech.minewalker.mapeditor.gui.tools.properties.PropertyChangeListener propertyChangeListener) {

    }


    @Override
    public void draw(Graphics g, int x, int y, int width, int height) {

    }
}
