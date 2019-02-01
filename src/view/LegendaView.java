package view;

import javax.swing.*;

import model.SimulatorModel;

import java.awt.event.*;

public class LegendaView extends AbstractView {
    private JLabel Legenda1;
    private JLabel Legenda2;
    private JLabel Legenda3;

// Hoe ze eruit zien, en waar ze in staan
    public LegendaView(SimulatorModel model) {
        super(model);

        setSize(1000, 800);

        Legenda1 = new JLabel("1");
        Legenda1.setBounds(50, 20, 200, 120);
        add(Legenda1);

        Legenda2 = new JLabel("2");
        Legenda2.setBounds(300, 20, 200, 120);
        add(Legenda2);

        Legenda3 = new JLabel("3");
        Legenda3.setBounds(550, 0, 200, 120);
        add(Legenda3);

//	update de view
        
    updateView();
    }
    public void updateView() {
    	//	een int met een formule die de vrije plekken berekenen
        int free = Math.round(500) - (Math.round(model.getAmountOfAd_Hoc()) + Math.round(model.getAmountOfPassCars()) + Math.round(model.getAmountOfReservedCars()));
        Legenda1.setText("<html><h4><b><font color=\"red\">Ad-Hoc: "+model.getAmountOfAd_Hoc()+ "</font><br><font color=\"blue\">Passholder: "+model.getAmountOfPassCars()+"</font><br><font color=\"green\">Gereserveerd: "+model.getAmountOfReservedCars()+"</font><br><font color=\"gray\">Vrije plaatsen: "+free+"</font></b></h4><br><br><br><br></html>");
        Legenda2.setText("<html><h4><b><font color=\"yellow\">Gereserveerd : "+model.getAmountOfReservedSpots()+ "</font><br><font color=\"green\">Gereserveerd<br>aanwezig: "+model.getAmountOfReservedCars()+"</font><br><br><br></b></h4></html>");
        Legenda3.setText("<html><h4><b>Abonement <font color=\"aqua\"><br>Vrij: "+model.amountOfPassSpots+ "</font><br><font color=\"blue\">Bezet: : "+model.getAmountOfPassCars()+"</font></b></h4></html>");

        repaint();
    }
}