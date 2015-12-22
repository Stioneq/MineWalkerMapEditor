package org.laptech.minewalker.mapeditor.gui;

import org.laptech.minewalker.mapeditor.data.Map;
import org.laptech.minewalker.mapeditor.data.io.XMLWriter;

/**
 * Link between Map and GUI
 * @author rlapin
 */
public class EditorController {
    private Map map;

    public EditorController() {
        map = new Map();
    }


    public void newMap(){
        map.getObjects().clear();
    }

    public void saveMap(String filePath){
        new XMLWriter(filePath).write(map);
    }
    public void loadMap(String filePath){
        new XMLWriter(filePath).write(map);
    }

    public Map getMap() {
        return map;
    }
}
