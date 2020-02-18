package ru.job4j.tracker;

public class DeleteAction implements UserAction {
    @Override
    public String name() {
        return "=== Delete item by id ====";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        String id = input.askStr("Enter id to delete item: ");
        if (tracker.delete(id)) {
            System.out.println("Successfully deleted item");
        } else {
            System.out.println("Not found id to delete item");
        }
        return true;
    }
}
