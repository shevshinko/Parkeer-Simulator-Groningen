package view;

import java.awt.*;

import model.SimulatorModel;

public class PieChartCars extends AbstractView{

    public PieChartCars(SimulatorModel simulator){
        super(simulator);
        setSize(200,200);
    }

    public void paintComponent(Graphics g) {
    	g.setColor(Color.WHITE);
        g.fillRect(0, 0, 200, 200);

        int amountOfAd_Hoc = model.getAmountOfAd_Hoc();
        int amountOfPassCars = model.getAmountOfPassCars();
        int amountOfReservedCars = model.getAmountOfReservedCars();

        //360 degree circle / 540 parking spots
        float equalizer = 360f / 540f;
        int angleAd_Hoc = Math.round(amountOfAd_Hoc * equalizer);
        int anglePassCars = Math.round(amountOfPassCars * equalizer);
        int angleReservedCars = Math.round(amountOfReservedCars);
        
        //Ad_Hoc slice
        g.setColor(Color.RED);
        g.fillArc(10, 10, 180, 180, 0, angleAd_Hoc);
        //PassCars slice
        g.setColor(Color.BLUE);
        g.fillArc(10, 10, 180, 180, angleAd_Hoc, anglePassCars);
        //ReservedCars slice
        g.setColor(Color.GREEN);
        g.fillArc(10, 10, 180, 180, anglePassCars + angleAd_Hoc, angleReservedCars);
        g.setColor(Color.LIGHT_GRAY);
        g.fillArc(10, 10, 180, 180, anglePassCars + angleAd_Hoc + angleReservedCars, 360-(angleAd_Hoc + anglePassCars + angleReservedCars));
    }
}
