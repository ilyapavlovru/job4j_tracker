package ru.job4j.calculator;

public class CarUsage {
    public static void main(String[] args) {
        Car audi = new Car();
        boolean driving = audi.canDrive();
        String carSays = driving ? "I can drive" : "I can't drive";
        System.out.println(carSays);
        System.out.println("Can you drive? : " + driving);
        System.out.println("I am going to a gas station.");
        int gas = 25;
        audi.fill(gas);
        driving = audi.canDrive();
        System.out.println("Can you drive now? : " + driving);
        System.out.println("Now. I am going to my granny.");
        System.out.println("It's 10 kilometers from here.");
        int distance = 10;
        audi.drive(distance);
        audi.gasInfo();

        byte age = 100;

        char sex = 'm';

        short height = 180;

        int bricks = 100500;

        long bacteria = 1223372036854775L;

        double dist = 5456687.234;

        boolean workpermit = true;

        long filesize = 657987214;
    }
}