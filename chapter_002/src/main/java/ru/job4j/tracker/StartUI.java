package ru.job4j.tracker;

public class StartUI {

    public static void createItem(Input input, Tracker tracker) {
        System.out.println("=== Create a new Item ====");
        String name = input.askStr("Enter name: ");
        Item item = new Item(name);
        tracker.add(item);
    }

    public static void showAllItems(Tracker tracker) {
        System.out.println("=== Show all items ====");
        Item[] items = tracker.findAll();
        for (int index = 0; index < items.length; index++) {
            Item item = items[index];
            System.out.println("id: " + item.getId() + "; name: " + item.getName());
        }
    }

    public static void editItem(Input input, Tracker tracker) {
        System.out.println("=== Edit item ====");
        String id = input.askStr("Enter id to replace item: ");
        String newName = input.askStr("Enter new name: ");
        Item newItem = new Item(newName);
        if (tracker.replace(id, newItem)) {
            System.out.println("Successfully replaced item");
        } else {
            System.out.println("Not found id to replace item");
        }
    }

    public static void deleteItem(Input input, Tracker tracker) {
        String id = input.askStr("Enter id to delete item: ");
        if (tracker.delete(id)) {
            System.out.println("Successfully deleted item");
        } else {
            System.out.println("Not found id to delete item");
        }
    }

    public static void findItemById(Input input, Tracker tracker) {
        System.out.println("=== Find item by Id ====");
        String id = input.askStr("Enter id to find items: ");
        Item item = tracker.findById(id);
        if (item != null) {
            System.out.println("id: " + item.getId() + "; name: " + item.getName());
        } else {
            System.out.println("Not found");
        }
    }

    public static void findItemByName(Input input, Tracker tracker) {
        System.out.println("=== Find items by name ====");
        String name = input.askStr("Enter name to find items: ");
        Item[] items = tracker.findByName(name);
        for (Item item : items) {
            System.out.println("id: " + item.getId() + "; name: " + item.getName());
        }
    }

    public void init(Input input, Tracker tracker) {
        boolean run = true;
        while (run) {
            this.showMenu();
            int select = input.askInt("Select: ");

            // Add new Item
            if (select == 0) {
                StartUI.createItem(input, tracker);

                // Show all items
            } else if (select == 1) {
                StartUI.showAllItems(tracker);

                // Edit item
            } else if (select == 2) {
                StartUI.editItem(input, tracker);

                // Delete item
            } else if (select == 3) {
                StartUI.deleteItem(input, tracker);

                // Find item by Id
            } else if (select == 4) {
                StartUI.findItemById(input, tracker);

                // Find items by name
            } else if (select == 5) {
                StartUI.findItemByName(input, tracker);

            } else if (select == 6) {
                run = false;
            }
        }
    }

    private void showMenu() {
        System.out.println("Menu.");
        System.out.println("0. Add new Item");
        System.out.println("1. Show all items");
        System.out.println("2. Edit item");
        System.out.println("3. Delete item");
        System.out.println("4. Find item by Id");
        System.out.println("5. Find items by name");
        System.out.println("6. Exit Program");
    }


    public static void main(String[] args) {
        Input input = new ConsoleInput();
        Tracker tracker = new Tracker();
        new StartUI().init(input, tracker);
    }
}