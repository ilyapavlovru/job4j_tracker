package ru.job4j.nonblockingalgo.nonblockingseqgenexample;

import java.util.ArrayList;
import java.util.List;

public class SequenceGeneratorExample {
    public static void main(String[] args) {
        SequenceGenerator sg = new SequenceGenerator();
        List<Sequence> sequences = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Sequence seq = new Sequence(i + 1, 3, sg);
            sequences.add(seq);
        }
        System.out.println("\nРасчет последовательностей\n");
        int summa;
        // Ожидание завершения потоков
        do {
            summa = 0;
            for (int i = 0; i < sequences.size(); i++) {
                if (!sequences.get(i).thread.isAlive()) {
                    sequences.get(i).printSequence();
                    summa++;
                }
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {

            }
        } while (summa < sequences.size());
        System.out.println("\n\nРабота потоков завершена");
        System.exit(0);
    }

}
