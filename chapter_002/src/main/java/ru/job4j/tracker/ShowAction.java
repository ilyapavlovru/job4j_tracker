package ru.job4j.tracker;

import java.util.List;

public class ShowAction implements UserAction {
    @Override
    public String name() {
        return "=== Show all items ====";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        List<Item> items = tracker.findAll();
        for (int index = 0; index < items.size(); index++) {
            Item item = items.get(index);
            System.out.println("id: " + item.getId() + "; name: " + item.getName());
        }
        return true;
    }
}
