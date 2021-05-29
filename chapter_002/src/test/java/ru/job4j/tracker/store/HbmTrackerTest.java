package ru.job4j.tracker.store;

import org.junit.Test;
import ru.job4j.tracker.model.Item;

import java.sql.Timestamp;
import java.util.List;

import static org.junit.Assert.*;

public class HbmTrackerTest {

    @Test
    public void whenAdd() {
        Store store = new HbmTracker();
        Item item = new Item("Learn Hibernate 3", "Description for Learn Hibernate 3",
                new Timestamp(1459510232000L));
        Item savedItem = store.add(item);
        List<Item> list = store.findAll();
        assertEquals(savedItem, list.get(0));
    }

    @Test
    public void whenFindAll() {
        Store store = new HbmTracker();
        Item item1 = new Item("Learn Hibernate 3", "Description for Learn Hibernate 3",
                new Timestamp(1459510232000L));
        Item item2 = new Item("Learn Hibernate 4", "Description for Learn Hibernate 4",
                new Timestamp(1459510232000L));
        Item savedItem1 = store.add(item1);
        Item savedItem2 = store.add(item2);
        assertEquals(List.of(savedItem1, savedItem2), store.findAll());
    }

    @Test
    public void whenDelete() {
        Store store = new HbmTracker();
        Item item1 = new Item("Learn Hibernate 3", "Description for Learn Hibernate 3",
                new Timestamp(1459510232000L));
        Item item2 = new Item("Learn Hibernate 4", "Description for Learn Hibernate 4",
                new Timestamp(1459510232000L));
        Item savedItem1 = store.add(item1);
        Item savedItem2 = store.add(item2);
        store.delete(1);
        assertEquals(List.of(savedItem2), store.findAll());
    }

    @Test
    public void whenReplace() {
        Store store = new HbmTracker();
        Item item = new Item("Learn Hibernate 3", "Description for Learn Hibernate 3",
                new Timestamp(1459510232000L));
        Item savedItem = store.add(item);
        item.setName("Learn Hibernate 4.");
        store.replace(savedItem.getId(), item);
        List<Item> list = store.findAll();
        assertEquals(item, list.get(0));
    }

    @Test
    public void whenFindById() {
        Store store = new HbmTracker();
        Item item1 = new Item("Learn Hibernate 3", "Description for Learn Hibernate 3",
                new Timestamp(1459510232000L));
        Item item2 = new Item("Learn Hibernate 4", "Description for Learn Hibernate 4",
                new Timestamp(1459510232000L));
        Item savedItem1 = store.add(item1);
        Item savedItem2 = store.add(item2);
        Item foundItem = store.findById(2);
        assertEquals(foundItem, savedItem2);
    }
}
