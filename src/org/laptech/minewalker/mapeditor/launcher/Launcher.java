package org.laptech.minewalker.mapeditor.launcher;

import org.laptech.minewalker.mapeditor.gui.MainWindow;

import javax.swing.SwingUtilities;

/**
 * Launches application
 *
 * @author rlapin
 */
public class Launcher {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainWindow().show());
    }
}
