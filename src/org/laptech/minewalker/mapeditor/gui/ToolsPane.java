package org.laptech.minewalker.mapeditor.gui;

import org.laptech.minewalker.mapeditor.gui.tools.Tool;
import org.laptech.minewalker.mapeditor.gui.tools.ToolChangeListener;
import org.laptech.minewalker.mapeditor.gui.tools.ToolFactory;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;

/**
 * Panel with tools and their configration
 * @author rlapin
 */
public class ToolsPane extends JPanel{
    private ToolChangeListener listener;

    public static final Dimension BTN_SIZE = new Dimension(32, 32);
    private EditorController controller;
    private SettingsPanel settingsPanel;

    public ToolsPane(EditorController controller) {
        this.controller = controller;
        initComponents();
    }

    private void initComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        add(createToolsPanel(),gbc);
        add(createSettingsPanel(), gbc);

    }

    private JPanel createSettingsPanel() {
        this.settingsPanel = new SettingsPanel();

        return settingsPanel.getPanel();
    }

    private JPanel createToolsPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER,5,5));
        ToolFactory factory = new ToolFactory(controller);
        panel.add(createToolButton(factory.createFloorTool()));
        panel.add(createToolButton(factory.createSelectionTool()));
        return panel;
    }

    private Component createToolButton(Tool tool) {
        JButton button = new JButton();
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder());
        ImageIcon imageIcon = new ImageIcon(tool.getToolIcon().getScaledInstance(BTN_SIZE.width, BTN_SIZE.height, Image.SCALE_FAST));
        button.setOpaque(true);
        button.setBackground(new Color(114, 114, 114,255));
        button.setSize(BTN_SIZE);
        button.setIcon(imageIcon);
        button.addActionListener(event -> listener.onToolChanged(tool));
        return button;
    }
}
