package ru.job4j.tracker;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ItemSorter {
    public static void main(String[] args) {
        List<Item> items = Arrays.asList(
                new Item("item3"),
                new Item("item1"),
                new Item("item2")
        );
        System.out.println(items);
        Collections.sort(items, new SortByNameItem());
        System.out.println(items);
        Collections.sort(items, new SortReversedByNameItem());
        System.out.println(items);
    }
}
