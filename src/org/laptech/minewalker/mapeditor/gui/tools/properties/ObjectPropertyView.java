package org.laptech.minewalker.mapeditor.gui.tools.properties;

/**
 * @author rlapin
 */
public interface ObjectPropertyView<T> extends PropertyView {
    T getValue();

    void addValueChangeListener(ValueChangeListener<T> onchange);
}
