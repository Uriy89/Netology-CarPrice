package task;

public class Main {

    public static void main(String[] args) {
        final CarShop shop = new CarShop();

        Runnable shopping = shop::buyCar;
        Thread bayer1 = new Thread(null, shopping, "Покупатель1");
        bayer1.start();
        Thread bayer2 = new Thread(null, shopping, "Покупатель2");
        bayer2.start();
        Thread bayer3 = new Thread(null, shopping, "Покупатель3");
        bayer3.start();
        Thread bayer4 = new Thread(null, shopping, "Покупатель4");
        bayer4.start();

        new Thread(null, shop::produceCar, "Автопром").start();
    }


}
