package model;

import java.awt.*;

public class ParkingPassSpot extends Car {
    private static final Color COLOR=Color.cyan;

    public ParkingPassSpot() {
        this.setType(5);
        this.setMinutesLeft(1);
        this.setHasToPay(false);

    }

    public Color getColor(){
        return COLOR;
    }

    @Override
    public void tick() {
    }
}
