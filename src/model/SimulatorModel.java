package model;

import java.util.Queue;
import java.util.Random;
import java.awt.*;

public class SimulatorModel extends AbstractModel implements Runnable{

    private static final String AD_HOC = "1";
    private static final String PASS = "2";

    private static int numberOfFloors;
    private static int numberOfRows;
    private static int numberOfPlaces;
    public static int numberOfOpenSpots;

    private double pay = 0;
    private double AdHocPay;
    public static double ReservedPay;

    public static int amountOfAd_Hoc = 0;
    public static int amountOfPassCars = 0;
    public static int amountOfPassSpots = 0;
    public static int amountOfReservedSpots = 0;
    public static int amountOfReservedCars = 0;

    private int numberOfOpenPassSpots = 0;
    private int numberOfOpenOtherSpots;

    private CarQueue entranceCarQueue;
    private CarQueue entrancePassQueue;
    private CarQueue paymentCarQueue;
    private CarQueue exitCarQueue;

    private int carQueueCount;
    private int passQueueCount;

    private Car[][][] cars;

    private int day = 0;
    private int hour = 0;
    private int minute = 0;

    private int tickPause = 100;

    private int weekDayArrivals = 100; 
    private int weekendArrivals = 100; 
    private int weekDayPassArrivals = 50;
    private int weekendPassArrivals = 25;
    private int weekReservedArrivals = 50;
    private int weekendReservedArrivals = 50;
    public static int numberOfPassSpots = 130;

    private int enterSpeed = 3;
    private int paymentSpeed = 7;
    private int exitSpeed = 5;

    private int aantalStappen;
    private boolean run;

    public SimulatorModel(int numberOfFloors, int numberOfRows, int numberOfPlaces) {

        this.numberOfFloors = numberOfFloors;
        this.numberOfRows = numberOfRows;
        this.numberOfPlaces = numberOfPlaces;
        this.numberOfOpenSpots = numberOfFloors*numberOfRows*numberOfPlaces;

        entranceCarQueue = new CarQueue();
        entrancePassQueue = new CarQueue();
        paymentCarQueue = new CarQueue();
        exitCarQueue = new CarQueue();
        cars = new Car[numberOfFloors][numberOfRows][numberOfPlaces];
        setParkingPassSpots();
        this.numberOfOpenOtherSpots = numberOfOpenSpots - numberOfPassSpots;
        super.notifyViews();
    }

    @Override
    public void run() {
        run = true;
        for (int i=0;i < aantalStappen && run;i++) {
            tick();
            try {
                Thread.sleep(tickPause);
            } catch (Exception e) {}
        }
    }

    private void tick() {
        resetTrackPresentCarsAndSpots();
        advanceTime();
        handleExit();
        tickCarPark();
        trackPresentCarsAndSpots();
        super.notifyViews();
        handleEntrance();
        handleReservations();
    }

    private void trackPresentCarsAndSpots(){
        for (int floor = 0; floor < numberOfFloors; floor++) {
            for (int row = 0; row < numberOfRows; row++) {
                for (int place = 0; place < numberOfPlaces; place++) {
                    Location location = new Location(floor, row, place);
                    Car car = this.getCarAt(location);
                    if (car != null) {
                        int type = car.getType();
                        switch(type)
                        {
                            case 1:
                                amountOfAd_Hoc++;
                                break;
                            case 2:
                                amountOfPassCars++;
                                break;
                            case 3:
                                amountOfReservedSpots++;
                                break;
                            case 4:
                                amountOfReservedCars++;
                                break;
                            case 5:
                                amountOfPassSpots++;
                        }
                    }
                }
            }
        }
    }

    private void resetTrackPresentCarsAndSpots(){
        amountOfAd_Hoc = 0;
        amountOfPassCars = 0;
        amountOfPassSpots = 0;
        amountOfReservedSpots = 0;
        amountOfReservedCars = 0;
    }



    private void advanceTime() {
        minute++;
        while (minute > 59) {
            minute -= 60;
            hour++;
        }
        while (hour > 23) {
            hour -= 24;
            day++;
        }
        while (day > 6) {
            day -= 7;
        }
    }

    private void setParkingPassSpots() {
        for (int floor = 0; floor < numberOfFloors; floor++) {
            for (int row = 0; row < numberOfRows; row++) {
                for (int place = 0; place < numberOfPlaces; place++) {
                    if(numberOfOpenPassSpots < numberOfPassSpots) {
                        Location location = new Location(floor, row, place);
                        Car car = new ParkingPassSpot();
                        setSpotAt(location, car);
                        numberOfOpenPassSpots++;
                    }
                }
            }
        }
    }

    private void tickCarPark() {
        for (int floor = 0; floor < numberOfFloors; floor++) {
            for (int row = 0; row < numberOfRows; row++) {
                for (int place = 0; place < numberOfPlaces; place++) {
                    Location location = new Location(floor, row, place);
                    Car car = this.getCarAt(location);
                    if (car != null) {
                        car.tick();
                    }
                }
            }
        }
    }

    private void handleExit() {
        carsReadyToLeave();
        carsPaying();
        carsLeaving();	
    }

    private void carsReadyToLeave() {
        Car car = getFirstLeavingCar();         // Takes the first leaving car to start the loop with.

        while (car != null) {
            if (car.getHasToPay()) {
                car.setIsPaying(true);
                paymentCarQueue.addCar(car);
            } else {
                carLeavesSpot(car);
            }
            car = getFirstLeavingCar();         // Adds the next leaving car to the loop.
        }
    }

    private Car getFirstLeavingCar() {
        for (int floor = 0; floor < numberOfFloors; floor++) {
            for (int row = 0; row < numberOfRows; row++) {
                for (int place = 0; place < numberOfPlaces; place++) {
                    Location location = new Location(floor, row, place);
                    Car car = this.getCarAt(location);
                    if (car != null && car.getMinutesLeft() <= 0 && !car.getIsPaying() && car.getType() != 3) {
                        return car;
                    }
                }
            }
        }
        return null;
    }

    private void carLeavesSpot(Car car) {

        if(car.getType() == 2) {
            Location location = car.getLocation();
            removeCarAt(location);
            Car spot = new ParkingPassSpot();
            setSpotAt(location, spot);
        } else {
            removeCarAt(car.getLocation());
        }
        exitCarQueue.addCar(car);
    }

    private Car removeCarAt(Location location) {
        if (!locationIsValid(location)) {
            return null;
        }
        Car car = getCarAt(location);
        if (car == null) {
            return null;
        }
        cars[location.getFloor()][location.getRow()][location.getPlace()] = null;
        car.setLocation(null);
        numberOfOpenSpots++;
        return car;
    }

    private void carsPaying() {
        // Let cars pay.
        int i = 0;
        while (paymentCarQueue.carsInQueue() > 0 && i < paymentSpeed) {
            Car car = paymentCarQueue.removeCar();
            int type = car.getType();
            if (type == 1){
                pay = car.getAdHocPay();
            }
            if (type == 2){
                pay = 0.0;
            }
            if (type == 4) {
                pay = car.getReservedPay();
            }
            this.AdHocPay = car.getAdHocPay();
            this.ReservedPay = car.getReservedPay();
            carLeavesSpot(car);
            i++;
        }
    }

    public double getPay(){return pay;}

    public double getAdHocPay(){return AdHocPay;}

    public double getReservedPay(){return ReservedPay;}

    private void carsLeaving() {
        // Let cars leave.
        int i = 0;
        while (exitCarQueue.carsInQueue() > 0 && i < exitSpeed) {
            exitCarQueue.removeCar();
            i++;
        }
    }

    private void handleEntrance() {
        carsArriving();
        carsEntering(entranceCarQueue);
        carsEntering(entrancePassQueue);
        super.notifyViews();
    }

    private void carsArriving() {
        int numberOfCars = getNumberOfCars(weekDayArrivals, weekendArrivals);
        addArrivingCars(numberOfCars, AD_HOC);

        numberOfCars = getNumberOfCars(weekDayPassArrivals, weekendPassArrivals);
        addArrivingCars(numberOfCars, PASS);
    }
    private int getNumberOfCars(int weekDay, int weekend){
        Random random = new Random();

        // Get the average number of cars that arrive per hour.
        int averageNumberOfCarsPerHour = day < 5
                ? weekDay
                : weekend;

        // Calculate the number of cars that arrive this minute.
        double timeFactor = checkTime();
        double standardDeviation = averageNumberOfCarsPerHour * 0.3;
        double numberOfCarsPerHour = averageNumberOfCarsPerHour + random.nextGaussian() * standardDeviation;
        return (int)Math.round((numberOfCarsPerHour / 60)*timeFactor);
    }

    private void addArrivingCars(int numberOfCars, String type){
        // Add the cars to the back of the queue.
        switch(type) {
            case AD_HOC:
                for (int i = 0; i < numberOfCars; i++) {
                    entranceCarQueue.addCar(new AdHocCar());
                    updateQueueCount();
                }
                break;
            case PASS:
                for (int i = 0; i < numberOfCars; i++) {
                    entrancePassQueue.addCar(new ParkingPassCar());
                    updateQueueCount();
                }
                break;
        }
    }

    private void carsEntering(CarQueue queue){
        int i=0;
        // Remove car from the front of the queue and assign to a parking space.
        while (queue.carsInQueue()>0 &&
                numberOfOpenSpots>0 &&
                i<enterSpeed) {
            Car car = queue.removeCar();
            Location freeLocation;
            if(car.getType() == 2) {
                freeLocation = this.getFirstFreeParkingPassLocation();
                removeCarAt(freeLocation);
            }
            if(car.getType() == 4) {
                freeLocation = this.getFirstArrivingLocation();
                removeCarAt(freeLocation);
            } else {
                freeLocation = this.getFirstFreeLocation();
            }
            this.setCarAt(freeLocation, car);
            i++;
            updateQueueCount();
        }
    }

    private Location getFirstFreeLocation() {
        for (int floor = 0; floor < numberOfFloors; floor++) {
            for (int row = 0; row < numberOfRows; row++) {
                for (int place = 0; place < numberOfPlaces; place++) {
                    Location location = new Location(floor, row, place);
                    if (this.getCarAt(location) == null) {
                        return location;
                    }
                }
            }
        }
        return null;
    }

    private Location getFirstArrivingLocation() {
        for (int floor = 0; floor < numberOfFloors; floor++) {
            for (int row = 0; row < numberOfRows; row++) {
                for (int place = 0; place < numberOfPlaces; place++) {
                    Location location = new Location(floor, row, place);
                    Car car = this.getCarAt(location);
                    if(car != null) {
                        if (car.getType() == 3 && car.getArriving()) {
                            return location;
                        }
                    }
                }
            }
        }
        return null;
    }

    private Location getFirstFreeParkingPassLocation() {
        for (int floor = 0; floor < numberOfFloors; floor++) {
            for (int row = 0; row < numberOfRows; row++) {
                for (int place = 0; place < numberOfPlaces; place++) {
                    Location location = new Location(floor, row, place);
                    Car car = this.getCarAt(location);
                    if(car != null) {
                        if (car.getType() == 5) {
                            return location;
                        }
                    }
                }
            }
        }
        return null;
    }


    public Car getCarAt(Location location) {
        if (!locationIsValid(location)) {
            return null;
        }
        return cars[location.getFloor()][location.getRow()][location.getPlace()];
    }

    private boolean setCarAt(Location location, Car car) {
        if (!locationIsValid(location)) {
            return false;
        }
        Car oldCar = getCarAt(location);
        if (oldCar == null) {
            cars[location.getFloor()][location.getRow()][location.getPlace()] = car;
            car.setLocation(location);
            numberOfOpenSpots--;
            return true;
        }
        return false;
    }

    private boolean setSpotAt(Location location, Car car) {
        if (!locationIsValid(location)) {
            return false;
        }
        Car oldCar = getCarAt(location);
        if (oldCar == null) {
            cars[location.getFloor()][location.getRow()][location.getPlace()] = car;
            car.setLocation(location);
            return true;
        }
        return false;
    }

    private boolean locationIsValid(Location location) {
        int floor = location.getFloor();
        int row = location.getRow();
        int place = location.getPlace();
        if (floor < 0 || floor >= numberOfFloors || row < 0 || row > numberOfRows || place < 0 || place > numberOfPlaces) {
            return false;
        }
        return true;
    }

    public static int getNumberOfFloors() {
        return numberOfFloors;
    }

    public static int getNumberOfRows() {
        return numberOfRows;
    }

    public static int getNumberOfPlaces() {
        return numberOfPlaces;
    }

    public int getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }
    public void start(){
        aantalStappen = 10800;
        new Thread(this).start();
    }
    public void stop(){
        run=false;
    }
    public void stappen(int aantalStappen){
        this.aantalStappen=aantalStappen;
        new Thread(this).start();
    }

    public void reset(){
       // reset(SimulatorModel);
    }
    public int getTickPause(){
        return tickPause;
    }
    public void settickPause(int value){
        tickPause = value;
    }
    public int getenterSpeed(){
        return enterSpeed;
    }
    public int getpaymentSpeed(){
        return paymentSpeed;
    }
    public int getexitSpeed(){
        return exitSpeed;
    }
    public void setSpeed(int enter, int payment, int exit){
        enterSpeed = enter;
        paymentSpeed = payment;
        exitSpeed = exit;
    }

    private void handleReservations() {
        handleNewReservations();
        checkArrivingReservedCars();
    }

    private void handleNewReservations() {
        int numberOfSpots = getNumberOfCars(weekReservedArrivals, weekendReservedArrivals);
        for(int i = 0; i < numberOfSpots; i++) {
            ReservedCarSpot spot = new ReservedCarSpot();
            Location freeLocation = this.getFirstFreeLocation();
            this.setCarAt(freeLocation, spot);
        }
    }
    public int getweekDayArrivals(){
        return weekDayArrivals;
    }
    public int getweekendArrivals(){
        return weekendArrivals;
    }
    public int getweekDayPassArrivals(){
        return weekDayPassArrivals;
    }
    public int getweekendPassArrivals(){
        return weekendPassArrivals;
    }
    public int getweekReservedArrivals(){
        return weekReservedArrivals;
    }
    public int getweekendReservedArrivals(){
        return weekendReservedArrivals;
    }

    public void setValuesArrivals(int weekDayArrivals, int weekendArrivals, int weekDayPassArrivals, int weekendPassArrivals, int weekReservedArrivals, int weekendReservedArrivals){
        this.weekDayArrivals = weekDayArrivals;
        this.weekendArrivals = weekendArrivals;
        this.weekDayPassArrivals = weekDayPassArrivals;
        this.weekendPassArrivals = weekendPassArrivals;
        this.weekReservedArrivals = weekReservedArrivals;
        this.weekendReservedArrivals = weekendReservedArrivals;
    }

    private void checkArrivingReservedCars() {
        for (int floor = 0; floor < numberOfFloors; floor++) {
            for (int row = 0; row < numberOfRows; row++) {
                for (int place = 0; place < numberOfPlaces; place++) {
                    Location location = new Location(floor, row, place);

                    Car car = this.getCarAt(location);
                    if(car != null) {
                        if (car.getType() == 3) {                                                       // Looks for every location in the carpark where the color is Yellow
                            if (car.getMinutesLeft() < 30 && !car.getArriving()) {               // Looks if the reserved spot is there for at least 15 minutes
                                Random random = new Random();
                                int arrivalAttempt = random.nextInt(100);                       // Tries to let the car arrive at the carpark. If the number is higher than 91,
                                if (arrivalAttempt > 90) {                                              // the car who made the reservation will arrive. There is a 30 minute window
                                    car.setArriving();                                                  // for the car to arrive. Every tick it will try again, until 30 minutes have past
                                    entrancePassQueue.addCar(new ReservedCar());                        // In total, that gives a probability of 0.059% for a car not to show up.
                                    updateQueueCount();
                                }
                            }
                            if (car.getMinutesLeft() <= 0 && !car.getArriving()) {               // Checks if the minutesLeft to get to the carpark is on 0,
                                removeCarAt(location);                                                  // and if the car is not waiting in the queue.
                            }                                                                           // If its on 0, the car has not shown up. It will remove the spot.
                        }
                    }
                }
            }
        }
    }

    private void updateQueueCount() {
        carQueueCount = entranceCarQueue.carsInQueue();
        passQueueCount = entrancePassQueue.carsInQueue();
    }

    public int getCarQueueCount() {
        return carQueueCount;
    }

    public int getPassQueueCount() {
        return passQueueCount;
    }

    private double checkTime() {
        double factor = 1;
        if(day <= 5) {
            if(hour >= 0 && hour < 6) {
                factor = 0.5;
            }
            if(hour >= 7 && hour < 9) {
                factor = 1.3;
            }
            if(hour >= 12 && hour < 14) {
                factor = 1.3;
            }
            if(hour > 18 && hour < 24) {
                factor = 0.8;
            }
        }
        if(day == 3 && hour >= 19 && hour < 22) {
            factor = 1.3;
        }
        if(day == 4 && hour >= 19 && hour <22) {
            factor = 1.3;
        }
        if(day == 5) {
            if(hour >= 0 && hour < 7) {
                factor = 0.5;
            }
            if(hour >= 12 && hour < 14) {
                factor = 1.2;
            }
            if(hour >= 19 && hour < 20) {
                factor = 1.2;
            }
        }
        if(day == 6) {
            if(hour >= 0 && hour < 7) {
                factor = 0.5;
            }
            if(hour >= 9 && hour < 10) {
                factor = 1.2;
            }
            if (hour >= 12 && hour < 14) {
                factor = 1.2;
            }
        }

        return factor;
    }

    public int getAmountOfAd_Hoc() {
        return amountOfAd_Hoc;
    }

    public int getAmountOfPassCars() {
        return amountOfPassCars;
    }

    public int getAmountOfPassSpots() {
        return amountOfPassSpots;
    }

    public int getAmountOfReservedSpots() {
        return amountOfReservedSpots;
    }

    public int getAmountOfReservedCars() {
        return amountOfReservedCars;
    }
}