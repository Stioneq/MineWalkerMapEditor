package org.laptech.minewalker.mapeditor.gui.tools;

import org.laptech.minewalker.mapeditor.gui.EditorController;

/**
 * Factory for creating editor tools
 *
 * @author rlapin
 */
public class ToolFactory {
    private final FloorTool FLOOR_TOOL;
    private final WallTool WALL_TOOL;
    private final MoveTool MOVE_TOOL;
    private final SelectionTool SELECTION_TOOL;
    private EditorController editorController;

    public ToolFactory(EditorController controller) {
        this.editorController = controller;
        SELECTION_TOOL = new SelectionTool(editorController);
        FLOOR_TOOL = new FloorTool(editorController);
        WALL_TOOL = new WallTool(editorController);
        MOVE_TOOL = new MoveTool(editorController);


    }

    /**
     * @return selection tool which is used for selecting map objects
     */
    public Tool createSelectionTool() {
        return SELECTION_TOOL;
    }

    /**
     * @return floor tool which is used for creating floor objects
     */
    public Tool createFloorTool() {
        return FLOOR_TOOL;
    }

    /**
     * @return wall tool which is used for creating wall objects
     */
    public Tool createWallTool() {
        return WALL_TOOL;
    }

    public Tool createMoveTool() {
        return MOVE_TOOL;
    }
}
