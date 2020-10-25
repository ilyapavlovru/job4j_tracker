package ru.job4j.net;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class FileDownloadProgress implements Runnable {

    private String file;
    private double userSpeed;

    public FileDownloadProgress(String file, double userSpeed) {
        this.file = file;
        this.userSpeed = userSpeed;
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        try (BufferedInputStream in = new BufferedInputStream(new URL(file).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream("downloaded_file.zip")) {
            byte[] dataBuffer = new byte[8192];
            int bytesRead;
            int totalBytesRead = 0;
            int step = 0;
            while ((bytesRead = in.read(dataBuffer, 0, 8192)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                System.out.println("bytesRead = " + bytesRead + ";  totalBytesRead = " + totalBytesRead + " bytes");
                totalBytesRead += bytesRead;
                long endTime = System.nanoTime();
                long nanoTimeBytesRead = endTime - lastTime;
                double timeBytesRead = (double) nanoTimeBytesRead / 1_000_000_000;
                System.out.println("timeBytesRead (sec) = " + timeBytesRead);
                double curSpeed =  (double) bytesRead / timeBytesRead;
                System.out.println("current speed (bytes per second) = " + curSpeed);
                double newTimeNeed = timeBytesRead * curSpeed / userSpeed;
                int delay = (int)((newTimeNeed - timeBytesRead) * 1000);
                delay = Math.max(delay, 0);
                System.out.println("current delay (ms) = " + delay);
                lastTime = System.nanoTime();
                System.out.println("do sleep and continue file downloading");
                if (step == 0) {
                    delay = 1000;
                }
                Thread.sleep(delay);
                step++;
                System.out.println("--------------------------------------");
            }
            System.out.println("totalBytesRead = " + totalBytesRead);
        } catch (InterruptedException | IOException e) {
            Thread.currentThread().interrupt();
        }
    }
}
