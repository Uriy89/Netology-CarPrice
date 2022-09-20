package task;

import java.util.ArrayList;
import java.util.List;

public class Producer extends Thread {
    private final static int CARS_COUNT = 10;
    protected final static int INTERVAL = 1500;

    public static void setListCars(List<Car> cars){
        for (int i =0; i < CARS_COUNT; i++) {
            cars.add(new Car());
        }
    }

}
