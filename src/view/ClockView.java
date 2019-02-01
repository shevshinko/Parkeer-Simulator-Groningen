package view;

import javax.swing.*;

import model.SimulatorModel;

public class ClockView extends AbstractView {

    private JLabel clock;

//	Haalt de constructor op van Simulator model
    public ClockView(SimulatorModel model) {
        super(model);
        clock = new JLabel("test");
        add(clock);
        updateView();
        setVisible(true);
    }
    
//	Laat de dag switchen
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
            default:    dayString = "Ongeldige dag";
                    break;
        }
        //	Laat de tijd 02 zijn in plaats van 2
        String hourString = String.format("%02d", hour);
        String minuteString = String.format("%02d", minute);
        clock.setText("<html><h2>"+dayString + " " + hourString + ":" + minuteString+"</h2></html>");
    }
    
//	Update de view
    public void updateView() {
        int day = model.getDay();
        int hour = model.getHour();
        int minute = model.getMinute();
        setClock(day, hour, minute);
        repaint();
    }


}
