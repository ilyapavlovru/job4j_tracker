package ru.job4j.concurrent;

public class ThreadState {
    public static void main(String[] args) {
        System.out.println("Main thread started...");
        Thread first = new Thread(
                () -> {
                    System.out.println("Creating first thread...name: " + Thread.currentThread().getName());
                }
                );
        System.out.println("state of first: " + first.getState());
        first.start();

        Thread second = new Thread(
                () -> {
                    System.out.println("Creating second thread...name: " + Thread.currentThread().getName());
                }
        );
        System.out.println("state of second: " + second.getState());
        second.start();

        while ((first.getState() != Thread.State.TERMINATED) ||
                (second.getState() != Thread.State.TERMINATED)) {
            System.out.println("in while:");
            System.out.println("state of first: " + first.getState());
            System.out.println("state of second: " + second.getState());
        }

        System.out.println("after while:");
        System.out.println("state of first: " + first.getState());
        System.out.println("state of second: " + second.getState());
        System.out.println("Main thread finished...");
    }
}
