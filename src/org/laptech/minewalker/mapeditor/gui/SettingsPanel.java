package org.laptech.minewalker.mapeditor.gui;

import org.laptech.minewalker.mapeditor.gui.tools.Tool;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import java.awt.FlowLayout;
import java.awt.GridLayout;

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
    public void revalidateForTool(Tool tool) {
        panel.setLayout(new GridLayout(2,1));
        addNumericLane("width: ");
        addNumericLane("height: ");


    }

    /**
     * Add lane to settingspanel with label and field for input number
     * @param laneText
     */
    private void addNumericLane(String laneText) {
        JPanel lane = new JPanel();
        lane.setLayout(new FlowLayout(FlowLayout.CENTER));
        lane.add(new JLabel(laneText));
        lane.add(new JSpinner(new SpinnerNumberModel(50,0,1000,5)));
    }

    public JPanel getPanel() {
        return panel;
    }
}
