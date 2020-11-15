package ru.job4j.nonblockingalgo.nonblockingalgorithm;

public class CASCountUsage {
    public static void main(String[] args) {
        CASCount<Integer> casCount = new CASCount<>();
        casCount.increment();
        System.out.println("The value after increment is " + casCount.get());
    }
}
