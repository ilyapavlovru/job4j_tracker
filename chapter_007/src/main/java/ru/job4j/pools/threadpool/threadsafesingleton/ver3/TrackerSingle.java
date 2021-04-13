package ru.job4j.pools.threadpool.threadsafesingleton.ver3;

public class TrackerSingle {

    private static TrackerSingle instance;

    private TrackerSingle() {
    }

    public static synchronized TrackerSingle getInstance() {
        if (instance == null) {
            instance = new TrackerSingle();
        }
        return instance;
    }

//    public Item add(Item model) {
//        return model;
//    }

    public static void main(String[] args) {
        TrackerSingle tracker = TrackerSingle.getInstance();
    }
}
