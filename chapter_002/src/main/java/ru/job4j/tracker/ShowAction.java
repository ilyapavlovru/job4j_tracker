package ru.job4j.tracker;

public class ShowAction implements UserAction {
    @Override
    public String name() {
        return "=== Show all items ====";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        Item[] items = tracker.findAll();
        for (int index = 0; index < items.length; index++) {
            Item item = items[index];
            System.out.println("id: " + item.getId() + "; name: " + item.getName());
        }
        return true;
    }
}
