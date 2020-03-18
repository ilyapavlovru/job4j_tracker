package ru.job4j.tracker;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ItemSortTest {
    @Test
    public void whenSortByName() {
        List<Item> items = Arrays.asList(
                new Item("item3"),
                new Item("item1"),
                new Item("item2")
        );
        List<Item> result = Arrays.asList(
                new Item("item1"),
                new Item("item2"),
                new Item("item3")
        );
        Collections.sort(items, new SortByNameItem());
        assertThat(result.toString(), is(items.toString()));
    }

    @Test
    public void whenSortReversedByName() {
        List<Item> items = Arrays.asList(
                new Item("item3"),
                new Item("item1"),
                new Item("item2")
        );
        List<Item> result = Arrays.asList(
                new Item("item3"),
                new Item("item2"),
                new Item("item1")
        );
        Collections.sort(items, new SortReversedByNameItem());
        assertThat(result.toString(), is(items.toString()));
    }

}


