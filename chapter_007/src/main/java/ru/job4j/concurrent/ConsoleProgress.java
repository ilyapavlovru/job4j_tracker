package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {

    @Override
    public void run() {
        int count = 0;
        while (!Thread.currentThread().isInterrupted()) {
            System.out.print("\rLoading ... " + process(count));

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count++;
        }
    }

    private String process(int count) {
        String rst = null;
        int k = count % 4;
        switch (k) {
            case 0:
                rst = "-";
                break;
            case 1:
                rst = "\\";
                break;
            case 2:
                rst = "|";
                break;
            case 3:
                rst = "/";
                break;
            default:
                break;
        }
        return rst;
    }
}
