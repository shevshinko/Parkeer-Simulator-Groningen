package model;

import java.awt.*;
import java.util.Random;

public class AdHocCar extends Car {
	private static final Color COLOR=Color.red;
	public static double adHocCarCost = 0.10;
	
    public AdHocCar() {
    	Random random = new Random();
        int stayMinutes = random.nextInt(100) + 10;
        System.out.println("Ad-Hoc Car is staying " + stayMinutes + " minutes");
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(true);
        this.setType(1);
        this.setAdHocPay(Math.round(adHocCarCost * stayMinutes));
    }
    
    public Color getColor(){
    	return COLOR;
    }
    public static void setAdHocPayCost(int a){
        adHocCarCost = a;
    }

}
