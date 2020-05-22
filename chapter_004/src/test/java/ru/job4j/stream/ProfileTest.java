package ru.job4j.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ProfileTest {

    @Test
    public void collectAddressesFromProfilesTest() {
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

    @Test
    public void collectSortedDistinctAddressesFromAddressesTest() {
        List<Profile> clients = List.of(
                new Profile("Ivanov", new Address("Spb", "Tenistaya", 3, 4)),
                new Profile("Sidorov", new Address("Moscow", "Vinogradnaya", 5, 6)),
                new Profile("Sidorova", new Address("Moscow", "Vinogradnaya", 5, 6))
        );

        Profiles profiles = new Profiles();
        List<Address> result = profiles.collectSortedDistinctAddresses(clients);
        List<Address> expected = Arrays.asList(
                new Address("Moscow", "Vinogradnaya", 5, 6),
                new Address("Spb", "Tenistaya", 3, 4)
        );
        assertThat(result, is(expected));
    }
}