package ru.job4j.tracker;

public class FindByNameAction implements UserAction {
    @Override
    public String name() {
        return "=== Find items by name ====";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        String name = input.askStr("Enter name to find items: ");
        Item[] items = tracker.findByName(name);
        for (Item item : items) {
            System.out.println("id: " + item.getId() + "; name: " + item.getName());
        }
        return true;
    }
}
