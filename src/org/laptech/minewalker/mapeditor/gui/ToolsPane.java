package org.laptech.minewalker.mapeditor.gui;

import org.laptech.minewalker.mapeditor.gui.tools.Tool;
import org.laptech.minewalker.mapeditor.gui.tools.ToolChangeListener;
import org.laptech.minewalker.mapeditor.gui.tools.ToolFactory;
import org.laptech.minewalker.mapeditor.gui.utils.ComponentUtils;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Panel with tools and their configration
 *
 * @author rlapin
 */
public class ToolsPane extends JPanel {
    public static final Color ROLLOVER_COLOR = new Color(120, 255, 120, 50);
    private static final Color PRESSED_COLOR = new Color(120, 255, 120, 100);
    ;
    private List<ToolChangeListener> listeners = new ArrayList<>();

    public static final Dimension BTN_SIZE = new Dimension(32, 32);
    private EditorController controller;
    private SettingsPanel settingsPanel;

    public ToolsPane(EditorController controller) {
        super(true);
        setOpaque(true);
        setBackground(new Color(80, 80, 80, 255));
        this.controller = controller;
        initComponents();
    }

    /**
     * Init components on the panel
     */
    private void initComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;
        add(createToolsPanel(), gbc);
        gbc.gridy = 1;
        add(createSettingsPanel(), gbc);

    }

    /**
     * Create panel for settings tools
     *
     * @return create {@link SettingsPanel}
     */
    private JPanel createSettingsPanel() {
        this.settingsPanel = new SettingsPanel();
        listeners.add(settingsPanel::revalidateForTool);
        return settingsPanel.getPanel();
    }

    /**
     * Create panel with tools and label at the top
     *
     * @return panel that contains two panel, one label panel and panel with tools
     */
    private JPanel createToolsPanel() {
        ButtonGroup group = new ButtonGroup();
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;
        JPanel labelPanel = ComponentUtils.createLabelLane("Tools");
        panel.add(labelPanel, gbc);
        panel.setOpaque(true);
        panel.setBackground(Color.GRAY);
        gbc.gridy = 1;
        JPanel separatorPanel;
        separatorPanel = new HidablePanel("Basic tools", createBasicToolsPanel(group));
        panel.add(separatorPanel, gbc);
        separatorPanel = new HidablePanel("Game tools", createGameObjectToolsPanel(group));
        gbc.gridy = 2;
        panel.add(separatorPanel, gbc);
        return panel;
    }


    /**
     * Create panel with gameobject tools
     *
     * @param group
     * @return panel with gameobject tools
     */
    private JPanel createGameObjectToolsPanel(ButtonGroup group) {
        JPanel contentPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        ToolFactory factory = new ToolFactory(controller);
        contentPanel.setOpaque(true);

        Color bgColor = new Color(204, 204, 204);
        contentPanel.setBackground(bgColor);
        contentPanel.add(createToolButton(factory.createFloorTool(), group));
        contentPanel.add(createToolButton(factory.createWallTool(), group));
        return contentPanel;
    }

    /**
     * Create panel with basic tools
     *
     * @param group
     * @return panel with basic tools
     */
    private JPanel createBasicToolsPanel(ButtonGroup group) {
        JPanel contentPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        ToolFactory factory = new ToolFactory(controller);
        contentPanel.setOpaque(true);
        Color bgColor = new Color(204, 204, 204);
        contentPanel.setBackground(bgColor);
        contentPanel.add(createToolButton(factory.createSelectionTool(), group));
        contentPanel.add(createToolButton(factory.createMoveTool(), group));
        return contentPanel;
    }


    /**
     * Create button for exact Tool and add it to buttongroup
     *
     * @param tool  tool that used for button
     * @param group
     * @return ImageButton
     */
    private Component createToolButton(Tool tool, ButtonGroup group) {
        JToggleButton button = new JToggleButton();
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder());
        Image image = tool.getToolIcon();
        ImageIcon imageIcon = new ImageIcon(image.getScaledInstance(BTN_SIZE.width, BTN_SIZE.height, Image.SCALE_FAST));

        button.setSize(BTN_SIZE);
        button.setIcon(imageIcon);

        button.setRolloverIcon(new ImageIcon(createRollOverImage(image).getScaledInstance(BTN_SIZE.width, BTN_SIZE.height, Image.SCALE_FAST)));
        button.setPressedIcon(new ImageIcon(createPressedImage(image).getScaledInstance(BTN_SIZE.width, BTN_SIZE.height, Image.SCALE_FAST)));
        button.setSelectedIcon(button.getPressedIcon());
        button.setToolTipText(tool.getTooltip());
        button.addActionListener(event -> {
            for (ToolChangeListener listener : listeners) {
                listener.onToolChanged(tool);
            }
            /*for(Component component :button.getParent().getComponents()){
                if(component!=button){
                    if(component instanceof JToggleButton){
                        ((JToggleButton)component).setSelected(false);
                    }
                }
            }*/
        });
        group.add(button);
        return button;
    }

    /**
     * Create image from existing icon image. That image will used for pressed button state.<br>
     * Main idea is adding new layer hovering input image
     *
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
     *
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

    /**
     * Add tool changelistener
     *
     * @param listener toolchange listener
     */
    public void addToolChangeListener(ToolChangeListener listener) {
        listeners.add(listener);
    }
}
