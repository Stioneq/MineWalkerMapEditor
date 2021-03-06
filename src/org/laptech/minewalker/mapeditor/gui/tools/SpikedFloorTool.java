package org.laptech.minewalker.mapeditor.gui.tools;

import org.laptech.minewalker.mapeditor.data.objects.Floor;
import org.laptech.minewalker.mapeditor.data.objects.GameObject;
import org.laptech.minewalker.mapeditor.gui.EditorController;

import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;

/**
 * @author rlapin
 */
public class SpikedFloorTool extends GameObjectTool {
    public static final String IMAGE_PATH = "images/spikedfloortool.png";
    public static final String TYPE = "spikedfloor";
    private static final Logger LOGGER = getLogger(SelectionTool.class.getName());
    private Image image;

    public SpikedFloorTool(EditorController editorController) {
        super(editorController);
        try {
            image = ImageIO.read(SelectionTool.class.getClassLoader().getResourceAsStream(IMAGE_PATH));

        } catch (IOException e) {
            LOGGER.severe("Cannot load image " + IMAGE_PATH);
        }
    }

    @Override
    public GameObject getObject(double x, double y, double width, double height) {
        return new Floor(x, y, width, height);
    }

    @Override
    public Image getToolIcon() {
        return image;
    }

    @Override
    public String getTooltip() {
        return "Spiked Floor tool";
    }

    @Override
    public String getType() {
        return TYPE;
    }


    @Override
    public void draw(Graphics g, int x, int y, int width, int height) {
        g.drawImage(image, x, y, width, height, null);
    }
}
