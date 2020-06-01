package ru.job4j.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class HumanUsage {
    public static void main(String[] args) {
        List<Human> humans = Arrays.asList(
                new Human("Sam", Arrays.asList("Buddy", "Lucy")),
                new Human("Bob", Arrays.asList("Frankie", "Rosie")),
                new Human("Marta", Arrays.asList("Simba", "Tilly")));

        // До Java 8
//        List<String> petNames = new ArrayList<>();
//        for (Human human : humans) {
//            petNames.addAll(human.getPets());
//        }


        // сейчас
        List<String> petNames = humans.stream()
                .map(human -> human.getPets()) //преобразовываем Stream<Human> в Stream<List<String>>
                .flatMap(pets -> pets.stream())//"разворачиваем" Stream<List<String>> в Stream<String>
                .collect(Collectors.toList());

        System.out.println(petNames); // output [Buddy, Lucy, Frankie, Rosie, Simba, Tilly]



        // До Java 8
//        int[][] arr = {{1, 2}, {3, 4}, {5, 6}}; // массив 3 на 2
//
//        int[] newArr = new int[arr.length * arr[0].length]; // length = 6
//
//        for (int i = 0, index = 0; i < arr.length; i++) {
//            for (int j = 0; j < arr[i].length; j++) {
//                newArr[index++] = arr[i][j];
//            }
//        }
//        System.out.println(Arrays.toString(newArr)); //output [1, 2, 3, 4, 5, 6]


        // сейчас
        int[][] arr = {{1, 2}, {3, 4}, {5, 6}};

        int[] newArr = Arrays.stream(arr)
                .flatMapToInt(i -> Arrays.stream(i)) //преобразовываем IntStream<int[]> в IntStream
                .toArray(); // преобразовываем IntStream в int[]

        System.out.println(Arrays.toString(newArr)); //output [1, 2, 3, 4, 5, 6]



        List<List<Integer>> matrix = List.of(
                List.of(1, 2),
                List.of(3, 4)
        );

        System.out.println(
                matrix.stream()
                        .flatMap(e -> e.stream())
                        .collect(Collectors.toList())
        );
    }
}
