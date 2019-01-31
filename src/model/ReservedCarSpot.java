package model;

import java.awt.*;

public class ReservedCarSpot extends Car {
    private static final Color COLOR=Color.YELLOW;
    private boolean arriving = false;

    public ReservedCarSpot() {
        this.setMinutesLeft(45);
        this.setHasToPay(false);
        this.setType(3);
    }

    public Color getColor(){
        return COLOR;
    }

    public boolean getArriving() {
        return arriving;
    }

    public void setArriving() {
        arriving = true;
    }
}
