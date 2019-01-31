package control;

import javax.swing.*;

import model.SimulatorModel;

public abstract class AbstractController extends JPanel {
    protected SimulatorModel model;

    public AbstractController(SimulatorModel model) {
        this.model = model;
    }

}
