package org.laptech.minewalker.mapeditor.data.io;

import org.laptech.minewalker.mapeditor.data.Map;

/**
 * Write map into xml file
 * @author rlapin
 */
public class XMLWriter implements Writer{
    /**
     * Path to xml file
     */
    private String filePath;
    public XMLWriter(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void write(Map map) {

    }
}
