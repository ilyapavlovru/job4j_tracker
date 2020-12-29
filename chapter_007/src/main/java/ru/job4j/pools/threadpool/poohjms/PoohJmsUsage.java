package ru.job4j.pools.threadpool.poohjms;

import java.io.IOException;

public class PoohJmsUsage {

    public static void main(String[] args) throws IOException, InterruptedException {

        // тестирование сервиса в режиме queue
        PoohJms poohJms = new PoohJms("queue");



//
//        JsonMessage jsonMessage = "{
//                "queue" : "weather",
//                "text" : "temperature +18 C"}";
//
//
//        // отправитель отправляет сообщение
//        poohJms.sendMessage(jsonMessage);
    }
}
