package view;

import java.awt.*;

import model.SimulatorModel;

public class PieChartReservedOccupation extends AbstractView {

    public PieChartReservedOccupation(SimulatorModel simulator){
        super(simulator);
        setSize(200,200);
    }

    public void paintComponent(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, 200, 200);


        float amountOfReservedSpots = model.getAmountOfReservedSpots();
        float amountOfReservedCars = model.getAmountOfReservedCars();

        System.out.println("Check amountOfReservedSpots = " + amountOfReservedSpots);

        int factor = Math.round(360f / (amountOfReservedSpots + amountOfReservedCars));

     

        int angleReservedCars = Math.round(factor * amountOfReservedCars);


        //Reservedcars slice
        g.setColor(Color.GREEN);
        g.fillArc(10, 10, 180, 180, 0, angleReservedCars);
        //Reservedspot slice
        g.setColor(Color.YELLOW);
        g.fillArc(10, 10, 180, 180, angleReservedCars, 360 - angleReservedCars);
    }
}

