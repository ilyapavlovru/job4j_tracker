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

    public int getPosition() {
        return position;
    }


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
     * Метод редактирования заявки в хранилище
     * @param position позиция заявки в хранилище
     * @param newName новое имя для заявки
     */
    public void edit(int position, String newName) {
        this.items[position].setName(newName);
    }

    /**
     * Метод удаления заявки из хранилища
     * @param index позиция заявки в хранилище
     */
    public void delete(int index) {
        for (int i = index; i < this.items.length - 1; i++) {
            this.items[i] = this.items[i + 1];
        }
        this.items[this.items.length - 1] = null;
        this.position--;
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
    public Item[] findAll() {
        return Arrays.copyOf(this.items, position);
    }


    /**
     * Метод  проверяет в цикле все элементы массива this.items, сравнивая
     * name (используя метод getName класса Item) с аргументом метода String key.
     * Элементы, у которых совпадает name, копирует в результирующий массив и возвращает его
     * @param key ключевое слово
     * @return массив Item с эл-ми, у которых поле name совпадает с key
     */
    public Item[] findByName(String key) {
        Item[] itemsWithKeywords = new Item[this.position];
        int size = 0;
        for (int index = 0; index < this.position; index++) {
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
     * @return найденный Item
     */
    public Item findById(String id) {
        return items[indexOf(id)];
    }


    private int indexOf(String id) {
        int rsl = -1;
        for (int index = 0; index < position; index++) {
            if (items[index].getId().equals(id)) {
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
        item.setId(id);
        this.items[index] = item;
        return true;
    }
}