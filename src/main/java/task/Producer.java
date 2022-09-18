package task;

import java.util.ArrayList;
import java.util.List;

public class Producer extends Thread {
    private final int CARS_COUNT = 10;
    protected final static int INTERVAL = 1500;
    private final List<Car> cars = new ArrayList<>();


    public Producer() {
        setListCars();
    }

    private void setListCars(){
        for (int i =0; i < CARS_COUNT; i++) {
            cars.add(new Car());
        }
    }

    public String getNextCar() {
        return isNextCar() ? cars.remove(0).getName() : null;
    }

    public boolean isNextCar() {
        return cars.size() > 0;
    }

}
