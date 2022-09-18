package task;

public class CarShop {
    private final Producer producer;
    private final int TIMEOUT = 2000;
    private boolean isCar = false;
    private String car = "";

    public CarShop() {
        this.producer = new Producer();
    }

    public synchronized void buyCar() {
        while (producer.isNextCar()){
            System.out.println(Thread.currentThread().getName() + " прибыл в салон");
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
            } catch (InterruptedException e) {}
        }
    }

    public synchronized void putCar() {
        while (producer.isNextCar()){
            if (!isCar){
                car = producer.getNextCar();
                isCar = true;
                System.out.println("\nПроизводитель " + car + " выпустил 1 авто\n");
                notifyAll();
            }
            try {
                wait(producer.INTERVAL);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Thread.currentThread().interrupt();
    }
}
