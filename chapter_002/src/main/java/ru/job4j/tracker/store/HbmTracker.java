package ru.job4j.tracker.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.tracker.model.Item;

import javax.persistence.Query;
import java.util.List;

public class HbmTracker implements Store, AutoCloseable {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    @Override
    public Item add(Item item) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            session.save(item);
            session.getTransaction().commit();
            return item;
        }
    }

    @Override
    public boolean replace(int id, Item item) {
        item.setId(id);
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            session.update(item);
            session.getTransaction().commit();
            return true;
        }
    }

    @Override
    public boolean delete(int id) {
        Item item = findById(id);
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            session.delete(item);
            session.getTransaction().commit();
            return true;
        }
    }

    @Override
    public List<Item> findAll() {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            List<Item> result = session.createQuery("from ru.job4j.tracker.model.Item").list();
            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public List<Item> findByName(String key) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            Query query = session.createQuery("from ru.job4j.tracker.model.Item where name = :key");
            query.setParameter("key", key);
            List result = ((org.hibernate.query.Query) query).list();
            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public Item findById(int id) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            Item result = session.get(Item.class, id);
            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public void close() throws Exception {
        StandardServiceRegistryBuilder.destroy(registry);
    }
}
