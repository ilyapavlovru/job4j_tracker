package ru.job4j.nonblockingalgo.nonblockingseqgenexample;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Sequence implements Runnable {

    Thread thread;
    int id;
    int count;
    SequenceGenerator sg;
    List<BigInteger> sequence = new ArrayList<BigInteger>();
    boolean printed = false;

    public Sequence(final int id, final int count, SequenceGenerator sg) {
        this.id = id;
        this.count = count;
        this.sg = sg;
        thread = new Thread(this);

        System.out.println("Создан поток " + id);
        thread.start();
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < count; i++) {
                sequence.add(sg.next());
                Thread.sleep((long) (Math.random() * 2 + 1) * 30);
            }
        } catch (InterruptedException e) {
            System.out.println("Поток " + id + " прерван");
        }
        System.out.println("Поток " + id + " завершен");
        printSequence();
    }

    public void printSequence() {
        if (printed) {
            return;
        }
        String tmp = "[";
        for (int i = 0; i < sequence.size(); i++) {
            if (i > 0) {
                tmp += ", ";
            }
            String nb = String.valueOf(sequence.get(i));
            while (nb.length() < 9) {
                nb = " " + nb;
            }
            tmp += nb;
        }
        tmp += "]";
        System.out.println("Последовательность потока " + id + " : " + tmp);
        printed = true;
    }
}
