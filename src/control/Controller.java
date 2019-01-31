package control;

import javax.swing.*;

import model.*;

import java.awt.event.*;

public class Controller extends AbstractController implements ActionListener {
    private JTextField aantal;
    private JButton start;
    private JButton stappen;
    private JButton stop;

    public Controller(SimulatorModel model) {
        super(model);

        start = new JButton("Start");
        start.addActionListener(this);
        add(start);
        start.setBounds(50, 10, 60, 25);

        aantal = new JTextField();
        add(aantal);
        aantal.setBounds(180, 10, 70, 25);

        stappen = new JButton("Stappen");
        stappen.addActionListener(this);
        add(stappen);
        stappen.setBounds(250, 10, 90, 25);

        stop = new JButton("Stop");
        stop.addActionListener(this);
        add(stop);
        stop.setBounds(110, 10, 60, 25);


        //buttons volledige lengte geven,
        this.setLayout(null);
        setSize(700, 150);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == start) {
            model.start();
        }
        if (e.getSource() == stappen) {
            model.stappen(Integer.parseInt(aantal.getText()));
      }

        if (e.getSource() == stop) {
            model.stop();
        }

    }
}