package view;

import javax.swing.*;

import model.SimulatorModel;

public class ClockView extends AbstractView {

    private JLabel clock;

    public ClockView(SimulatorModel model) {
        super(model);
        clock = new JLabel("test");
        add(clock);
        updateView();
        setVisible(true);
    }

    private void setClock(int day, int hour, int minute) {
        String dayString;
        switch(day) {
            case 0:     dayString = "Maandag";
                    break;
            case 1:     dayString = "Dinsdag";
                    break;
            case 2:     dayString = "Woensdag";
                    break;
            case 3:     dayString = "Donderdag";
                    break;
            case 4:     dayString = "Vrijdag";
                    break;
            case 5:     dayString = "Zaterdag";
                    break;
            case 6:     dayString = "Zondag";
                    break;
            default:    dayString = "Invalid day";
                    break;
        }
        String hourString = String.format("%02d", hour);
        String minuteString = String.format("%02d", minute);
        clock.setText("<html><h2>"+dayString + " " + hourString + ":" + minuteString+"</h2></html>");
    }

    public void updateView() {
  //      SimulatorModel model = (SimulatorModel) super.model;

        int day = model.getDay();
        int hour = model.getHour();
        int minute = model.getMinute();
        setClock(day, hour, minute);
        repaint();
    }


}
