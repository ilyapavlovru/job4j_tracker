package ru.job4j.pools.threadpool.threadsafesingleton.ver3;

public class TrackerSingle {

    private static TrackerSingle INSTANCE;

    private TrackerSingle() {
    }

    public static synchronized TrackerSingle getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TrackerSingle();
        }
        return INSTANCE;
    }

//    public Item add(Item model) {
//        return model;
//    }

    public static void main(String[] args) {
        TrackerSingle tracker = TrackerSingle.getInstance();
    }
}
