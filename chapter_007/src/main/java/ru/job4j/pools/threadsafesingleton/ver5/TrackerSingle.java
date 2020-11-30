package ru.job4j.pools.threadsafesingleton.ver5;

public class TrackerSingle {

    private TrackerSingle() {
    }

    public static TrackerSingle getInstance() {
        return Holder.INSTANCE;
    }

//    public Item add(Item model) {
//        return model;
//    }

    private static final class Holder {
        private static final TrackerSingle INSTANCE = new TrackerSingle();
    }

    public static void main(String[] args) {
        TrackerSingle tracker = TrackerSingle.getInstance();
    }
}
