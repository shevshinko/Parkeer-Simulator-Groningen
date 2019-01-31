package control;

import javax.swing.*;

import model.AdHocCar;
import model.ReservedCar;
import model.SimulatorModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InputValues extends AbstractController implements ActionListener {
    private JLabel weekDayArrivalsLabel;
    private JLabel weekendArrivalsLabel;
    private JLabel weekDayPassArrivalsLabel;
    private JLabel weekendPassArrivalsLabel;
    private JLabel weekReservedArrivalsLabel;
    private JLabel weekendReservedArrivalsLabel;
    private JLabel enterSpeedLabel;
    private JLabel paymentSpeedLabel;
    private JLabel exitSpeedLabel;
    private JLabel numberOfPassSpotsLabel;
    private JLabel reservedCarCostLabel;
    private JLabel reservationCostLabel;
    private JLabel adHocCarCostLabel;

    private JTextField weekDayArrivalsField;
    private JTextField weekendArrivalsField;
    private JTextField weekDayPassArrivalsField;
    private JTextField weekendPassArrivalsField;
    private JTextField weekReservedArrivalsField;
    private JTextField weekendReservedArrivalsField;
    private JTextField enterSpeedField;
    private JTextField paymentSpeedField;
    private JTextField exitSpeedField;
    private JTextField numberOfPassSpotsField;
    private JTextField reservedCarCostField;
    private JTextField reservationCostField;
    private JTextField adHocCarCostField;


    private JButton toepassen;

    public InputValues(SimulatorModel model) {
        super(model);

        setSize(700, 150);

        weekDayArrivalsLabel = new JLabel("Werkdag Ad Hoc binnenkomst");
        weekDayArrivalsLabel.setBounds(5, 10, 200, 25);
        add(weekDayArrivalsLabel);

            weekDayArrivalsField = new JTextField("" + model.getweekDayArrivals());
            add(weekDayArrivalsField);
            weekDayArrivalsField.setBounds(210, 10, 70, 25);

        weekendArrivalsLabel = new JLabel("Weekend Ad Hoc binnenkomst");
        weekendArrivalsLabel.setBounds(5, 40, 200, 25);
        add(weekendArrivalsLabel);

            weekendArrivalsField = new JTextField("" + model.getweekendArrivals());
            add(weekendArrivalsField);
            weekendArrivalsField.setBounds(210, 40, 70, 25);

        weekDayPassArrivalsLabel = new JLabel("Werkdag abbonement binnenkomst");
        weekDayPassArrivalsLabel.setBounds(5, 70, 200, 25);
        add(weekDayPassArrivalsLabel);

            weekDayPassArrivalsField = new JTextField("" + model.getweekDayPassArrivals());
            add(weekDayPassArrivalsField);
            weekDayPassArrivalsField.setBounds(210, 70, 70, 25);

        weekendPassArrivalsLabel = new JLabel("Weekend abbonement binnenkomst");
        weekendPassArrivalsLabel.setBounds(5, 100, 200, 25);
        add(weekendPassArrivalsLabel);

            weekendPassArrivalsField = new JTextField("" + model.getweekendPassArrivals());
            add(weekendPassArrivalsField);
            weekendPassArrivalsField.setBounds(210, 100, 70, 25);

        weekReservedArrivalsLabel = new JLabel("Werkdag gereserveerde autos");
        weekReservedArrivalsLabel.setBounds(5, 130, 200, 25);
        add(weekReservedArrivalsLabel);

            weekReservedArrivalsField = new JTextField("" + model.getweekReservedArrivals());
            add(weekReservedArrivalsField);
            weekReservedArrivalsField.setBounds(210, 130, 70, 25);

        weekendReservedArrivalsLabel = new JLabel("Weekend gereserveerde autos");
        weekendReservedArrivalsLabel.setBounds(5, 160, 200, 25);
        add(weekendReservedArrivalsLabel);

            weekendReservedArrivalsField = new JTextField("" + model.getweekendReservedArrivals());
            add(weekendReservedArrivalsField);
            weekendReservedArrivalsField.setBounds(210, 160, 70, 25);
///////////////////////////////////////////////////////////////////
        enterSpeedLabel = new JLabel("Binnenkomst snelheid");
        enterSpeedLabel.setBounds(330, 10, 130, 25);
        add(enterSpeedLabel);

            enterSpeedField = new JTextField("" + model.getenterSpeed());
            add(enterSpeedField);
            enterSpeedField.setBounds(460, 10, 70, 25);

        paymentSpeedLabel = new JLabel("Betaling snelheid");
        paymentSpeedLabel.setBounds(330, 40, 130, 25);
        add(paymentSpeedLabel);

            paymentSpeedField = new JTextField("" + model.getpaymentSpeed());
            add(paymentSpeedField);
            paymentSpeedField.setBounds(460, 40, 70, 25);

        exitSpeedLabel = new JLabel("Uitgang snelheid");
        exitSpeedLabel.setBounds(330, 70, 130, 25);
        add(exitSpeedLabel);

            exitSpeedField = new JTextField("" + model.getexitSpeed());
            add(exitSpeedField);
            exitSpeedField.setBounds(460, 70, 70, 25);
///////////////////////////////////////////////////////////////////
        numberOfPassSpotsLabel = new JLabel("Abbonement houders");
        numberOfPassSpotsLabel.setBounds(600, 10, 160, 25);
        add(numberOfPassSpotsLabel);

            numberOfPassSpotsField = new JTextField("" + model.numberOfPassSpots);
            add(numberOfPassSpotsField);
            numberOfPassSpotsField.setBounds(750, 10, 70, 25);

        reservedCarCostLabel = new JLabel("Reserveering kosten / min");
        reservedCarCostLabel.setBounds(600, 40, 160, 25);
        add(reservedCarCostLabel);

            reservedCarCostField = new JTextField("" + ReservedCar.reservedCarCost);
            add(reservedCarCostField);
            reservedCarCostField.setBounds(750, 40, 70, 25);

        reservationCostLabel = new JLabel("Reserveeringskosten");
        reservationCostLabel.setBounds(600, 70, 160, 25);
        add(reservationCostLabel);

            reservationCostField = new JTextField("" + ReservedCar.reservationCost);
            add(reservationCostField);
            reservationCostField.setBounds(750, 70, 70, 25);
/////////
        adHocCarCostLabel = new JLabel("Ad Hoc kosten / min");
        adHocCarCostLabel.setBounds(600, 100, 160, 25);
        add(adHocCarCostLabel);

            adHocCarCostField = new JTextField("" + AdHocCar.adHocCarCost);
            add(adHocCarCostField);
            adHocCarCostField.setBounds(750, 100, 70, 25);




        toepassen = new JButton("Toepassen");
        toepassen.addActionListener(this);
        add(toepassen);
        toepassen.setBounds(200, 220, 150, 25);

        this.setLayout(null);
        setSize(800, 170);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == toepassen) {
            model.setValuesArrivals(
                    Integer.parseInt(weekDayArrivalsField.getText()),
                    Integer.parseInt(weekendArrivalsField.getText()),
                    Integer.parseInt(weekDayPassArrivalsField.getText()),
                    Integer.parseInt(weekendPassArrivalsField.getText()),
                    Integer.parseInt(weekReservedArrivalsField.getText()),
                    Integer.parseInt(weekendReservedArrivalsField.getText())
            );
            model.setSpeed(
                    Integer.parseInt(enterSpeedField.getText()),
                    Integer.parseInt(paymentSpeedField.getText()),
                    Integer.parseInt(exitSpeedField.getText())
            );
            ReservedCar.setReservedPayCost(
                    Double.parseDouble(reservedCarCostField.getText()),
                    Integer.parseInt(reservationCostField.getText())
            );
            AdHocCar.setAdHocPayCost(
                    Integer.parseInt(adHocCarCostField.getText())
            );
        }
    }
}