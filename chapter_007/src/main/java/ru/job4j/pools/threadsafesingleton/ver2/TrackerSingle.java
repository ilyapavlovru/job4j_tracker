package ru.job4j.pools.threadsafesingleton.ver2;

public class TrackerSingle {

    private static final TrackerSingle INSTANCE = new TrackerSingle();

    private TrackerSingle() {
    }

    public static TrackerSingle getInstance() {
        return INSTANCE;
    }

//    public Item add(Item model) {
//        return model;
//    }

    public static void main(String[] args) {
        TrackerSingle tracker = TrackerSingle.getInstance();
    }
}
