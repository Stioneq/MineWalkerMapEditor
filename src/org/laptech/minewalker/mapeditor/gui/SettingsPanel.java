package org.laptech.minewalker.mapeditor.gui;

import org.laptech.minewalker.mapeditor.gui.tools.Tool;

import javax.swing.JPanel;

/**
 * Panel for tool settings
 * @author rlapin
 */
public class SettingsPanel {
    private JPanel panel;

    public SettingsPanel() {
        this.panel = new JPanel();
        // the first time i will send null to the method
        revalidateForTool(null);

    }

    /**
     * Define components in panel based on current tool
     * @param tool
     */
    private void revalidateForTool(Tool tool) {

    }

    public JPanel getPanel() {
        return panel;
    }
}
