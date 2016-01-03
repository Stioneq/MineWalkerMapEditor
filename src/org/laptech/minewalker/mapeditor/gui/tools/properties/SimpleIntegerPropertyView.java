package org.laptech.minewalker.mapeditor.gui.tools.properties;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.Component;

/**
 * @author rlapin
 */
public class SimpleIntegerPropertyView implements IntegerPropertyView {
    private JSpinner spinner = new JSpinner(new SpinnerNumberModel(50, 1, 500, 1));
    private ValueChangeListener<Integer> changeListener;

    public SimpleIntegerPropertyView() {
        spinner.addChangeListener(e -> changeListener.valueChanged((Integer) spinner.getValue()));
    }

    @Override
    public int getValue() {
        return (int) spinner.getValue();
    }

    @Override
    public void addValueChangeListener(ValueChangeListener<Integer> changeListener) {
        this.changeListener = changeListener;
    }

    @Override
    public Component getComponent() {
        return spinner;
    }

}
