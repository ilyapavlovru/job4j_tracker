package ru.job4j.stream;

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

    public static void main(String[] args) {
        List<Profile> clients = List.of(
                new Profile("Ivanov", new Address("Moscow", "Abrikosovaya", 3, 4)),
                new Profile("Petrov", new Address("Moscow", "Vinogradnaya", 5, 6)),
                new Profile("Sidorov", new Address("Moscow", "Tenistaya", 7, 8))
        );
        Profiles profiles = new Profiles();
        List<Address> addresses = profiles.collect(clients);
        addresses.forEach(System.out::println);
    }
}
