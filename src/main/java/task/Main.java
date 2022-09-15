package task;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final int SLEEP = 3000;
    private static final int MAX = 10;
    private static final int CUSTOMERS = 3;

    public static void main(String[] args) throws InterruptedException {

        List<String> names = new ArrayList<>();

        List<String> car = new ArrayList<>();

        new Thread(() -> {
            for (int i = 0; i < MAX; i++) {
                synchronized (car) {
                    car.add("Toyota");
                    System.out.println("Производитель Toyota выпустил 1 авто");
                    car.notify();
                }
                try {
                    Thread.sleep(SLEEP);
                } catch (InterruptedException e) {
                    return;
                }

            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < MAX; i++) {
                names.add("Покупатель" + i);
                System.out.println(names.get(i) + " зашел в магазин");
                synchronized (car) {
                    if (!car.isEmpty()) {
                        System.out.println("Покупатель" + i + " уехал на новеньком авто " + car.remove(0));
                    } else {
                        System.out.println("Машин нет");
                        try {
                            car.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }
}
