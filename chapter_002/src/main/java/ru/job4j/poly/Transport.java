package ru.job4j.poly;

public interface Transport {
    void drive();
    void passengers(int numPassengers);
    double refuel(double fuelQty);
}
