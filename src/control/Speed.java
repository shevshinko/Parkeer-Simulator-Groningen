package control;

import javax.swing.*;

import model.*;

import java.awt.event.*;

public class Speed extends AbstractController implements ActionListener{
    public JTextField speed;
    public JButton speedKnop;


    public Speed(SimulatorModel model) {
        super(model);

        setSize(700, 150);

        speed = new JTextField("" + model.getTickPause());
        add(speed);
        speed.setBounds(50, 10, 60, 25);

            speedKnop = new JButton("Tick snelheid");
            speedKnop.addActionListener(this);
            add(speedKnop);
            speedKnop.setBounds(110, 10, 110, 25);


        this.setLayout(null);
        setSize(700, 170);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == speedKnop) {
            model.settickPause(Integer.parseInt(speed.getText()));
        }
    }
}
