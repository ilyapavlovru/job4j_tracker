package ru.job4j.tracker;

public class FindByIdAction implements UserAction {

    private final Output out;

    public FindByIdAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "=== Find item by Id ====";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        String id = input.askStr("Enter id to find items: ");
        Item item = tracker.findById(id);
        if (item != null) {
            out.println("id: " + item.getId() + "; name: " + item.getName());
        } else {
            out.println("Not found");
        }
        return true;
    }
}
