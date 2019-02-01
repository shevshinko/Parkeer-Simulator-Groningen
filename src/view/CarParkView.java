package view;

import java.awt.*;

import model.Car;
import model.Location;
import model.SimulatorModel;

public class CarParkView extends AbstractView {

    private Image carParkImage;
    private Dimension size;

    public CarParkView(SimulatorModel model) {
        super(model);

        size = new Dimension(0,0);
    }

    public void paintComponent(Graphics g) {
        if (carParkImage == null) {
        return;
    }

    Dimension currentSize = getSize();
    if (size.equals(currentSize)) {
        g.drawImage(carParkImage, 0, 0, null);
    }
        else {
            g.drawImage(carParkImage, 0, 0, currentSize.width, currentSize.height, null);
        }
    }

    public void updateView() {
        if (!size.equals(getSize())) {
            size = getSize();
            carParkImage = createImage(size.width, size.height);
        }

        Graphics graphics = carParkImage.getGraphics();


        for (int floor = 0; floor < model.getNumberOfFloors(); floor++) {
            for (int row = 0; row < model.getNumberOfRows(); row++) {
                for (int place = 0; place < model.getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    Car car = model.getCarAt(location);
                    Color color = car == null ? Color.white : car.getColor();
                    drawPlace(graphics, location, color);
                }
            }
        }
        setVisible(true);
        repaint();
    }

    private void drawPlace(Graphics graphics, Location location, Color color) {
        graphics.setColor(color);
        graphics.fillRect(
                (location.getFloor() * 260 + (1 + (int) Math.floor(location.getRow() * 0.5)) * 60 + (location.getRow() % 2) * 20) -59,
                location.getPlace() * 10 + 30,
                20 - 1,
                10 - 1);
    }

}
