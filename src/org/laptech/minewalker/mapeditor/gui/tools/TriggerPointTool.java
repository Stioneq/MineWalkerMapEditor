package org.laptech.minewalker.mapeditor.gui.tools;

import org.laptech.minewalker.mapeditor.data.objects.Floor;
import org.laptech.minewalker.mapeditor.data.objects.GameObject;
import org.laptech.minewalker.mapeditor.data.objects.TriggerPoint;
import org.laptech.minewalker.mapeditor.gui.EditorController;
import org.laptech.minewalker.mapeditor.gui.tools.properties.ListObjectPropertyView;
import org.laptech.minewalker.mapeditor.gui.tools.properties.ObjectPropertyView;
import org.laptech.minewalker.mapeditor.gui.tools.properties.SimpleBooleanPropertyView;
import org.laptech.minewalker.mapeditor.gui.tools.properties.ToolProperty;

import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;

/**
 * Tool for adding spawn/end points
 * @author rlapin
 */
public class TriggerPointTool extends GameObjectTool {
    public static final String IMAGE_PATH = "images/triggerpointtool.png";
    public static final String SPAWN_IMAGE_PATH = "images/spawnpointtool.png";
    public static final String FINISH_IMAGE_PATH = "images/finishpointtool.png";
    public static final String TYPE = "floor";
    private static final Logger LOGGER = getLogger(SelectionTool.class.getName());
    private Image image;
    private TriggerPoint.TriggerType type = TriggerPoint.TriggerType.SPAWN;
    private Image spawnImage;
    private Image finishImage;

    public TriggerPointTool(EditorController editorController) {
        super(editorController);
        try {
            image = ImageIO.read(SelectionTool.class.getClassLoader().getResourceAsStream(IMAGE_PATH));
        } catch (IOException e) {
            LOGGER.severe("Cannot load image " + IMAGE_PATH);
        }
        try {
            spawnImage = ImageIO.read(SelectionTool.class.getClassLoader().getResourceAsStream(SPAWN_IMAGE_PATH));
        } catch (IOException e) {
            LOGGER.severe("Cannot load spawn image " + SPAWN_IMAGE_PATH);
        }
        try {
            finishImage = ImageIO.read(SelectionTool.class.getClassLoader().getResourceAsStream(FINISH_IMAGE_PATH));
        } catch (IOException e) {
            LOGGER.severe("Cannot load finish image " + FINISH_IMAGE_PATH);
        }
    }

    @Override
    public GameObject getObject(double x, double y, double width, double height) {
        return new TriggerPoint(x, y, width, height,type);
    }

    @Override
    public Image getToolIcon() {
        return image;
    }

    @Override
    public String getTooltip() {
        return "Trigger point tool";
    }

    @Override
    public String getType() {
        return TYPE;
    }
    @Override
    protected void setupProperties() {
        super.setupProperties();
        ObjectPropertyView<TriggerPoint.TriggerType> view = new ListObjectPropertyView<>(TriggerPoint.TriggerType.SPAWN, TriggerPoint.TriggerType.FINISH);
        view.addValueChangeListener(value -> {type = value;onPropertyChanged();});
        properties.add(new ToolProperty("TriggerType", view));
    }

    @Override
    public void draw(Graphics g, int x, int y, int width, int height) {
        switch (type){
            case SPAWN:
                g.drawImage(spawnImage, x, y, width, height, null);
                break;
            case FINISH:
                g.drawImage(finishImage, x, y, width, height, null);
                break;
        }
    }
}
