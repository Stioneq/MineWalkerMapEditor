package org.laptech.minewalker.mapeditor.gui;

import org.laptech.minewalker.mapeditor.gui.tools.Tool;
import org.laptech.minewalker.mapeditor.gui.tools.ToolChangeListener;
import org.laptech.minewalker.mapeditor.gui.tools.ToolFactory;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 * Panel with tools and their configration
 *
 * @author rlapin
 */
public class ToolsPane extends JPanel {
    public static final Color ROLLOVER_COLOR = new Color(120, 255, 120, 50);
    private static final Color PRESSED_COLOR = new Color(120, 255, 120, 100);;
    private ToolChangeListener listener;

    public static final Dimension BTN_SIZE = new Dimension(32, 32);
    private EditorController controller;
    private SettingsPanel settingsPanel;

    public ToolsPane(EditorController controller) {
        super(true);
        setOpaque(true);
        setBackground(new Color(80,80,80,255));
        this.controller = controller;
        initComponents();
    }

    /**
     * Init components on the panel
     */
    private void initComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        add(createToolsPanel(), gbc);
        add(createSettingsPanel(), gbc);

    }

    /**
     * Create panel for settings tools
     * @return create {@link SettingsPanel}
     */
    private JPanel createSettingsPanel() {
        this.settingsPanel = new SettingsPanel();

        return settingsPanel.getPanel();
    }

    /**
     * Create panel with tools and label at the top
     * @return panel that contains two panel, one label panel and panel with tools
     */
    private JPanel createToolsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel labelPanel = createLabelLane("Tools");
        panel.add(labelPanel, BorderLayout.NORTH);
        panel.setOpaque(true);
        panel.setBackground(Color.GRAY);
        JPanel contentPanel = createContentPanel();
        panel.add(contentPanel,BorderLayout.CENTER);
        return panel;
    }

    /**
     * Create panel with tools
     * @return panel with tools
     */
    private JPanel createContentPanel() {
        JPanel contentPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,5,5));
        ToolFactory factory = new ToolFactory(controller);
        contentPanel.setOpaque(true);
        Color bgColor = new Color(204, 204, 204);
        contentPanel.setBackground(bgColor);
        contentPanel.add(createToolButton(factory.createFloorTool()));
        contentPanel.add(createToolButton(factory.createSelectionTool()));
        contentPanel.add(createToolButton(factory.createWallTool()));
        return contentPanel;
    }

    /**
     * Create lane with label containing definite text
     * @param text text of label
     * @return JPanel
     */
    private JPanel createLabelLane(String text) {
        JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        labelPanel.setOpaque(true);
        Color bgColor = new Color(30, 30, 30, 255);
        labelPanel.setBackground(bgColor);
        JLabel label = new JLabel(text);
        Color fgLabelColor = new Color(120, 200, 145, 255);
        label.setForeground(fgLabelColor);
        labelPanel.add(label);
        return labelPanel;
    }

    /**
     * Create button for exact Tool
     * @param tool tool that used for button
     * @return ImageButton
     */
    private Component createToolButton(Tool tool) {
        JButton button = new JButton();
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder());
        Image image = tool.getToolIcon();
        ImageIcon imageIcon = new ImageIcon(image.getScaledInstance(BTN_SIZE.width, BTN_SIZE.height, Image.SCALE_FAST));

        button.setSize(BTN_SIZE);
        button.setIcon(imageIcon);
        button.setRolloverIcon(new ImageIcon(createRollOverImage(image).getScaledInstance(BTN_SIZE.width, BTN_SIZE.height, Image.SCALE_FAST)));
        button.setPressedIcon(new ImageIcon(createPressedImage(image).getScaledInstance(BTN_SIZE.width, BTN_SIZE.height, Image.SCALE_FAST)));
        button.setToolTipText(tool.getTooltip());
        button.addActionListener(event -> listener.onToolChanged(tool));
        return button;
    }

    /**
     * Create image from existing icon image. That image will used for pressed button state.<br>
     * Main idea is adding new layer hovering input image
     * @param image Input image
     * @return Image
     */
    private Image createPressedImage(Image image) {
        BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = bufferedImage.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.setColor(PRESSED_COLOR);
        g.fillRect(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight());
        return bufferedImage;
    }
    /**
     * Create image from existing icon image. That image will used for roolover button state.<br>
     * Main idea is adding new layer hovering input image
     * @param image Input image
     * @return Image
     */
    private Image createRollOverImage(Image image) {
        BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = bufferedImage.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.setColor(ROLLOVER_COLOR);
        g.fillRect(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight());
        return bufferedImage;
    }
}
