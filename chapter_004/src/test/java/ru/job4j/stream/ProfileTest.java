package ru.job4j.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ProfileTest {

    @Test
    public void isScoreMoreThan70AndLessThan100Test() {
        List<Profile> clients = List.of(
                new Profile("Ivanov", new Address("Moscow", "Abrikosovaya", 3, 4)),
                new Profile("Petrov", new Address("Moscow", "Vinogradnaya", 5, 6)),
                new Profile("Sidorov", new Address("Moscow", "Tenistaya", 7, 8))
        );

        Profiles profiles = new Profiles();
        List<Address> result = profiles.collect(clients);
        List<Address> expected = Arrays.asList(
                new Address("Moscow", "Abrikosovaya", 3, 4),
                new Address("Moscow", "Vinogradnaya", 5, 6),
                new Address("Moscow", "Tenistaya", 7, 8)
        );
        assertThat(result, is(expected));
    }
}