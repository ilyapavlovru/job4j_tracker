package ru.job4j.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class MatrixToList {
    List<Integer> matrixToListViaArraysStream(Integer[][] matrix) {
        return Arrays.stream(matrix)
                .flatMap(e -> Arrays.stream(e))
                .collect(Collectors.toList());
    }

    List<Integer> matrixToListViaStreamOf(Integer[][] matrix) {
        return Arrays.stream(matrix)
                .flatMap(e -> Arrays.stream(e))
                .collect(Collectors.toList());
    }

    List<Integer> matrixToListViaListstream(List<List<Integer>> matrix) {
        return matrix.stream()
                .flatMap(e -> e.stream())
                .collect(Collectors.toList());
    }
}
