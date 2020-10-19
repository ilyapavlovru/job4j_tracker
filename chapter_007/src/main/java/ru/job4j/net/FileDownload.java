package ru.job4j.net;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class FileDownload {

    private static long lastTime = System.nanoTime();;

    public static void main(String[] args) {

        //String file = "https://examples.javacodegeeks.com/wp-content/uploads/2016/03/JSONRestWebServiceExample.zip";
        //double userSpeed = 500;  // скорость скачивания, заданная пользователем (байтов в секунду)

        // ссылка
        String file = args[0];

        // скорость в килобайтах в секунду
        double userSpeed = Double.parseDouble(args[1]) * 1024;  // в байтах

        Thread thread = new Thread(
                () -> {
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

                            // время потраченное на скачивание bytesRead байт
                            double timeBytesRead = (double) nanoTimeBytesRead / 1_000_000_000;
                            System.out.println("timeBytesRead (sec) = " + timeBytesRead);

                            // скорость скачивания текущая
                            double curSpeed =  (double) bytesRead / timeBytesRead;
                            System.out.println("current speed (bytes per second) = " + curSpeed);

                            // новое время, необходимое для скачивания такого же кол-ва байт bytesRead со скоростью userSpeed
                            double newTimeNeed = timeBytesRead * curSpeed / userSpeed;

                            // задержка, необходимая для новой скорости
                            int delay = (int)((newTimeNeed - timeBytesRead) * 1000);
                            delay = Math.max(delay, 0);
                            System.out.println("current delay (ms) = " + delay);

                            lastTime = System.nanoTime();

                            // делаем эту задержку
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
        );
        thread.start();
        System.out.println("Main");
    }
}
