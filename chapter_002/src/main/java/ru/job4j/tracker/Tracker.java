package ru.job4j.tracker;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Tracker {
    /**
     * Массив для хранения заявок.
     */
//    private final Item[] items = new Item[100];
    private final List<Item> items = new ArrayList<Item>();


    public int getPosition() {
        return items.size();
    }

    /**
     * Метод добавления заявки в хранилище
     * @param item новая заявка
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        items.add(item);
        return item;
    }

    /**
     * Метод удаления заявки по id
     * @param id позиция заявки в хранилище
     */
    public boolean delete(String id) {
        int index = indexOf(id);
        if (index == -1) {
            return false;
        } else {
            this.items.remove(index);
            return true;
        }
    }

    /**
     * Метод генерирует уникальный ключ для заявки.
     * Так как у заявки нет уникальности полей, имени и описание. Для идентификации нам нужен уникальный ключ.
     * @return Уникальный ключ.
     */
    private String generateId() {
        Random rm = new Random();
        return String.valueOf(rm.nextLong() + System.currentTimeMillis());
    }


    /**
     * Метод  возвращает копию массива this.items без null элементов (без пустых клеток)
     * @return копия массива this.items без null элементов (без пустых клеток)
     */
    public List<Item> findAll() {
        return this.items;
    }


    /**
     * Метод  проверяет в цикле все элементы массива this.items, сравнивая
     * name (используя метод getName класса Item) с аргументом метода String key.
     * Элементы, у которых совпадает name, копирует в результирующий массив и возвращает его
     * @param key ключевое слово
     * @return массив Item с эл-ми, у которых поле name совпадает с key
     */
    public List<Item> findByName(String key) {
        List<Item> itemsWithKeywords = new ArrayList<Item>();
        for (int index = 0; index < this.items.size(); index++) {
            Item item = this.items.get(index);
            if (item.getName().equals(key)) {
                itemsWithKeywords.add(item);
            }
        }
        return itemsWithKeywords;
    }


    /**
     * Метод проверяет в цикле все элементы массива this.items, сравнивая id с аргументом String id
     * и возвращает найденный Item. Если Item не найден - возвращает null.
     * @param id идентификатор для поиска
     * @return найденный Item
     */
    public Item findById(String id) {
        Item item = null;
        for (int index = 0; index < items.size(); index++) {
            Item current = items.get(index);
            if (current.getId().equals(id)) {
                item = current;
                break;
            }
        }
        return item;
    }


    private int indexOf(String id) {
        int rsl = -1;
        for (int index = 0; index < items.size(); index++) {
            if (items.get(index).getId().equals(id)) {
                rsl = index;
                break;
            }
        }
        return rsl;
    }

    /**
     * Метод замены заявки в хранилище (id заявки сохраняется)
     * @param id заявки, которую нужно заменить
     * @param item новая заявка
     */
    public boolean replace(String id, Item item) {
        int index = indexOf(id);
        if (index == -1) {
            return false;
        } else {
            item.setId(id);
            this.items.set(index, item);
            return true;
        }
    }
}