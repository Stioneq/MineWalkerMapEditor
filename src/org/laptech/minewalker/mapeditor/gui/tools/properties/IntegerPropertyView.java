package org.laptech.minewalker.mapeditor.gui.tools.properties;

/**
 * @author rlapin
 */
public interface IntegerPropertyView extends PropertyView {
    int getValue();

    void addValueChangeListener(ValueChangeListener<Integer> onchange);
}
