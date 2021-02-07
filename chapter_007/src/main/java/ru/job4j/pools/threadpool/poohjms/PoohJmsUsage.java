package ru.job4j.pools.threadpool.poohjms;

import java.io.IOException;

public class PoohJmsUsage {
    public static void main(String[] args) throws IOException, InterruptedException {
        //PoohJms poohJms = new PoohJms("queue");
        PoohJms poohJms = new PoohJms("topic");
    }
}
