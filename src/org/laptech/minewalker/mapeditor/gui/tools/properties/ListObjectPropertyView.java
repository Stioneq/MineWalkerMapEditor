package org.laptech.minewalker.mapeditor.gui.tools.properties;

import org.laptech.minewalker.mapeditor.data.objects.TriggerPoint;

import javax.swing.JComboBox;
import java.awt.Component;

/**
 * @author rlapin
 */
public class ListObjectPropertyView<T> implements ObjectPropertyView<T> {
    private JComboBox<T> comboBox = new JComboBox<>();
    private ValueChangeListener<T> changeListener;

    public ListObjectPropertyView(T ...types) {
        for(T type: types) {
            comboBox.addItem(type);
        }
        comboBox.addActionListener(e -> changeListener.valueChanged(getValue()));
    }


    @Override
    public T getValue() {
        return (T) comboBox.getSelectedItem();
    }

    @Override
    public void addValueChangeListener(ValueChangeListener<T> changeListener) {
        this.changeListener = changeListener;
    }



    @Override
    public Component getComponent() {
        return comboBox;
    }

}
