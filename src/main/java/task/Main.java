package task;

public class Main {

    public static void main(String[] args) {
        final CarShop shop = new CarShop();

        Runnable shopping = shop::buyCar;
        for (int i = 1; i < 4; i++) {
            String name = "Покупатель" + i;
            Thread bayer1 = new Thread(null, shopping, name);
            bayer1.start();
        }


        new Thread(null, shop::produceCar, "Автопром").start();
    }


}
