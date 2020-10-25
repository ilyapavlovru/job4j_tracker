package ru.job4j.net;

public class FileDownload {
    public static void main(String[] args) {
        String file = args[0];
        double userSpeed = Double.parseDouble(args[1]) * 1024;
        Thread fileDownload = new Thread(new FileDownloadProgress(file, userSpeed));
        fileDownload.start();
    }
}
