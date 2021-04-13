package ru.job4j.pools.threadpool.socketsandhttpexamples;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class SocketServerExample {
    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(8080);
             Socket socket = serverSocket.accept();
             Scanner scanner = new Scanner(socket.getInputStream())) {

            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            while (scanner.hasNextLine()) {
                String str = scanner.nextLine();
                printWriter.println("you've send: " + str);
                System.out.println(str);
                if (str.equals("exit")) {
                    break;
                }
            }
        }
    }
}
