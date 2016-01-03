package org.laptech.minewalker.mapeditor.gui;

import org.laptech.minewalker.mapeditor.gui.tools.EmptyTool;
import org.laptech.minewalker.mapeditor.gui.tools.GameObjectTool;
import org.laptech.minewalker.mapeditor.gui.tools.Tool;
import org.laptech.minewalker.mapeditor.gui.tools.ToolProperty;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import java.awt.FlowLayout;
import java.awt.GridLayout;

/**
 * Panel for tool settings
 *
 * @author rlapin
 */
public class SettingsPanel {
    private JPanel panel;

    public SettingsPanel() {
        this.panel = new JPanel();
        // the first time i will send null to the method
        revalidateForTool(new EmptyTool());

    }

    /**
     * Define components in panel based on current tool
     *
     * @param tool
     */
    public void revalidateForTool(Tool tool) {
        panel.removeAll();
        panel.setLayout(new GridLayout(2, 1));
        for (ToolProperty toolProperty : tool.getProperties())
            addComponent(toolProperty);
        panel.revalidate();
    }


    /**
     * Add lane to settingspanel with label and field for input data
     *
     * @param toolProperty
     */
    private void addComponent(ToolProperty toolProperty) {
        JPanel lane = new JPanel();
        lane.setLayout(new FlowLayout(FlowLayout.CENTER));
        lane.add(new JLabel(toolProperty.getDescription()));
        JSpinner spinner = new JSpinner(new SpinnerNumberModel(50, 0, 1000, 5));
        spinner.addChangeListener(e -> {
            System.out.println(spinner.getValue());
        });
        lane.add(spinner);
        panel.add(lane);
    }

    public JPanel getPanel() {
        return panel;
    }
}
