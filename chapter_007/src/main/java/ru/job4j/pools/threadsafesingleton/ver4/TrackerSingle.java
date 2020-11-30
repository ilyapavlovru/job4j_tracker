package ru.job4j.pools.threadsafesingleton.ver4;

public class TrackerSingle {

    private static volatile TrackerSingle INSTANCE;

    private TrackerSingle() {
    }

    public static TrackerSingle getInstance() {
        if (INSTANCE == null) {
            synchronized (TrackerSingle.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TrackerSingle();
                }
            }
        }
        return INSTANCE;
    }

//    public static add(Item model) {
//        return model;
//    }

    public static void main(String[] args) {
        TrackerSingle tracker = TrackerSingle.getInstance();
    }
}
