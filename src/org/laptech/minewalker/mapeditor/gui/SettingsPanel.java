package org.laptech.minewalker.mapeditor.gui;

import org.laptech.minewalker.mapeditor.gui.tools.EmptyTool;
import org.laptech.minewalker.mapeditor.gui.tools.Tool;
import org.laptech.minewalker.mapeditor.gui.tools.properties.ToolProperty;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

/**
 * Panel for tool settings
 *
 * @author rlapin
 */
public class SettingsPanel {
    public static final Color BGCOLOR = new Color(30, 30, 30, 255);
    public static final Color FGCOLOR = new Color(120, 200, 145, 255);
    private JPanel contentPanel;
    private JPanel panel;

    public SettingsPanel() {

        this.contentPanel = new JPanel();
        panel = new HidablePanel("Tool settings: ", contentPanel);
        // the first time i will send null to the method
        revalidateForTool(new EmptyTool());

    }

    /**
     * Define components in contentPanel based on current tool
     *
     * @param tool
     */
    public void revalidateForTool(Tool tool) {
        contentPanel.removeAll();
        contentPanel.setLayout(new GridLayout(tool.getProperties().size(), 1));
        tool.getProperties().forEach(this::addComponent);
        panel.revalidate();
        panel.setVisible(tool.getProperties().size() > 0);
    }


    /**
     * Add lane to settingspanel with label and field for input data
     *
     * @param toolProperty
     */
    private void addComponent(ToolProperty toolProperty) {
        JPanel lane = new JPanel();
        lane.setOpaque(true);
        lane.setBackground(BGCOLOR);
        lane.setForeground(FGCOLOR);
        lane.setLayout(new FlowLayout(FlowLayout.CENTER));
        lane.add(new JLabel(toolProperty.getDescription()));

        lane.add(toolProperty.getView());
        contentPanel.add(lane);
    }

    public JPanel getContentPanel() {
        return panel;
    }
}
