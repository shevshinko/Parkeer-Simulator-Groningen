package model;

import java.awt.*;
import java.util.Random;

public class ReservedCar extends Car {
    private static final Color COLOR=Color.GREEN;
    public static double reservedCarCost = 0.1;
    public static int reservationCost = 5;

    public ReservedCar() {
        Random random = new Random();
        int stayMinutes = random.nextInt(100) + 10;
        System.out.println("Reserved Car is staying " + stayMinutes + " minutes");
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(true);
        this.setType(4);
        this.setReservedPay(Math.round(reservedCarCost * stayMinutes + reservationCost));

      
    }

    public Color getColor(){
        return COLOR;
    }
    public static void setReservedPayCost(double a, int b){
        reservedCarCost = a;
        reservationCost = b;
    }

}

