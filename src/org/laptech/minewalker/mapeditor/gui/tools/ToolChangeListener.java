package org.laptech.minewalker.mapeditor.gui.tools;

/**
 * Fires when tool changed(selected)
 * @author rlapin
 */
public interface ToolChangeListener {
    /**
     * Tool changed
     * @param tool tool which is selected
     */
    void onToolChanged(Tool tool);
}
