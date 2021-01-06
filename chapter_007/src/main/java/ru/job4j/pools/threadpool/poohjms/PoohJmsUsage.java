package ru.job4j.pools.threadpool.poohjms;

import java.io.IOException;

public class PoohJmsUsage {

    public static void main(String[] args) throws IOException, InterruptedException {

        // тестирование сервиса в режиме topic
        PoohJms poohJms = new PoohJms("topic");

    }
}
