package ru.job4j.tracker;

import java.util.Scanner;

public class StartUI {

    public void init(Scanner scanner, Tracker tracker) {
        boolean run = true;
        while (run) {
            this.showMenu();
            System.out.print("Select: ");
            int select = Integer.parseInt(scanner.nextLine());
            // Add new Item
            if (select == 0) {
                System.out.println("=== Create a new Item ====");
                System.out.print("Enter name: ");
                String name = scanner.nextLine();
                Item item = new Item(name);
                tracker.add(item);

                // Show all items
            } else if (select == 1) {
                System.out.println("=== Show all items ====");
                Item[] items = tracker.findAll();
                for (int index = 0; index < items.length; index++) {
                    Item item = items[index];
                    System.out.println("id: " + item.getId() + "; name: " + item.getName());
                }

                // Edit item
            } else if (select == 2) {
                System.out.println("=== Edit item ====");
                System.out.print("Select position: ");
                int position = Integer.parseInt(scanner.nextLine());
                System.out.print("Enter new name: ");
                String newName = scanner.nextLine();
                tracker.edit(position, newName); // замена по позиции
                //tracker.edit(oldName, newName); // замена по имени

                // Delete item
            } else if (select == 3) {
                System.out.println("=== Delete item ====");
                System.out.print("Select position: ");
                int position = Integer.parseInt(scanner.nextLine());
                tracker.delete(position); // удаление по позиции

                // Find item by Id
            } else if (select == 4) {
                System.out.println("=== Find item by Id ====");
                System.out.print("Enter id to find items: ");
                String id = scanner.nextLine();
                Item item = tracker.findById(id);
                System.out.println("id: " + item.getId() + "; name: " + item.getName());

                // Find items by name
            } else if (select == 5) {
                System.out.println("=== Find items by name ====");
                System.out.print("Enter name to find items: ");
                String name = scanner.nextLine();
                Item[] items = tracker.findByName(name);
                for (int index = 0; index < items.length; index++) {
                    Item item = items[index];
                    System.out.println("id: " + item.getId() + "; name: " + item.getName());
                }

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
        Scanner scanner = new Scanner(System.in);
        Tracker tracker = new Tracker();
        new StartUI().init(scanner, tracker);
    }
}