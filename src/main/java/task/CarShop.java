package task;

import java.util.ArrayList;
import java.util.List;

public class CarShop {
    private final Producer producer;
    private final static int TIMEOUT = 2000;

    private List<Car> cars = new ArrayList<>();
    private boolean isCar = false;
    private String car = "";

    public CarShop() {
        this.producer = new Producer();
    }

    public synchronized void buyCar() {
        do {
            System.out.println(Thread.currentThread().getName() + " прибыл в салон");
            Producer.setListCars(cars);
            try {
                while (!isCar){
                    System.out.println("Машин нет!");
                    wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            isCar = false;
            try {
                Thread.sleep(TIMEOUT);
            } catch (InterruptedException e) {}
            System.out.println(Thread.currentThread().getName() + " поехал на новеньком " + car);
            try {
                Thread.sleep(TIMEOUT);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (isNextCar());
    }

    public synchronized void putCar() {
        while (isNextCar()){
            if (!isCar){
                car = getNextCar();
                isCar = true;
                try {
                    System.out.println("\nПроизводитель " + car + " выпустил 1 авто\n");
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
                notifyAll();
            }
            try {
                wait(Producer.INTERVAL);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Thread.currentThread().interrupt();
    }


    private String getNextCar() {
        return isNextCar() ? cars.remove(0).getName() : null;
    }

    private boolean isNextCar() {
        return cars.size() > 0;
    }
}
