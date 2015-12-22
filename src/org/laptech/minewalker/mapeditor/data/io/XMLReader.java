package org.laptech.minewalker.mapeditor.data.io;

import org.laptech.minewalker.mapeditor.data.Map;

/**
 * Reads map from xml file
 * @author rlapin
 */
public class XMLReader implements Reader{
    /**
     * Path to xml file
     */
    private String filePath;
    public XMLReader(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public Map read() {
        return null;
    }
}
