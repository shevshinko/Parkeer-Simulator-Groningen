package view;

import java.awt.*;

import model.SimulatorModel;

public class PieChartPassOccupation extends AbstractView {

    public PieChartPassOccupation(SimulatorModel simulator){
        super(simulator);
        setSize(200,200);
    }

    public void paintComponent(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, 200, 200);


        float amountOfPassSpots = model.getAmountOfPassSpots();
        float amountOfPassCars = model.getAmountOfPassCars();

   

        int factor = Math.round(360f / (amountOfPassSpots + amountOfPassCars));

        int anglePassCars = Math.round(factor * amountOfPassCars);


        //Passcars slice
        g.setColor(Color.BLUE);
        g.fillArc(10, 10, 180, 180, 0, anglePassCars);
        //Passspot slice
        g.setColor(Color.CYAN);
        g.fillArc(10, 10, 180, 180, anglePassCars, 360 - anglePassCars);
    }
}
