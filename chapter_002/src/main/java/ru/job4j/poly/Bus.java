package ru.job4j.poly;

public class Bus implements Transport {

    private double volume;
    private double passengers;

    @Override
    public void drive() {
        volume -= 10;
    }

    @Override
    public void passengers(int numPassengers) {
        passengers += numPassengers;
    }

    @Override
    public double refuel(double fuelQty) {
        volume += fuelQty;
        return fuelQty * 10;
    }
}
