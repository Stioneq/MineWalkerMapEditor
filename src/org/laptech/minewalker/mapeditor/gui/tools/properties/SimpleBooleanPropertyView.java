package org.laptech.minewalker.mapeditor.gui.tools.properties;

import javax.swing.JCheckBox;
import java.awt.Component;

/**
 * @author rlapin
 */
public class SimpleBooleanPropertyView implements BooleanPropertyView {
    private JCheckBox checkBox = new JCheckBox();
    private ValueChangeListener<Boolean> valueChangeListener;

    public SimpleBooleanPropertyView() {
        checkBox.addChangeListener(e -> valueChangeListener.valueChanged(getValue()));
    }

    @Override
    public boolean getValue() {
        return checkBox.isSelected();
    }

    @Override
    public void addValueChangeListener(ValueChangeListener<Boolean> valueChangeListener) {
        this.valueChangeListener = valueChangeListener;
    }

    @Override
    public Component getComponent() {
        return checkBox;
    }
}
