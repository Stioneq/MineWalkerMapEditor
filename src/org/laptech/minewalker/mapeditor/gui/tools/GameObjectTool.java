package org.laptech.minewalker.mapeditor.gui.tools;

import org.laptech.minewalker.mapeditor.data.objects.GameObject;
import org.laptech.minewalker.mapeditor.gui.EditorController;
import org.laptech.minewalker.mapeditor.gui.tools.properties.PropertyChangeListener;
import org.laptech.minewalker.mapeditor.gui.tools.properties.SimpleIntegerPropertyView;
import org.laptech.minewalker.mapeditor.gui.tools.properties.ToolProperty;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Tool for adding gameobjects to map
 *
 * @author rlapin
 */
public abstract class GameObjectTool implements Tool {
    private EditorController editorController;
    private double width = 50;
    private double height = 50;
    private PropertyChangeListener propertyChangeListener;
    private Set<ToolProperty> properties = new LinkedHashSet<>();

    public GameObjectTool(EditorController editorController) {
        this.editorController = editorController;


        SimpleIntegerPropertyView widthView = new SimpleIntegerPropertyView();
        widthView.addValueChangeListener(value -> {
            setWidth(value);
            onPropertyChanged();
        });
        properties.add(new ToolProperty("width: ", widthView));

        SimpleIntegerPropertyView heightView = new SimpleIntegerPropertyView();
        heightView.addValueChangeListener(value -> {
            setHeight(value);
            onPropertyChanged();

        });
        properties.add(new ToolProperty("height: ", heightView));

    }

    private void onPropertyChanged() {
        propertyChangeListener.onChange();
    }

    @Override
    public void apply(double x, double y, double width, double height) {
        editorController.getMap().addGameObject(getObject(x, y, getWidth(), getHeight()).with(this));
    }

    @Override
    public void apply(double x, double y) {
        apply(x, y, getWidth(), getHeight());
    }

    public abstract GameObject getObject(double x, double y, double width, double height);

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public Set<ToolProperty> getProperties() {

        return properties;
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener propertyChangeListener) {
        System.out.println(2);
        this.propertyChangeListener = propertyChangeListener;
    }
}
