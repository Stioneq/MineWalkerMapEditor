package org.laptech.minewalker.mapeditor.data.io;

import org.laptech.minewalker.mapeditor.data.Map;

/**
 * Use for writing map to structure e.g File
 *
 * @author rlapin
 */
public interface Writer {
    void write(Map map);
}
