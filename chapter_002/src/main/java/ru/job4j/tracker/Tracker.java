package ru.job4j.tracker;

import java.util.Arrays;
import java.util.Random;

public class Tracker {
    /**
     * Массив для хранения заявок.
     */
    private final Item[] items = new Item[100];

    /**
     * Указатель ячейки для новой заявки.
     */
    private int position = 0;

    /**
     * Метод добавления заявки в хранилище
     * @param item новая заявка
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        items[this.position++] = item;
        return item;
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
     * @return TODO
     */
    public Item[] findAll() {
        Item[] itemsWithoutNull = new Item[this.items.length];
        int size = 0;
        for (int index = 0; index < this.items.length; index++) {
            Item item = this.items[index];
            if (item != null) {
                itemsWithoutNull[size] = item;
                size++;
            }
        }
        itemsWithoutNull = Arrays.copyOf(itemsWithoutNull, size);
        return itemsWithoutNull;
    }


    /**
     * Метод  проверяет в цикле все элементы массива this.items, сравнивая
     * name (используя метод getName класса Item) с аргументом метода String key.
     * Элементы, у которых совпадает name, копирует в результирующий массив и возвращает его
     * @param key ключевое слово
     * @return TODO
     */
    public Item[] findByName(String key) {
        Item[] itemsWithKeywords = new Item[this.items.length];
        int size = 0;
        for (int index = 0; index < this.items.length; index++) {
            Item item = this.items[index];
            if (item.getName().equals(key)) {
                itemsWithKeywords[size] = item;
                size++;
            }
        }
        itemsWithKeywords = Arrays.copyOf(itemsWithKeywords, size);
        return itemsWithKeywords;
    }


    /**
     * Метод проверяет в цикле все элементы массива this.items, сравнивая id с аргументом String id
     * и возвращает найденный Item. Если Item не найден - возвращает null.
     * @param id идентификатор для поиска
     * @return TODO
     */
    public Item findById(String id) {
        for (int index = 0; index < this.items.length; index++) {
            Item item = this.items[index];
            if (item.getId().equals(id)) {
                return item;
            }
        }
        return null;
    }
}