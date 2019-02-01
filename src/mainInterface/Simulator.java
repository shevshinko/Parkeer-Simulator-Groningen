package mainInterface;

import control.AbstractController;
import control.Controller;
import control.Speed;
import model.SimulatorModel;
import view.*;

import javax.swing.*;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.BorderLayout;
import javax.swing.GroupLayout.Alignment;

public class Simulator {
    private JFrame screen;
    private SimulatorModel simulator;
    private AbstractView carParkView;
    private AbstractView clockView;
    private AbstractView queueView;
    private AbstractController controller;
    private AbstractView moneyView;
    private AbstractController speed;
    private AbstractView piechartcars;
    private AbstractView piechartReservedOccupation;
    private AbstractView piechart;
    private AbstractView legenda;
    private AbstractView piechartPassOccupation;
    

    public Simulator() {
        simulator = new SimulatorModel(3,6,30);
        controller = new Controller(simulator);
        speed = new Speed(simulator);
        carParkView = new CarParkView(simulator);
        carParkView.setBackground(new Color(0, 0, 0));
        moneyView = new MoneyView(simulator);
        piechart = new PieChartCars(simulator);
        legenda = new LegendaView(simulator);
        legenda.setBackground(SystemColor.menu);
        piechartcars = new PieChartCars(simulator);
        piechartcars.setBackground(SystemColor.menu);
        piechartReservedOccupation = new PieChartReservedOccupation(simulator);
        piechartReservedOccupation.setBackground(SystemColor.menu);
        screen = new JFrame("Parkeergarage Groningen");
        screen.getContentPane().setForeground(new Color(0, 0, 0));

        screen.setSize(1800,1000);
        screen.setResizable(false);
        screen.getContentPane().setLayout(null);
        screen.getContentPane().add(carParkView);
        screen.getContentPane().add(moneyView);
        screen.getContentPane().add(piechartcars);
        screen.getContentPane().add(piechartReservedOccupation);
        screen.getContentPane().add(controller);
        screen.getContentPane().add(speed);
        screen.getContentPane().add(legenda);
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        carParkView.setBounds(341,12,697,330);
        moneyView.setBounds(1056, 106, 214, 120);
        controller.setBounds(382, 344, 400, 50);
        controller.setLayout(null);
        piechart.setBounds(860, 80, 200, 200);
        piechartcars.setBounds(337, 405, 200, 200);
        piechartReservedOccupation.setBounds(590,405,200,200);
        speed.setBounds(1,176,234,50);
        legenda.setBounds(337, 606, 707, 142);
        legenda.setLayout(null);
        piechartPassOccupation = new PieChartPassOccupation(simulator);
        piechartPassOccupation.setBackground(SystemColor.menu);
        piechartPassOccupation.setBounds(843, 405, 200, 200);
        screen.getContentPane().add(piechartPassOccupation);
        clockView = new ClockView(simulator);
        clockView.setBounds(52, 88, 149, 52);
        screen.getContentPane().add(clockView);
        clockView.setBackground(SystemColor.menu);
        queueView = new QueueView(simulator);
        queueView.setBounds(46, 250, 270, 64);
        screen.getContentPane().add(queueView);

        screen.setVisible(true);
        simulator.notifyViews();
    }
}
