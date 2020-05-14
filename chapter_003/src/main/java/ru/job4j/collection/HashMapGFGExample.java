package ru.job4j.collection;

// Java program to demonstrate
// computeIfPresent(Key, BiFunction) method.

import java.util.*;

public class HashMapGFGExample {
    public static void main(String[] args) {
        // Create a HashMap and add some values
        HashMap<String, Integer> wordCount = new HashMap<>();
        wordCount.put("Geeks", 1);
        wordCount.put("for", 2);
        wordCount.put("geeks", 3);

        // print HashMap details
        System.out.println("Hashmap before operation :\n "
                + wordCount);

        // provide new value for keys which is present
        // using computeIfPresent method
        wordCount.computeIfPresent("for",
                (key, val) -> val + 1);

        // print new mapping
        System.out.println("HashMap after operation :\n "
                + wordCount);
    }
}