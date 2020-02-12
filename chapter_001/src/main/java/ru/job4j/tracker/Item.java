package ru.job4j.tracker;

public class Item {

    public Item() {
        System.out.println("load Item");
    }

    public Item(String name) {
        System.out.println("load Item with String");
    }

    public static void main(String[] args) {
        Item item = new Item("");
        Bug bug = new Bug("bug");
    }
}