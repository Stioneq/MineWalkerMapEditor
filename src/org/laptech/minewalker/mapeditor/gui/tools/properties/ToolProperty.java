package org.laptech.minewalker.mapeditor.gui.tools.properties;

import java.awt.Component;

/**
 * Describes different properties for tool
 *
 * @author rlapin
 */
public class ToolProperty {

    private String description;
    private PropertyView view;

    public ToolProperty(String description, PropertyView view) {
        this.description = description;
        this.view = view;
    }

    public String getDescription() {
        return description;
    }

    public Component getView() {
        return view.getComponent();
    }
}
