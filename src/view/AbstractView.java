package view;

import javax.swing.*;

import model.AbstractModel;
import model.SimulatorModel;

public abstract class AbstractView extends JPanel {
    protected SimulatorModel model;

    public AbstractView(SimulatorModel model) {
        this.model = model;
        model.addView(this);
    }

    public AbstractModel getModel() {
        return model;
    }

    public void updateView() {
        repaint();
    }
}
