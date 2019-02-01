package view;

import javax.swing.*;

import model.SimulatorModel;

public  class QueueView extends AbstractView {

    private JLabel test;

    public QueueView(SimulatorModel model) {
        super(model);
        test = new JLabel("test");
        add(test);
        updateView();
        setVisible(true);
    }

    private void setTest(int adHoc, int pass) {
        test.setText("<html><h3>Auto's in wachtrij (Ad-Hoc): " + adHoc + ".<br/> Auto's in wachtrij (Abonnement): " + pass +"</h3></html>");
    }

    public void updateView() {
        int adHocQueue = model.getCarQueueCount();
        int passQueue = model.getPassQueueCount();
        setTest(adHocQueue, passQueue);
        repaint();
    }
}
