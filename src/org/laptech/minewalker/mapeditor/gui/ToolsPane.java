package org.laptech.minewalker.mapeditor.gui;

import org.laptech.minewalker.mapeditor.gui.tools.Tool;
import org.laptech.minewalker.mapeditor.gui.tools.ToolFactory;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

/**
 * Panel with tools and their configration
 * @author rlapin
 */
public class ToolsPane extends JPanel{


    private EditorController controller;

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
        JPanel panel = new JPanel();

        return panel;
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
        ImageIcon imageIcon = new ImageIcon(tool.getToolIcon());
        button.setIcon(imageIcon);
        return button;
    }
}
