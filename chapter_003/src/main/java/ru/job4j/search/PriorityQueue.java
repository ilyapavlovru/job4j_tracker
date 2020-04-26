package ru.job4j.search;

import java.util.LinkedList;

public class PriorityQueue {
    private LinkedList<Task> tasks = new LinkedList<>();

    /**
     * Метод вставляет в нужную позицию элемент.
     * Позиция определяется по полю приоритет.
     * @param task задача
     */
    public void put(Task task) {
        int index = 0;
        int k = 0;
        for (Task element : tasks) {
            if (task.getPriority() < element.getPriority()) {
                index = k;
                break;
            }
            k++;
        }
        this.tasks.add(index, task);
    }

    public Task take() {
        return tasks.remove(0);
    }
}