package org.laptech.minewalker.mapeditor.gui.tools.properties;

import org.laptech.minewalker.mapeditor.gui.tools.properties.PropertyView;

/**
 * @author rlapin
 */
public interface IntegerPropertyView extends PropertyView {
    int getValue();
    void addValueChangeListener(ValueChangeListener<Integer> onchange);
}
