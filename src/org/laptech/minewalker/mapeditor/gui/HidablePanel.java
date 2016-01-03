package org.laptech.minewalker.mapeditor.gui;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

/**
 * Panel that can be hidden by clicked on the title
 *
 * @author rlapin
 */
public class HidablePanel extends JPanel {
    public static final Color BGCOLOR = new Color(30, 30, 30, 255);
    public static final Color FGCOLOR = new Color(120, 200, 145, 255);
    public static final int LABEL_IMG_SIZE = 30;
    public static ImageIcon IMAGE_PLUS;
    public static ImageIcon IMAGE_MINUS;

    static {
        try {
            IMAGE_PLUS = new ImageIcon(ImageIO.read(HidablePanel.class.getClassLoader().getResourceAsStream("images/plus.png")).getScaledInstance(LABEL_IMG_SIZE, LABEL_IMG_SIZE, Image.SCALE_FAST));
            IMAGE_MINUS = new ImageIcon(ImageIO.read(HidablePanel.class.getClassLoader().getResourceAsStream("images/minus.png")).getScaledInstance(LABEL_IMG_SIZE, LABEL_IMG_SIZE, Image.SCALE_FAST));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Panel title
     */
    private String title;
    /**
     * Panel that can hide via clicking on the title
     */
    private JPanel hidablePane;

    public HidablePanel() {
        setOpaque(true);
        setBackground(Color.GRAY);

    }

    public HidablePanel(String title, JPanel hidablePane) {
        this();
        this.title = title;
        this.hidablePane = hidablePane;
        initComponents();

    }


    private void initComponents() {
        setLayout(new BorderLayout());
        add(createLabelLane(title), BorderLayout.NORTH);
        add(hidablePane, BorderLayout.CENTER);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    /**
     * Create label lane with hide marker
     */
    private JPanel createLabelLane(String text) {
        JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        labelPanel.setOpaque(true);
        labelPanel.setBackground(BGCOLOR);
        JLabel label = new JLabel(text);
        label.setIcon(IMAGE_MINUS);
        label.setForeground(FGCOLOR);
        labelPanel.add(label);

        labelPanel.addMouseListener(new MouseAdapter() {
            Timer timer;
            int tempHeight;
            boolean isResizing;

            @Override
            public void mouseClicked(MouseEvent e) {
                if (!isResizing) {
                    isResizing = true;
                    boolean isCollapse = hidablePane.isVisible();
                    if (isCollapse) {
                        tempHeight = hidablePane.getHeight();
                    }
                    timer = new Timer(5, new AbstractAction() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (isCollapse) {
                                collapse();
                            } else {
                                hidablePane.setVisible(true);
                                expand();

                            }

                        }

                        private void expand() {
                            if (hidablePane.getHeight() < tempHeight) {
                                hidablePane.setPreferredSize(new Dimension(hidablePane.getWidth(), hidablePane.getHeight() + 1));
                                hidablePane.setMaximumSize(new Dimension(hidablePane.getWidth(), hidablePane.getHeight() + 1));
                                hidablePane.revalidate();
                            } else {
                                label.setIcon(IMAGE_MINUS);
                                timer.stop();
                                isResizing = false;
                            }
                        }

                        private void collapse() {
                            if (hidablePane.getHeight() != 0) {
                                hidablePane.setPreferredSize(new Dimension(hidablePane.getWidth(), hidablePane.getHeight() - 1));
                                hidablePane.setMaximumSize(new Dimension(hidablePane.getWidth(), hidablePane.getHeight() - 1));
                                hidablePane.revalidate();
                            } else {
                                hidablePane.setVisible(false);
                                label.setIcon(IMAGE_PLUS);
                                timer.stop();
                                isResizing = false;
                            }
                        }
                    });
                    timer.start();
                }

            }
        });
        return labelPanel;
    }
}
