package ru.job4j.ex;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class FactTest {
    @Test(expected = IllegalArgumentException.class)
    public void whenNLessThen1ThenFinish() {
        Fact.calc(0);
    }

    @Test
    public void whenNIs3Then6() {
        int rsl = Fact.calc(3);
        assertThat(rsl, is(6));
    }
}
