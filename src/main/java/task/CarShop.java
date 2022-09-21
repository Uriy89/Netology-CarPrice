package task;

import java.util.ArrayList;
import java.util.List;

public class CarShop extends Thread {

    private final List<Car> cars = new ArrayList<>();
    private static final int TIMEOUT = 3000;
    private static final int CREATE = 1000;
    private static  int COUNT = 10;
    public synchronized void buyCar() {
        System.out.println(Thread.currentThread().getName() + " прибыл в салон");
        while (COUNT > 0) {
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
                try {
                    Thread.sleep(TIMEOUT);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + " поехал на новеньком авто");
                COUNT--;
                cars.remove(0);
            }
        }
    }


    public void produceCar() {
        while (COUNT > 0) {
            try {
                Thread.sleep(CREATE);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            cars.add(new Car());
            String name = cars.get(0).getName();
            synchronized (this) {
                System.out.println("\nПроизводитель " + name + " выпустил 1 авто\n");
                notifyAll();
            }

        }
    }

}
