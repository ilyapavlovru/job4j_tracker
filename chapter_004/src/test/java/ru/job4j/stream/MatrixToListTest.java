package ru.job4j.stream;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class MatrixToListTest {
    @Test
    public void matrixToListViaArraysStream() {
        Integer[][] input = {{1, 2}, {3, 4}, {5, 6}};
        List<Integer> expected = List.of(1, 2, 3, 4, 5, 6);
        MatrixToList matrixToList = new MatrixToList();
        List<Integer> result = matrixToList.matrixToListViaArraysStream(input);
        assertThat(result, is(expected));
    }

    @Test
    public void matrixToListViaStreamOf() {
        Integer[][] input = {{1, 2}, {3, 4}, {5, 6}};
        List<Integer> expected = List.of(1, 2, 3, 4, 5, 6);
        MatrixToList matrixToList = new MatrixToList();
        List<Integer> result = matrixToList.matrixToListViaStreamOf(input);
        assertThat(result, is(expected));
    }

    @Test
    public void matrixToListViaListstream() {
        List<List<Integer>> input = List.of(
                List.of(1, 2),
                List.of(3, 4)
        );
        List<Integer> expected = List.of(1, 2, 3, 4);
        MatrixToList matrixToList = new MatrixToList();
        List<Integer> result = matrixToList.matrixToListViaListstream(input);
        assertThat(result, is(expected));
    }
}