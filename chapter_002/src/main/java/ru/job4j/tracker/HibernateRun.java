package ru.job4j.tracker;

import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.tracker.model.Item;
import ru.job4j.tracker.store.HbmTracker;
import ru.job4j.tracker.store.Store;

import java.sql.Timestamp;
import java.util.List;

public class HibernateRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            Store store = new HbmTracker();

            Item item = new Item("Learn Hibernate 3", "Description for Learn Hibernate 3",
                    new Timestamp(1459510232000L));
            Item savedItem = store.add(item);
            System.out.println(savedItem);

            item.setName("Learn Hibernate 4.");
            store.replace(savedItem.getId(), item);
            System.out.println(item);

            Item rsl = store.findById(item.getId());
            System.out.println(rsl);
            store.delete(rsl.getId());

            List<Item> items = store.findByName("Learn Hibernate 4.");
            for (Item it : items) {
                System.out.println(it);
            }

            List<Item> list = store.findAll();
            for (Item it : list) {
                System.out.println(it);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
