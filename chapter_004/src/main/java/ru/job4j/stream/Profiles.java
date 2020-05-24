package ru.job4j.stream;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Profiles {
    List<Address> collect(List<Profile> profiles) {
        return profiles.stream()
                .map(
                        profile -> profile.getAddress()
                )
                .collect(Collectors.toList());
    }

    List<Address> collectSortedDistinctAddresses(List<Profile> profiles) {
        return profiles.stream()
                .map(
                        profile -> profile.getAddress()
                )
                .sorted(Comparator.comparing(Address::getCity))
                .distinct()
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<Profile> clients = List.of(
                new Profile("Ivanov", new Address("Spb", "Tenistaya", 3, 4)),
                new Profile("Sidorov", new Address("Moscow", "Vinogradnaya", 5, 6)),
                new Profile("Sidorova", new Address("Moscow", "Vinogradnaya", 5, 6))
        );
        Profiles profiles = new Profiles();
        List<Address> addresses = profiles.collect(clients);
        addresses.forEach(System.out::println);
        System.out.println();

        List<Address> sortedDistinctAddresses = profiles.collectSortedDistinctAddresses(clients);
        sortedDistinctAddresses.forEach(System.out::println);
    }
}
