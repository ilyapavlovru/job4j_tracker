package ru.job4j.pools.threadpool.threadsafesingleton.ver4;

public class TrackerSingle {

    private static volatile TrackerSingle instance;

    private TrackerSingle() {
    }

    public static TrackerSingle getInstance() {
        if (instance == null) {
            synchronized (TrackerSingle.class) {
                if (instance == null) {
                    instance = new TrackerSingle();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        TrackerSingle tracker = TrackerSingle.getInstance();
    }
}
