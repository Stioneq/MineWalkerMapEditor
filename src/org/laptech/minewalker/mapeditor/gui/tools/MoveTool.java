package org.laptech.minewalker.mapeditor.gui.tools;

import org.laptech.minewalker.mapeditor.gui.EditorController;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.EnumSet;
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
    public EnumSet<ToolProperty> getProperties() {
        return EnumSet.noneOf(ToolProperty.class);
    }


    @Override
    public void draw(Graphics g, int x, int y, int width, int height) {

    }
}
