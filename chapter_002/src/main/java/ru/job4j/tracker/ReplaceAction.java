package ru.job4j.tracker;

public class ReplaceAction implements UserAction {

    private final Output out;

    public ReplaceAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "=== Edit item ====";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        String id = input.askStr("Enter id to replace item: ");
        String newName = input.askStr("Enter new name: ");
        Item newItem = new Item(newName);
        if (tracker.replace(id, newItem)) {
            out.println("Successfully replaced item");
        } else {
            out.println("Not found id to replace item");
        }
        return true;
    }
}
