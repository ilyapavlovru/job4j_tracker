package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MinDiapasonTest {

    @Test
    public void whenArrayHasMinVal3() {

        int[] input = new int[] {5, 10, 3};
        int start = 0;
        int finish = 2;
        int result = MinDiapason.findMin(input, start, finish);
        int expect = 3;
        assertThat(result, is(expect));
    }

    @Test
    public void whenArrayHasMinVal5() {

        int[] input = new int[] {5, 10, 6};
        int start = 0;
        int finish = 2;
        int result = MinDiapason.findMin(input, start, finish);
        int expect = 5;
        assertThat(result, is(expect));
    }

    @Test
    public void whenArrayHasMinVal3AndTheSameAllValues() {

        int[] input = new int[] {5, 5, 5};
        int start = 0;
        int finish = 2;
        int result = MinDiapason.findMin(input, start, finish);
        int expect = 5;
        assertThat(result, is(expect));
    }

    @Test
    public void whenArrayHasMinVal5OutsideSelectedDiapason() {

        int[] input = new int[] {5, 10, 6};
        int start = 1;
        int finish = 2;
        int result = MinDiapason.findMin(input, start, finish);
        int expect = 6;
        assertThat(result, is(expect));
    }
}
