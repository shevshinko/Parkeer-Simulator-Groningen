package model;

import java.awt.*;

public abstract class Car {

    private Location location;
    private int minutesLeft;
    private boolean isPaying;
    private boolean hasToPay;
    private boolean arriving = false;
    private boolean rightSpot = true;
    private int type = 0;
    private double adHocPay = 0.00;
    private double reservedPay = 0.00;

    /**
     * Constructor for objects of class Car
     */
    public Car() {

    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getMinutesLeft() {
        return minutesLeft;
    }

    public void setMinutesLeft(int minutesLeft) {
        this.minutesLeft = minutesLeft;
    }

    public boolean getIsPaying() {
        return isPaying;
    }

    public void setIsPaying(boolean isPaying) {
        this.isPaying = isPaying;
    }

    public boolean getHasToPay() {
        return hasToPay;
    }

    public void setHasToPay(boolean hasToPay) {
        this.hasToPay = hasToPay;
    }

    public void tick() {
        minutesLeft--;
    }

    public boolean getArriving() {
        return arriving;
    }

    public void setArriving() {
        this.arriving = true;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public abstract Color getColor();

    public void setAdHocPay(double pay) {this.adHocPay = pay;}

    public void setReservedPay(double pay) {this.reservedPay = pay;}

    public double getAdHocPay(){return adHocPay;}

    public double getReservedPay(){return reservedPay;}

    public boolean isRightSpot() {
        return rightSpot;
    }

    public void setRightSpot(boolean rightSpot) {
        this.rightSpot = rightSpot;
    }
}