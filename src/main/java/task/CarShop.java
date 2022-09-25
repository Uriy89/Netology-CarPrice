package task;

import java.util.ArrayList;
import java.util.List;


public class CarShop extends Thread {

    private final List<Car> cars = new ArrayList<>();
    private static final int TIMEOUT = 3000;
    private static final int CREATE = 1000;
    private static int count = 10;

    public void buyCar() {
        synchronized (this) {

            while (count > 0) {
                System.out.println(Thread.currentThread().getName() + " прибыл в салон");
                try {
                    Thread.sleep(TIMEOUT);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (cars.size() == 0) {
                    System.out.println("Машин нет!");
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        Thread.sleep(TIMEOUT);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    System.out.println(Thread.currentThread().getName() + " поехал на новеньком авто");
                    count--;
                    cars.remove(0);
                }
            }
        }
    }


    public void produceCar() {
        while (count > 0) {
            try {
                Thread.sleep(CREATE);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (this) {
                cars.add(new Car());
                String name = cars.get(0).getName();
                System.out.println("\nПроизводитель " + name + " выпустил 1 авто\n");
                notifyAll();
            }
        }
    }

}
