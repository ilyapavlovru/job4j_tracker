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

        String[] resultArr = new String[3];
        resultArr[0] = tracker.findById(item0.getId()).getName();
        resultArr[1] = tracker.findById(item1.getId()).getName();
        resultArr[2] = tracker.findById(item2.getId()).getName();

        assertThat(resultArr, is(arr));
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
        String[] resultArr = new String[3];
        resultArr[0] = items[0].getName();
        resultArr[1] = items[1].getName();
        resultArr[2] = items[2].getName();

        assertThat(resultArr, is(arr));
    }

    @Test
    public void whenAdd3NewItemsAndTestFindByName() {
        Tracker tracker = new Tracker();
        Item item0 = new Item("test0");
        Item item1 = new Item("test1");
        Item item2 = new Item("test1");
        tracker.add(item0);
        tracker.add(item1);
        tracker.add(item2);

        String[] arr = new String[2];
        arr[0] = item1.getName();
        arr[1] = item2.getName();

        Item[] items = tracker.findByName("test1");
        String[] resultArr = new String[2];
        resultArr[0] = items[0].getName();
        resultArr[1] = items[1].getName();

        assertThat(resultArr, is(arr));
    }
}