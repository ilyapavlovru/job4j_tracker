package ru.job4j.pools.threadpool.socketsandhttpexamples;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class SimpleHTTPServer {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8080);
        System.out.println("Listening for connection on port 8080 ....");
        while (true) {
            try (Socket socket = server.accept()) {
                Date today = new Date();
                String httpResponse = "HTTP/1.1 200 OK\r\n\r\n" + today;
                socket.getOutputStream().write(httpResponse.getBytes("UTF-8"));

                // понять что нам приходит get или post запрос
                // если get то мы должны из очереди прочитать сообщение
                // если post то мы должны в очередь добавить сообщение
            }
        }
    }
}
