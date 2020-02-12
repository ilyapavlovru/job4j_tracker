package ru.job4j.tracker;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class TrackerTest {
    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1");
        tracker.add(item);
        Item result = tracker.findById(item.getId());
        assertThat(result.getName(), is(item.getName()));
    }

    @Test
    public void whenAdd3NewItemsThenTrackerHasSame3Items() {
        Tracker tracker = new Tracker();
        Item item0 = new Item("test0");
        Item item1 = new Item("test1");
        Item item2 = new Item("test2");
        tracker.add(item0);
        tracker.add(item1);
        tracker.add(item2);

        String[] arr = new String[3];
        arr[0] = item0.getName();
        arr[1] = item1.getName();
        arr[2] = item2.getName();

        String[] result_arr = new String[3];
        result_arr[0] = tracker.findById(item0.getId()).getName();
        result_arr[1] = tracker.findById(item1.getId()).getName();
        result_arr[2] = tracker.findById(item2.getId()).getName();

        assertThat(result_arr, is(arr));
    }


    @Test
    public void whenAdd3NewItemsThenTrackerHasSame3ItemsViaFindAll() {
        Tracker tracker = new Tracker();
        Item item0 = new Item("test0");
        Item item1 = new Item("test1");
        Item item2 = new Item("test2");
        tracker.add(item0);
        tracker.add(item1);
        tracker.add(item2);

        String[] arr = new String[3];
        arr[0] = item0.getName();
        arr[1] = item1.getName();
        arr[2] = item2.getName();

        Item[] items = tracker.findAll();
        String[] result_arr = new String[3];
        result_arr[0] = items[0].getName();
        result_arr[1] = items[1].getName();
        result_arr[2] = items[2].getName();

        assertThat(result_arr, is(arr));
    }
}