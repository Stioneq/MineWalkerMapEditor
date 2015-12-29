package org.laptech.minewalker.mapeditor.gui.tools;

import org.laptech.minewalker.mapeditor.gui.EditorController;

/**
 * Factory for creating editor tools
 * @author rlapin
 */
public class ToolFactory {
    private EditorController editorController;
    public ToolFactory(EditorController controller) {
        this.editorController = controller;
    }

    /**
     *
     * @return selection tool which is used for selecting map objects
     */
    public Tool createSelectionTool(){
        return new SelectionTool(editorController);
    }

    /**
     *
     * @return floor tool which is used for creating floor objects
     */
    public Tool createFloorTool(){
        return new FloorTool(editorController);
    }

    /**
     *
     * @return wall tool which is used for creating wall objects
     */
    public Tool createWallTool(){
        return new WallTool(editorController);
    }
}
