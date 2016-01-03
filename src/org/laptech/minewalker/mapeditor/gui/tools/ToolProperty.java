package org.laptech.minewalker.mapeditor.gui.tools;

/**
 * Describes different properties for tool
 * @author rlapin
 */
public enum ToolProperty {
    WIDTH("width: "), HEIGHT("height: "), ISOPENED("isopened: ");

    private String description;

    ToolProperty(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
