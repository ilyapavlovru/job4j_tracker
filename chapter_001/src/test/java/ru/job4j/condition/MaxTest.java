package ru.job4j.condition;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MaxTest {
    @Test
    public void whenMax1To2Then2() {
        int result = Max.max(1, 2);
        assertThat(result, is(2));
    }

    @Test
    public void whenMax2To1Then2() {
        int result = Max.max(2, 1);
        assertThat(result, is(2));
    }

    @Test
    public void whenMax4To3Then4() {
        int result = Max.max(4, 3);
        assertThat(result, is(4));
    }

    @Test
    public void whenMax4To4Then4() {
        int result = Max.max(4, 4);
        assertThat(result, is(4));
    }

    @Test
    public void whenFirst1Second2Third3() {
        int result = Max.max(1, 2, 3);
        assertThat(result, is(3));
    }

    @Test
    public void whenFirst3Second2Third1() {
        int result = Max.max(3, 2, 1);
        assertThat(result, is(3));
    }

    @Test
    public void whenFirst3Second2Third4() {
        int result = Max.max(3, 2, 4);
        assertThat(result, is(4));
    }

    @Test
    public void whenFirst3Second3Third3() {
        int result = Max.max(3, 3, 3);
        assertThat(result, is(3));
    }
}
