package org.laptech.minewalker.mapeditor.gui.tools;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.IOException;
import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;

/**
 * @author rlapin
 */
public class SelectionTool implements Tool{
    private static final Logger LOGGER = getLogger(SelectionTool.class.getName());
    public static final String IMAGE_PATH = "images/selectiontool.png";
    private Image image;

    public SelectionTool() {
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

    }


}
