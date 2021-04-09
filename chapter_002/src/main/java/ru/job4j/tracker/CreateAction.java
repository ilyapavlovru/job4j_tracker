package ru.job4j.tracker;

public class CreateAction implements UserAction {

    private final Output out;

    public CreateAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "=== Create a new Item ====";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        out.println("Enter name: ");
        String name = input.askStr("");
        Item item = new Item(name);
        tracker.add(item);
        return true;
    }
}
