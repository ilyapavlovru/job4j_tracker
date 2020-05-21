package ru.job4j.stream;

import java.util.List;
import java.util.stream.Collectors;

public class StreamUsage {
    public static class Task {
        private final String name;
        private final long spent;

        public Task(String name, long spent) {
            this.name = name;
            this.spent = spent;
        }

        @Override
        public String toString() {
            return "Task{"
                    + "name='" + name + '\''
                    + ", spent=" + spent
                    + '}';
        }
    }

    public static void main(String[] args) {

        System.out.println("--- StreamUsage ---");

        System.out.println("Input list:");
        List<Task> tasks = List.of(
                new Task("Bug #1", 100),
                new Task("Task #2", 100),
                new Task("Bug #3", 100)
        );
        tasks.forEach(System.out::println);
        System.out.println();

        // отфильтруем все задачи, где в имени есть слово Bug и сохраним эти задачи в коллекцию List
        System.out.println("Filter bugs:");
        List<Task> bugs = tasks.stream().filter(
                task -> task.name.contains("Bug")
        ).collect(Collectors.toList());
        bugs.forEach(System.out::println);
        System.out.println();

        // допустим, что на нужно получить только имена задач
        System.out.println("Map names:");
        List<String> names = tasks.stream().map(
                task -> task.name
        ).collect(Collectors.toList());
        names.forEach(System.out::println);
        System.out.println();

        // посчитаем общую сумму потраченную на все задачи
        System.out.print("Sum of spent: ");
        long total = tasks.stream().map(
                task -> task.spent
        ).reduce(0L, Long::sum);
        System.out.println(total);
    }
}
