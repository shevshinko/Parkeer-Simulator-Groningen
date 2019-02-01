package view;

import java.awt.*;

import model.SimulatorModel;

public class PieChartCars extends AbstractView{

    public PieChartCars(SimulatorModel simulator){
        super(simulator);
        setSize(200,200);
    }

    	//paintcomponent is een methode van Java. setcolor maakt de kleur aan van het blokje, fillrect is de grootte van het blokje.
    public void paintComponent(Graphics g) {
    	g.setColor(new Color(244,244,244,1));
        g.fillRect(0, 0, 200, 200);

        //hier definiteren we de hoevheelheid, dat word uit simulator model gehaald.
        int amountOfAd_Hoc = model.getAmountOfAd_Hoc();
        int amountOfPassCars = model.getAmountOfPassCars();
        int amountOfReservedCars = model.getAmountOfReservedCars();

        //360 degree circle gedeeld door 500 parkeerplekken met de bijbehorende formules
        float equalizer = 360f / 500f;
        int angleAd_Hoc = Math.round(amountOfAd_Hoc * equalizer);
        int anglePassCars = Math.round(amountOfPassCars * equalizer);
        int angleReservedCars = Math.round(amountOfReservedCars);
        
        //Ad_Hoc stukjes met het begin van de angle en het einde
        g.setColor(Color.RED);
        g.fillArc(10, 10, 180, 180, 0, angleAd_Hoc);
        //PassCars stukje
        g.setColor(Color.BLUE);
        g.fillArc(10, 10, 180, 180, angleAd_Hoc, anglePassCars);
        //ReservedCars stukje
        g.setColor(Color.GREEN);
        g.fillArc(10, 10, 180, 180, anglePassCars + angleAd_Hoc, angleReservedCars);
        g.setColor(Color.LIGHT_GRAY);
        g.fillArc(10, 10, 180, 180, anglePassCars + angleAd_Hoc + angleReservedCars, 360-(angleAd_Hoc + anglePassCars + angleReservedCars));
    }
}
