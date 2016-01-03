package org.laptech.minewalker.mapeditor.data.undoredo;

import org.laptech.minewalker.mapeditor.data.Map;
import org.laptech.minewalker.mapeditor.data.MapState;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;

/**
 * Used to saving states and to iterate by them
 * @author rlapin
 */
public class DefaultUndoRedoAction implements UndoRedoAction {
    private static final Logger LOGGER = getLogger(DefaultUndoRedoAction.class.getName());
    /**
     * State of map for undo/redo
     */
    private List<MapState> states = new LinkedList<>();
    /**
     * Current state. 0 - first state
     */
    private int currentState = 0;
    private Map map;
    private UndoRedoHandler undoRedoHandler;

    public DefaultUndoRedoAction(Map map, UndoRedoHandler undoRedoHandler) {

        this.map = map;
        this.undoRedoHandler = undoRedoHandler;
        addState();
    }

    public void addState(){
        while(states.size()-1>currentState){
            states.remove(states.size()-1);
        }
        states.add(getCurrentState());
    }

    private MapState getCurrentState() {
        return new MapState(new HashSet<>(map.getObjects()),new HashSet<>(map.getSelectedObjects()),"");
    }

    @Override
    public void undo() {
        restoreState(states.get(currentState-1));
        currentState--;
        undoRedoHandler.onUndoRedo(states,currentState);
        LOGGER.info("UNDO #"+currentState);
    }

    /**
     * Restore mapstate
     * @param mapState
     */
    private void restoreState(MapState mapState) {
        map.setObjects(mapState.getObjects());
        map.setSelectedObjects(mapState.getSelectedObjects());
    }

    @Override
    public void redo() {
        restoreState(states.get(currentState+1));
        currentState++;
        undoRedoHandler.onUndoRedo(states,currentState);
        LOGGER.info("REDO #"+currentState);
    }

    @Override
    public void changed() {
        addState();
        currentState++;
        undoRedoHandler.onUndoRedo(states,currentState);
        LOGGER.info("Changed #"+currentState);
    }
}
