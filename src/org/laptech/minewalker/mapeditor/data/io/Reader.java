package org.laptech.minewalker.mapeditor.data.io;

import org.laptech.minewalker.mapeditor.data.Map;

/**
 * Read map from structure
 *
 * @author rlapin
 */
public interface Reader {
    /**
     * Read map from structure
     *
     * @return Map consists of game objects
     */
    Map read();
}
