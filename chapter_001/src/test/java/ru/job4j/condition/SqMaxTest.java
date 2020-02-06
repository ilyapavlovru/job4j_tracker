package ru.job4j.condition;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SqMaxTest {
    @Test
    public void whenFirstMax() {

        int first = 4;
        int second = 1;
        int third = 1;
        int forth = 1;

        int expected = 4;

        int result = SqMax.max(first, second, third, forth);
        assertThat(result, is(expected));
    }

    @Test
    public void whenSecondMax() {

        int first = 1;
        int second = 4;
        int third = 1;
        int forth = 1;

        int expected = 4;

        int result = SqMax.max(first, second, third, forth);
        assertThat(result, is(expected));
    }

    @Test
    public void whenThirdMax() {

        int first = 1;
        int second = 1;
        int third = 4;
        int forth = 1;

        int expected = 4;

        int result = SqMax.max(first, second, third, forth);
        assertThat(result, is(expected));
    }

    @Test
    public void whenForthMax() {

        int first = 1;
        int second = 1;
        int third = 1;
        int forth = 4;

        int expected = 4;

        int result = SqMax.max(first, second, third, forth);
        assertThat(result, is(expected));
    }

    @Test
    public void whenAllTheSame() {

        int first = 4;
        int second = 4;
        int third = 4;
        int forth = 4;

        int expected = 4;

        int result = SqMax.max(first, second, third, forth);
        assertThat(result, is(expected));
    }
}
