package ru.job4j.interview;

public class Item {

    public Item() {
        System.out.println("Item конструктор без параметров");
    }


    public Item(String name) {
        System.out.println("Item конструктор с параметром " + name);
    }

    public static void main(String[] args) {
//        Item item = new Item();
        Bug bug = new Bug("Bug");
    }
}