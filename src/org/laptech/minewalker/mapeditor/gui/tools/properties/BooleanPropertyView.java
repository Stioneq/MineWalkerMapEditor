package org.laptech.minewalker.mapeditor.gui.tools.properties;

/**
 * @author rlapin
 */
public interface BooleanPropertyView extends PropertyView {
    boolean getValue();

    void addValueChangeListener(ValueChangeListener<Boolean> onchange);
}
