package ru.job4j.tracker;

import java.util.List;

public class FindByNameAction implements UserAction {

    private final Output out;

    public FindByNameAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "=== Find items by name ====";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        String name = input.askStr("Enter name to find items: ");
        List<Item> items = tracker.findByName(name);
        for (Item item : items) {
            out.println("id: " + item.getId() + "; name: " + item.getName());
        }
        return true;
    }
}
